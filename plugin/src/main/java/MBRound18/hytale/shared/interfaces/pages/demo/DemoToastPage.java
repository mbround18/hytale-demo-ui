package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoToastPage extends AbstractDemoPage {
  public DemoToastPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoToast.ui");
  }
}
