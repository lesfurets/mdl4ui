/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.ez18n.apt.processor;

import org.apache.commons.lang3.StringUtils;
import org.ez18n.apt.LabelTemplateMethod;
import org.ez18n.apt.TemplateLoader;
import org.ez18n.apt.base.TemplateParam;
import org.ez18n.apt.macro.PropertyParsingException;

import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.util.*;

import static java.text.DateFormat.SHORT;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.ez18n.apt.macro.MacroProcessor.replaceProperties;

public abstract class TestBundleProcessor extends LabelBundleProcessor {
  protected String template;
  protected String methodTemplate;

  public TestBundleProcessor() {
    try {
      template = TemplateLoader.load("TestBundle.template");
      methodTemplate = TemplateLoader.load("TestBundleMethod.template");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  protected String getTargetSimpleName(TypeElement typeElement) {
    return getBundleClassName(typeElement, false) + "Test";
  }

  protected abstract String getBundleClassName(TypeElement typeElement, boolean fqcn);

  @Override
  protected String getCode(TypeElement bundleType, List<LabelTemplateMethod> methods) {
    final StringBuffer methodsCode = new StringBuffer();
    for (LabelTemplateMethod method : methods) {
      methodsCode.append(getCode(bundleType, method));
    }

    final String code;
    final Map<String, String> conf = new HashMap<String, String>();
    conf.put("process.class", getClass().getName());
    conf.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
    conf.put("target.class.name", getTargetSimpleName(bundleType));
    conf.put("package.name", bundleType.getEnclosingElement().toString());
    conf.put("methods.code", methodsCode.toString());
    conf.put("annotation.qualified.class.name", getAnnotation().getName());
    conf.put("annotation.simple.class.name", getAnnotation().getSimpleName());
    conf.put("bundle.class.name", bundleType.getSimpleName().toString());
    try {
      code = replaceProperties(template, conf, NO_VALUE);
    } catch (PropertyParsingException e) {
      throw new RuntimeException(e);
    }
    return code;
  }

  private final String getCode(TypeElement bundleType, LabelTemplateMethod method) {
    if (isEmpty(method.getBase())) {
      processingEnv.getMessager().printMessage(Kind.WARNING, "base label is null", bundleType);
    }

    List<String> params = new ArrayList<String>();
    for (TemplateParam param : method.getParams()) {
      if (Integer.TYPE.getName().equals(param.getType())) {
        params.add("0");
        continue;
      }
      if (Double.TYPE.getName().equals(param.getType())) {
        params.add("0.0");
        continue;
      }

      final Class<?> clazz;
      try {
        clazz = Class.forName(param.getType());
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }

      if (Integer.class.isAssignableFrom(clazz)) {
        params.add("0");
      } else if (Double.class.isAssignableFrom(clazz)) {
        params.add("0.0");
      } else {
        params.add("null");
      }

    }

    final String code;
    final Map<String, String> conf = new HashMap<String, String>();
    conf.put("source.class.name.camel", toCamelCase(bundleType));
    conf.put("method.name", method.getName());
    conf.put("input.params", StringUtils.join(params, ","));
    try {
      code = replaceProperties(methodTemplate, conf, NO_VALUE);
    } catch (PropertyParsingException e) {
      throw new RuntimeException(e);
    }
    return code;
  }

  protected abstract Class<? extends Annotation> getAnnotation();
}
