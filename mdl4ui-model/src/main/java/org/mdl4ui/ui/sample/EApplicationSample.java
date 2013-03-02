package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.Arrays;
import java.util.List;

import org.mdl4ui.base.model.ApplicationID;
import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.base.model.UIElementImpl;

public enum EApplicationSample implements ApplicationID {
    SAMPLE(EScreenSample.SCR_REGISTRATION, EScreenSample.SCR_DONE);

    private final List<ElementID> childs;

    private EApplicationSample(ElementID... blocks) {
        this.childs = Arrays.asList(blocks);
    }

    @Override
    public EElementType elementType() {
        return EElementType.APPLICATION;
    }

    @Override
    public List<ElementID> childs() {
        return childs;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ScreenID> screens() {
        return (List) childs;
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
    public ScreenID nextScreen(ScreenID screen) {
        return UIElementImpl.<ScreenID, ElementID> nextChild(this, screen);
    }
}
