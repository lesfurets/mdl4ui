/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.TextBoxField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.FavoriteWebsite;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField({ EFieldSample.FAVORITE_SITE_NAME_1,
                EFieldSample.FAVORITE_SITE_NAME_2,
                EFieldSample.FAVORITE_SITE_NAME_3 }))
public class FavoriteWebSiteNameEditor extends SampleEditor {

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        FavoriteWebsite webSite = context.getUserAccount().getWebSite(field.position() - 1);
        if (webSite == null) {
            return null;
        } else {
            return webSite.getName();
        }
    }

    @Override
    protected void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        FavoriteWebsite webSite = context.getUserAccount().getWebSite(field.getFieldID().position() - 1);
        if (webSite != null) {
            TextBoxField textbox = field.getComponent();
            textbox.setValue(webSite.getName());
        }
    }

    @Override
    protected void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        String value = textbox.getValue();
        if (value != null && !value.isEmpty()) {
            FavoriteWebsite webSite = context.getUserAccount().getWebSite(field.getFieldID().position() - 1);
            if (webSite == null) {
                webSite = new FavoriteWebsite();
                int index = field.getFieldID().position() - 1;
                context.getUserAccount().getTop3WebSite().add(index, webSite);
            }
            webSite.setName(value);
        }
    }

    @Override
    protected FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        return valid(field);
    }
}
