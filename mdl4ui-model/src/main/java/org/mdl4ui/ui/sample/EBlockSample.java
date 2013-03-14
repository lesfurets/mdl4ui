package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;
import static org.mdl4ui.ui.sample.EFieldSample.BIRTHDATE;
import static org.mdl4ui.ui.sample.EFieldSample.EMAIL;
import static org.mdl4ui.ui.sample.EFieldSample.EMAIL_ACCEPTED;
import static org.mdl4ui.ui.sample.EFieldSample.FIRST_NAME;
import static org.mdl4ui.ui.sample.EFieldSample.LANGUAGE;
import static org.mdl4ui.ui.sample.EFieldSample.LAST_NAME;
import static org.mdl4ui.ui.sample.EFieldSample.LOGIN;
import static org.mdl4ui.ui.sample.EFieldSample.PASSWORD;
import static org.mdl4ui.ui.sample.EFieldSample.PASSWORD_CONFIRMATION;
import static org.mdl4ui.ui.sample.EFieldSample.PHONE_NUMBER;
import static org.mdl4ui.ui.sample.EFieldSample.TIMEZONE;
import static org.mdl4ui.ui.sample.EGroupSample.EMAIL_GROUP;

import java.util.Arrays;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ElementType;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.UIElementImpl;

public enum EBlockSample implements BlockID {
    PERSONAL_INFORMATIONS(FIRST_NAME, //
                    LAST_NAME, //
                    EMAIL, //
                    BIRTHDATE), //
    MAIL_SETTINGS(LANGUAGE, //
                    EMAIL_ACCEPTED, //
                    EMAIL_GROUP, //
                    TIMEZONE), //
    PHONE_SETTINGS(LANGUAGE, //
                    PHONE_NUMBER, //
                    TIMEZONE), //
    ACCOUNT(LOGIN, //
                    PASSWORD, //
                    PASSWORD_CONFIRMATION);

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
