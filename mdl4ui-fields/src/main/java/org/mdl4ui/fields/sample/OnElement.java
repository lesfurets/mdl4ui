package org.mdl4ui.fields.sample;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.mdl4ui.ui.sample.EBlockSample;
import org.mdl4ui.ui.sample.EFieldSample;

@Retention(RetentionPolicy.SOURCE)
public @interface OnElement {

    EFieldSample[] fields() default {};

    EBlockSample[] blocks() default {};
}
