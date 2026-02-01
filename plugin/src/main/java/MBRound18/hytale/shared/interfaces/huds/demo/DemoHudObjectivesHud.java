package MBRound18.hytale.shared.interfaces.huds.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

import MBRound18.hytale.shared.interfaces.abstracts.AbstractCustomUIHud;

public class DemoHudObjectivesHud extends AbstractCustomUIHud {
  public DemoHudObjectivesHud(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Huds/DemoHudObjectives.ui");
  }
}
