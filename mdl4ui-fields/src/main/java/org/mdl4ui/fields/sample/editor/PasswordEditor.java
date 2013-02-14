package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField({ EFieldSample.PASSWORD, EFieldSample.PASSWORD_CONFIRMATION }))
public class PasswordEditor extends DefaultEditor {
}
