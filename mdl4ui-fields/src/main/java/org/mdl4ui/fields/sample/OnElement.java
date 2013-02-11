package org.mdl4ui.fields.sample;

import org.mdl4ui.ui.sample.EBlockSample;
import org.mdl4ui.ui.sample.EFieldSample;

public @interface OnElement {

    EFieldSample[] fields() default {};

    EBlockSample[] blocks() default {};
}
