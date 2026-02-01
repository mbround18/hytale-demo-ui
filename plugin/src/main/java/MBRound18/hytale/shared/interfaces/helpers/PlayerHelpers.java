package MBRound18.hytale.shared.interfaces.helpers;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nonnull;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.NameMatching;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public final class PlayerHelpers {
  public @Nonnull Universe getUniverse() {
    Universe universe = Universe.get();
    return Objects.requireNonNull(universe, "universe");
  }

  public @Nonnull Player getPlayerByRef(
      @Nonnull Store<EntityStore> store,
      @Nonnull PlayerRef playerRef) {
    Ref<EntityStore> entityRef = Objects.requireNonNull(playerRef, "playerRef").getReference();
    entityRef = Objects.requireNonNull(entityRef, "entityRef");
    Player player = (Player) store.getComponent(entityRef, Player.getComponentType());
    return Objects.requireNonNull(player, "player");
  }

  public @Nonnull Player getPlayerByUUID(
      @Nonnull Store<EntityStore> store,
      @Nonnull UUID playerUUID) {
    Universe universe = getUniverse();
    PlayerRef playerRef = universe.getPlayer(playerUUID);
    return getPlayerByRef(store, Objects.requireNonNull(playerRef, "playerRef"));
  }

  public @Nonnull Player getPlayerByName(
      @Nonnull Store<EntityStore> store,
      @Nonnull String playerName) {
    Universe universe = getUniverse();
    PlayerRef playerRef = universe.getPlayerByUsername(Objects.requireNonNull(playerName, "playerName"),
        NameMatching.STARTS_WITH_IGNORE_CASE);
    return getPlayerByRef(store, Objects.requireNonNull(playerRef, "playerRef"));
  }
}
