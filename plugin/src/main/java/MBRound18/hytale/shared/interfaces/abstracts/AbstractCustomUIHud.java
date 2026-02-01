package MBRound18.hytale.shared.interfaces.abstracts;

import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import java.util.Objects;
import javax.annotation.Nonnull;

public abstract class AbstractCustomUIHud extends CustomUIHud {
  private final String uiPath;

  protected AbstractCustomUIHud(@Nonnull PlayerRef playerRef, @Nonnull String uiPath) {
    super(Objects.requireNonNull(playerRef, "playerRef"));
    this.uiPath = Objects.requireNonNull(uiPath, "uiPath");
  }

  @Override
  protected void build(@Nonnull UICommandBuilder builder) {
    // Relative path from Common/UI/Custom/*
    builder.append(uiPath);
  }

  public void clear() {
    UICommandBuilder builder = new UICommandBuilder();
    update(true, builder);
  }
}
