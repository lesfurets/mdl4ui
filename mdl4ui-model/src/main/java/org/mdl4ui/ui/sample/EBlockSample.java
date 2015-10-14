package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.Arrays;
import java.util.List;

import org.mdl4ui.base.model.*;

public enum EBlockSample implements BlockID {
    PERSONAL_INFORMATIONS(EFieldSample.FIRST_NAME, //
                    EFieldSample.LAST_NAME, //
                    EFieldSample.EMAIL, //
                    EFieldSample.BIRTHDATE), //
    MAIL_SETTINGS(EFieldSample.LANGUAGE, //
                    EFieldSample.TIMEZONE, //
                    EFieldSample.EMAIL_ACCEPTED, //
                    EGroupSample.EMAIL_GROUP, //
                    EGroupSample.FAVORITE_WEB_SITE_1, //
                    EGroupSample.FAVORITE_WEB_SITE_2, //
                    EGroupSample.FAVORITE_WEB_SITE_3), //
    PHONE_SETTINGS(EFieldSample.LANGUAGE, //
                    EFieldSample.PHONE_NUMBER, //
                    EFieldSample.TIMEZONE), //
    ACCOUNT(EFieldSample.LOGIN, //
                    EFieldSample.PASSWORD, //
                    EFieldSample.PASSWORD_CONFIRMATION);

    private final List<ElementID> childs;

    private EBlockSample(ElementID... blocks) {
        this.childs = Arrays.asList(blocks);
    }

    @Override
    public ElementType elementType() {
        return ElementType.BLOCK;
    }

    @Override
    public List<ElementID> childs() {
        return childs;
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
}
