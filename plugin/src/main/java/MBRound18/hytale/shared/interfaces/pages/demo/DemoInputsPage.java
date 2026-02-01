package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoInputsPage extends AbstractDemoPage {
  public DemoInputsPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoInputs.ui");
  }
}
