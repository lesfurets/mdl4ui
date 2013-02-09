/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.ez18n.apt.macro;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Template macro processor for variables declared like ${...} - Ant like
 */
public class MacroProcessor {

    private static final int MAX_DEPTH = 5;
    public static final String REF_PREFIX = "${";
    public static final String REF_SUFFIX = "}";
    private static final String STR_MORE_THAN_0_LEVEL_TO_EXPAND_1 = "There is more than {0} level to expand the property : ''{1}'')";
    private static final String STR_SYNTAX_ERROR_IN_0 = "Syntax error in property: ''{0}''";
    private static final MessageFormat MORE_THAN_0_LEVEL_TO_EXPAND_1 = new MessageFormat(
                    STR_MORE_THAN_0_LEVEL_TO_EXPAND_1);
    private static final Evaluator DEFAULT_STRING_EVAL = new DefaultEvaluator();

    public static String replaceProperties(String value, Map<String, ?> conf, String defaultReplacement)
                    throws PropertyParsingException {
        return replacePropertiesRec(value, conf, new ArrayList<String>(), new ArrayList<String>(), 0, false,
                        defaultReplacement);
    }

    public static String replaceProperties(String value, Map<String, ?> conf, String defaultReplacement,
                    Evaluator evaluator) throws PropertyParsingException {
        return replacePropertiesRec(value, conf, new ArrayList<String>(), new ArrayList<String>(), 0, false,
                        defaultReplacement, evaluator);
    }

    public static String replaceProperties(String value, Map<String, ?> conf, int depth)
                    throws PropertyParsingException {
        return replacePropertiesRec(value, conf, new ArrayList<String>(), new ArrayList<String>(), depth, false, null,
                        DEFAULT_STRING_EVAL);
    }

    /**
     * Macro-expand parameter references <code>${xx}</code>.<br>
     * If a reference is not found, the value of {@code param} is used if it is not null - otherwise referenc is not
     * replaced.
     * 
     * @param value input string
     * @param conf map containing all known parameters
     * @param fragments
     * @param propertyRefs
     * @param depth recursivity index, limited to {@value MacroProcessor#MAX_DEPTH}.
     * @param encodeHTML
     * @param defaultReplacement
     * @return macro-expanded value.
     * @throws PropertyParsingException if recursivity goes beyond {@value MacroProcessor#MAX_DEPTH} limit.
     */
    public static final String replacePropertiesRec(String value, Map<String, ?> conf, List<String> fragments,
                    List<String> propertyRefs, int depth, boolean encodeHTML, String defaultReplacement)
                    throws PropertyParsingException {
        return replacePropertiesRec(value, conf, fragments, propertyRefs, depth, encodeHTML, defaultReplacement,
                        DEFAULT_STRING_EVAL);
    }

    private static final String replacePropertiesRec(String value, Map<String, ?> conf, List<String> fragments,
                    List<String> propertyRefs, int depth, boolean encodeHTML, String defaultReplacement,
                    Evaluator evaluator) throws PropertyParsingException {
        parsePropertyString(value, fragments, propertyRefs);
        final StringBuilder unkownParam = new StringBuilder();
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> i = fragments.iterator();
        final Iterator<String> j = propertyRefs.iterator();
        while (i.hasNext()) {
            String fragment = (String) i.next();
            if (fragment == null) {
                final String propertyName = (String) j.next();
                fragment = evaluator.eval(conf, propertyName, defaultReplacement, encodeHTML);
                final boolean sameProperty = fragment.indexOf(REF_PREFIX + propertyName + REF_SUFFIX) >= 0;
                final boolean alwaysProperty = containProperty(fragment);
                if (alwaysProperty && !sameProperty)
                    unkownParam.append(fragment);
            }
            sb.append(fragment);
        }
        final boolean containProperty = containProperty(unkownParam.toString());
        final String expandedValue = sb.toString();
        if (containProperty && depth <= MAX_DEPTH) {
            fragments.clear();
            propertyRefs.clear();
            return replacePropertiesRec(expandedValue, conf, fragments, propertyRefs, depth + 1, encodeHTML, null,
                            evaluator);
        } else if (containProperty && depth > MAX_DEPTH)
            throw new PropertyParsingException(MORE_THAN_0_LEVEL_TO_EXPAND_1.format(new Object[] { MAX_DEPTH,
                            expandedValue }));
        else
            return expandedValue;

    }

    /**
     * Parses a string containing <code>${xxx}</code> style property references into two lists. The first list is a
     * collection of text fragments, while the other is a set of string property names. <code>null</code> entries in the
     * first list indicate a property reference from the second list.
     * 
     * It can be overridden with a more efficient or customized version.
     * 
     * @param textToParse Text to parse. Must not be <code>null</code>.
     * @param fragments List to add text fragments to. Must not be <code>null</code>.
     * @param propertyRefs List to add property names to. Must not be <code>null</code>.
     * 
     * @exception PropertyParsingException if the string contains an opening <code>${</code> without a closing
     *                <code>}</code>
     */
    private static final void parsePropertyString(String textToParse, List<String> fragments, List<String> propertyRefs)
                    throws PropertyParsingException {
        final MessageFormat SYNTAX_ERROR_IN_0 = new MessageFormat(STR_SYNTAX_ERROR_IN_0);
        int prev = 0;
        int pos;

        // search for the next instance of $ from the 'prev' position
        while ((pos = textToParse.indexOf('$', prev)) >= 0) {
            // if there was any text before this, add it as a fragment
            // XXX, this check could be modified to go if pos>prev;
            // seems like this current version could stick empty strings
            // into the list
            if (pos > 0)
                fragments.add(textToParse.substring(prev, pos));

            // if we are at the end of the string, we tack on a $
            // then move past it
            if (pos == (textToParse.length() - 1)) {
                fragments.add("$");
                prev = pos + 1;
            } else if (textToParse.charAt(pos + 1) != '{') {
                // peek ahead to see if the next char is a property or not
                // not a property: insert the char as a literal

                /*
                 * fragments.addElement(value.substring(pos + 1, pos + 2)); prev = pos + 2;
                 */
                if (textToParse.charAt(pos + 1) == '$') {
                    // backwards compatibility two $ map to one mode
                    fragments.add("$");
                    prev = pos + 2;
                } else {
                    // new behaviour: $X maps to $X for all values of X!='$'
                    fragments.add(textToParse.substring(pos, pos + 2));
                    prev = pos + 2;
                }
            } else {
                // property found, extract its name or bail on a typo
                final int endName = textToParse.indexOf('}', pos);
                if (endName < 0)
                    throw new PropertyParsingException(SYNTAX_ERROR_IN_0.format(new String[] { textToParse }));
                String propertyName = textToParse.substring(pos + 2, endName);
                fragments.add(null);
                propertyRefs.add(propertyName);
                prev = endName + 1;
            }
        }

        // no more $ signs found
        // if there is any tail to the file, append it
        if (prev < textToParse.length())
            fragments.add(textToParse.substring(prev));
    }

    private static final boolean containProperty(final String value) {
        final int pos = value.indexOf('$');
        return (pos >= 0) && (value.charAt(pos + 1) == '{');
    }
}
