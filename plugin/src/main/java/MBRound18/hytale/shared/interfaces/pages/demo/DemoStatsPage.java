package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoStatsPage extends AbstractDemoPage {
  public DemoStatsPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoStats.ui");
  }
}
