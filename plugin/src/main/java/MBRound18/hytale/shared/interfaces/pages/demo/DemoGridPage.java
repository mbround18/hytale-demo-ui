package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoGridPage extends AbstractDemoPage {
  public DemoGridPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoGrid.ui");
  }
}
