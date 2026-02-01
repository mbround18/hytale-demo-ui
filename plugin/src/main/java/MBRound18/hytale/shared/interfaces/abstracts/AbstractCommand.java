package MBRound18.hytale.shared.interfaces.abstracts;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import java.util.Objects;
import java.util.function.BiFunction;
import javax.annotation.Nonnull;

public abstract class AbstractCommand<THandler> extends AbstractPlayerCommand {
  protected final BiFunction<Ref<EntityStore>, Store<EntityStore>, THandler> handlerFactory;

  protected AbstractCommand(
      @Nonnull String name,
      @Nonnull String description,
      boolean requiresConfirmation,
      @Nonnull BiFunction<Ref<EntityStore>, Store<EntityStore>, THandler> handlerFactory) {
    super(name, description, requiresConfirmation);
    this.handlerFactory = Objects.requireNonNull(handlerFactory, "handlerFactory");
  }
}
