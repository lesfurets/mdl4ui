package org.mdl4ui.base.model;

import java.util.List;

public interface ScenarioID extends ElementID {

    List<ScreenID> screens();

    ScreenID nextScreen(ScreenID blockID);
}
