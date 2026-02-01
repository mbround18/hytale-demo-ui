package MBRound18.hytale.shared.interfaces.abstracts;

import javax.annotation.Nonnull;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import MBRound18.hytale.shared.interfaces.helpers.PlayerHelpers;

public class AbstractInteractivePage<T> extends InteractiveCustomUIPage<T> {
  protected final PlayerHelpers playerHelpers = new PlayerHelpers();

  public AbstractInteractivePage(
      @Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime, @Nonnull BuilderCodec<T> eventDataCodec) {
    super(playerRef, lifetime, eventDataCodec);
  }

  @Override
  public void build(
      @Nonnull Ref<EntityStore> ref,
      @Nonnull com.hypixel.hytale.server.core.ui.builder.UICommandBuilder builder,
      @Nonnull com.hypixel.hytale.server.core.ui.builder.UIEventBuilder events,
      @Nonnull Store<EntityStore> store) {
    /**
     * Intentionally empty; to be overridden by subclasses.
     */
    return;
  }
}
