package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoToolbarPage extends AbstractDemoPage {
  public DemoToolbarPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoToolbar.ui");
  }
}
