package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoRowsPage extends AbstractDemoPage {
  public DemoRowsPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoRows.ui");
  }
}
