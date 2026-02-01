package MBRound18.hytale.shared.interfaces.abstracts;

import java.util.Objects;

import javax.annotation.Nonnull;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import MBRound18.hytale.shared.interfaces.helpers.PlayerHelpers;

public abstract class AbstractInteraction {
  protected final Store<EntityStore> store;
  protected final Ref<EntityStore> ref;
  protected final PlayerHelpers playerHelpers = new PlayerHelpers();

  public AbstractInteraction(
      @Nonnull Ref<EntityStore> ref,
      @Nonnull Store<EntityStore> store) {

    this.store = Objects.requireNonNull(store, "store");
    this.ref = Objects.requireNonNull(ref, "ref");
  }

}
