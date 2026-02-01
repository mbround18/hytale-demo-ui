package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoUtilityPage extends AbstractDemoPage {
  public DemoUtilityPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoUtility.ui");
  }
}
