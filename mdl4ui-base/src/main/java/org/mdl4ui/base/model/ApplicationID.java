package org.mdl4ui.base.model;

import java.util.List;

public interface ApplicationID extends ElementID {

    List<ScreenID> screens();

    ScreenID nextScreen(ScreenID blockID);
}
