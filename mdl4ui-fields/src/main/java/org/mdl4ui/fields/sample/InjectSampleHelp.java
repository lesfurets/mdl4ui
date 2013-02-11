package org.mdl4ui.fields.sample;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mdl4ui.base.injection.InjectHelp;

@InjectHelp
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface InjectSampleHelp {

    OnElement value();
}
