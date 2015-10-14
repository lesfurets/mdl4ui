package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.Arrays;
import java.util.List;

import org.mdl4ui.base.model.*;

public enum EGroupSample implements GroupID {
    EMAIL_GROUP(EFieldSample.EMAILS_PREFERENCES, EFieldSample.MAX_EMAILS_PER_WEEK),//

    FAVORITE_WEB_SITE_1(EFieldSample.FAVORITE_SITE_NAME_1, EFieldSample.FAVORITE_SITE_URL_1),//
    FAVORITE_WEB_SITE_2(EFieldSample.FAVORITE_SITE_NAME_2, EFieldSample.FAVORITE_SITE_URL_2),//
    FAVORITE_WEB_SITE_3(EFieldSample.FAVORITE_SITE_NAME_3, EFieldSample.FAVORITE_SITE_URL_3);

    private final List<ElementID> childs;

    private EGroupSample(FieldID... fields) {
        this.childs = Arrays.<ElementID> asList(fields);
    }

    @Override
    public ElementType elementType() {
        return ElementType.GROUP;
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
