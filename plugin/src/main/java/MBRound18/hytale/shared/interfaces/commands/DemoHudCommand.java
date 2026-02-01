package MBRound18.hytale.shared.interfaces.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.entity.entities.player.hud.HudManager;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nonnull;

import MBRound18.hytale.shared.interfaces.abstracts.AbstractCommand;
import MBRound18.hytale.shared.interfaces.abstracts.AbstractCustomUIHud;
import MBRound18.hytale.shared.interfaces.huds.demo.DemoHudObjectivesHud;
import MBRound18.hytale.shared.interfaces.huds.demo.DemoHudPartyStatusHud;
import MBRound18.hytale.shared.interfaces.huds.demo.DemoHudQuickActionsHud;
import MBRound18.hytale.shared.interfaces.huds.demo.DemoHudStatsHud;
import MBRound18.hytale.shared.interfaces.huds.demo.DemoHudWidgetStripHud;

public class DemoHudCommand extends AbstractCommand<Object> {
  private static final Map<String, Function<PlayerRef, CustomUIHud>> HUDS = new LinkedHashMap<>();
  private static final String[] PRIMARY_NAMES = {
      "widgetstrip",
      "objectives",
      "partystatus",
      "quickactions",
      "stats"
  };

  static {
    register("widgetstrip", DemoHudWidgetStripHud::new, "demohudwidgetstrip");
    register("objectives", DemoHudObjectivesHud::new, "demohudobjectives");
    register("partystatus", DemoHudPartyStatusHud::new, "demohudpartystatus");
    register("quickactions", DemoHudQuickActionsHud::new, "demohudquickactions");
    register("stats", DemoHudStatsHud::new, "demohudstats");
  }

  private static void register(
      @Nonnull String primaryKey,
      @Nonnull Function<PlayerRef, CustomUIHud> factory,
      @Nonnull String... aliases) {
    HUDS.put(primaryKey, factory);
    for (String alias : aliases) {
      HUDS.put(alias, factory);
    }
  }

  private final RequiredArg<String> hudArg;

  public DemoHudCommand() {
    super("dhud", "Shows a demo HUD by name or resets HUD", false, (ref, store) -> new Object());
    this.hudArg = withRequiredArg("hud", "HUD name or reset", ArgTypes.STRING);
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
    if (playerRef == null) {
      context.sendMessage(Message.raw("HUD command requires a player."));
      return;
    }

    String hudName = context.get(hudArg);
    if (hudName == null || hudName.trim().isEmpty()) {
      context.sendMessage(Message.raw("Usage: /dhud <name|reset|list>. Available: " + availableHuds()));
      return;
    }

    String key = hudName.trim().toLowerCase(Locale.ROOT);
    HudManager hudManager = player.getHudManager();
    if (hudManager == null) {
      context.sendMessage(Message.raw("HUD manager unavailable."));
      return;
    }

    if ("list".equals(key)) {
      context.sendMessage(Message.raw("Available HUDs: " + availableHuds()));
      return;
    }

    if ("reset".equals(key)) {
      CustomUIHud currentHud = hudManager.getCustomHud();
      if (currentHud instanceof AbstractCustomUIHud) {
        ((AbstractCustomUIHud) currentHud).clear();
        context.sendMessage(Message.raw("HUD reset."));
      } else {
        context.sendMessage(Message.raw("No custom HUD or unknown HUD to reset."));
      }
      return;
    }

    Function<PlayerRef, CustomUIHud> factory = HUDS.get(key);
    if (factory == null) {
      context.sendMessage(
          Message.raw("Unknown HUD '" + hudName + "'. Available: " + availableHuds()));
      return;
    }

    CustomUIHud hud = factory.apply(playerRef);
    if (hud == null) {
      context.sendMessage(Message.raw("HUD factory returned no HUD."));
      return;
    }
    hudManager.setCustomHud(playerRef, hud);
  }

  public static String availableHuds() {
    return String.join(", ", Arrays.asList(PRIMARY_NAMES));
  }
}
