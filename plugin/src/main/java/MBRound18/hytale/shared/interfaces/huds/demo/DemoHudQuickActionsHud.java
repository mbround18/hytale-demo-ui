package MBRound18.hytale.shared.interfaces.huds.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

import MBRound18.hytale.shared.interfaces.abstracts.AbstractCustomUIHud;

public class DemoHudQuickActionsHud extends AbstractCustomUIHud {
  public DemoHudQuickActionsHud(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Huds/DemoHudQuickActions.ui");
  }
}
