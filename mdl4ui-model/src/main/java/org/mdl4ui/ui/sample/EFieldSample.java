package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.List;

import org.mdl4ui.base.model.*;

public enum EFieldSample implements FieldID {
    FIRST_NAME(FieldType.TEXTBOX, ESampleTag.USER), //
    LAST_NAME(FieldType.TEXTBOX, ESampleTag.USER), //
    BIRTHDATE(FieldType.DATE, ESampleTag.USER), //

    EMAIL(FieldType.TEXTBOX, ESampleTag.ACCOUNT), //
    LANGUAGE(FieldType.RADIO_GROUP, ESampleTag.ACCOUNT), //
    TIMEZONE(FieldType.LISTBOX, ESampleTag.ACCOUNT), //

    PHONE_NUMBER(FieldType.TEXTBOX, ESampleTag.ACCOUNT), //
    EMAIL_ACCEPTED(FieldType.RADIO_GROUP, ESampleTag.ACCOUNT), //
    EMAILS_PREFERENCES(FieldType.CHECKBOX_GROUP, ESampleTag.ACCOUNT), //
    MAX_EMAILS_PER_WEEK(FieldType.NUMERIC, ESampleTag.ACCOUNT), //

    FAVORITE_SITE_NAME_1(FieldType.TEXTBOX, 1, ESampleTag.ACCOUNT),
    FAVORITE_SITE_NAME_2(FieldType.TEXTBOX, 2, ESampleTag.ACCOUNT),
    FAVORITE_SITE_NAME_3(FieldType.TEXTBOX, 3, ESampleTag.ACCOUNT),

    FAVORITE_SITE_URL_1(FieldType.TEXTBOX, 1, ESampleTag.ACCOUNT),
    FAVORITE_SITE_URL_2(FieldType.TEXTBOX, 2, ESampleTag.ACCOUNT),
    FAVORITE_SITE_URL_3(FieldType.TEXTBOX, 3, ESampleTag.ACCOUNT),

    LOGIN(FieldType.TEXTBOX, ESampleTag.ACCOUNT), //
    PASSWORD(FieldType.PASSWORD, ESampleTag.ACCOUNT), //
    PASSWORD_CONFIRMATION(FieldType.PASSWORD, ESampleTag.ACCOUNT);

    private FieldType type;
    private final int position;
    private final TagId[] tags;

    private EFieldSample(FieldType type) {
        this(type, -1);
    }

    private EFieldSample(FieldType type, TagId... tags) {
        this(type, -1, tags);
    }

    private EFieldSample(FieldType type, int position, TagId... tags) {
        this.type = type;
        this.position = position;
        this.tags = tags;
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

    @Override
    public int position() {
        return position;
    }

    @Override
    public TagId[] tags() {
        return tags;
    }
}
