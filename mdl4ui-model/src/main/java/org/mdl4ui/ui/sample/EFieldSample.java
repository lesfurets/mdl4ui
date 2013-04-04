package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.ElementType;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.FieldType;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.UIElementImpl;

public enum EFieldSample implements FieldID {
    FIRST_NAME(FieldType.TEXTBOX), //
    LAST_NAME(FieldType.TEXTBOX), //
    EMAIL(FieldType.TEXTBOX), //
    BIRTHDATE(FieldType.DATE), //
    LANGUAGE(FieldType.RADIO_GROUP), //
    TIMEZONE(FieldType.LISTBOX), //

    PHONE_NUMBER(FieldType.TEXTBOX), //
    EMAIL_ACCEPTED(FieldType.RADIO_GROUP), //
    EMAILS_PREFERENCES(FieldType.CHECKBOX_GROUP), //
    MAX_WEEKLY_EMAILS(FieldType.NUMERIC), //

    LOGIN(FieldType.TEXTBOX), //
    PASSWORD(FieldType.PASSWORD), //
    PASSWORD_CONFIRMATION(FieldType.PASSWORD);
    private FieldType type;

    private EFieldSample(FieldType type) {
        this.type = type;
    }

    @Override
    public ElementType elementType() {
        return ElementType.FIELD;
    }

    @Override
    public List<ElementID> childs() {
        return null;
    }

    @Override
    public boolean contains(ElementID child) {
        return containsRec(this, child);
    }

    @Override
    public List<FieldID> fields() {
        return UIElementImpl.<FieldID, ElementID> collectFields(this);
    }

    @Override
    public List<BlockID> blocks() {
        return UIElementImpl.<BlockID, ElementID> collectBlocks(this);
    }

    @Override
    public List<GroupID> groups() {
        return UIElementImpl.<GroupID, ElementID> collectGroups(this);
    }

    @Override
    public FieldType type() {
        return type;
    }
}
