package MBRound18.hytale.shared.interfaces.debug.interactions;

import javax.annotation.Nonnull;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import MBRound18.hytale.shared.interfaces.abstracts.AbstractInteraction;

public class HelloInteractions extends AbstractInteraction {
  public HelloInteractions(
      @Nonnull Ref<EntityStore> ref,
      @Nonnull Store<EntityStore> store) {
    super(ref, store);
  }

  public void sendWrittenBy(@Nonnull Player player) {
    player.sendMessage(Message.raw("Written by MBRound18"));
  }
}
