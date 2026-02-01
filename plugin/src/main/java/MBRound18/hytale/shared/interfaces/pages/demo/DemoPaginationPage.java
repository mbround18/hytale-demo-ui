package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DemoPaginationPage extends AbstractDemoPage {
  public DemoPaginationPage(@Nonnull PlayerRef playerRef) {
    super(playerRef, "Demos/Pages/DemoPagination.ui");
  }
}
