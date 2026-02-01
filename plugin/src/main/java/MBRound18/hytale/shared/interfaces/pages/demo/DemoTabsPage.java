package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoTabsPage extends AbstractDemoPage {
  public DemoTabsPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoTabs.ui");
  }
}
