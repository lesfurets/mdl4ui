package org.mdl4ui.fields.sample;

import org.mdl4ui.base.injection.OnElement;
import org.mdl4ui.ui.sample.EFieldSample;

@OnElement
public @interface OnSample {

    EFieldSample[] fields() default {};
}
