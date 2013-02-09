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

import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringEscapeUtils;

public class DefaultEvaluator implements Evaluator {
    private static final Logger LOG = Logger.getLogger(DefaultEvaluator.class.getName());

    @Override
    public String eval(Map<String, ?> conf, String key, String replacement, boolean encodeHTML) {
        if (conf == null) {
            return replacement;
        }
        final Object value = conf.get(key);
        if (value == null) {
            LOG.info(key + " is unknown in configuration");
        }
        if (value == null && replacement != null && encodeHTML) {
            return escapeHtml(replacement);
        } else if (value == null && replacement != null) {
            return replacement;
        } else if (value == null) {
            return MacroProcessor.REF_PREFIX + key + MacroProcessor.REF_SUFFIX;
        } else if (encodeHTML) {
            return escapeHtml(value);
        }
        return value.toString();
    }

    private static final String escapeHtml(Object value) {
        return StringEscapeUtils.escapeHtml4(value.toString()).replace("\r\n", "<br>").replace("\n", "<br>");
    }
}
