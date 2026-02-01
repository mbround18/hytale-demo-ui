package MBRound18.hytale.shared.interfaces.pages.demo;

import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.BasicCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import java.util.Objects;
import javax.annotation.Nonnull;

public abstract class AbstractDemoPage extends BasicCustomUIPage {
  private final String uiPath;

  protected AbstractDemoPage(@Nonnull PlayerRef playerRef, @Nonnull String uiPath) {
    super(
        Objects.requireNonNull(playerRef, "playerRef"),
        CustomPageLifetime.CanDismissOrCloseThroughInteraction);
    this.uiPath = Objects.requireNonNull(uiPath, "uiPath");
  }

  @Override
  public void build(UICommandBuilder builder) {
    // Relative path from Common/UI/Custom/*
    builder.append(uiPath);
  }
}
