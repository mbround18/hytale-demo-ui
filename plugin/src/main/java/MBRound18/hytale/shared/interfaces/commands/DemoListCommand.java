package MBRound18.hytale.shared.interfaces.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import javax.annotation.Nonnull;

import MBRound18.hytale.shared.interfaces.abstracts.AbstractCommand;

public class DemoListCommand extends AbstractCommand<Object> {
  public DemoListCommand() {
    super("dlist", "Lists available demo UI pages", false, (ref, store) -> new Object());
  }

  @Override
  protected void execute(
      @Nonnull CommandContext context,
      @Nonnull Store<EntityStore> store,
      @Nonnull Ref<EntityStore> ref,
      @Nonnull PlayerRef playerRef,
      @Nonnull World world) {
    Player player = store.getComponent(ref, Player.getComponentType());
    if (player == null) {
      return;
    }

    context.sendMessage(Message.raw("Demo pages: " + DemoPageCommand.availablePages()));
  }
}
