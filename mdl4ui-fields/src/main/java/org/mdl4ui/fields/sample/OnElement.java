package org.mdl4ui.fields.sample;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.mdl4ui.ui.sample.EBlockSample;
import org.mdl4ui.ui.sample.EFieldSample;
import org.mdl4ui.ui.sample.EGroupSample;
import org.mdl4ui.ui.sample.EScreenSample;

@Retention(RetentionPolicy.SOURCE)
public @interface OnElement {

    EFieldSample[] fields() default {};

    EGroupSample[] groups() default {};

    EBlockSample[] blocks() default {};

    EScreenSample[] screens() default {};
}
