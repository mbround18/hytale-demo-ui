package MBRound18.hytale.shared.interfaces.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.BasicCustomUIPage;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import javax.annotation.Nonnull;

import MBRound18.hytale.shared.interfaces.abstracts.AbstractCommand;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoGridPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoInputsPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoModalPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoPaginationPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoRowsPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoStatsPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoTabsPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoToastPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoToolbarPage;
import MBRound18.hytale.shared.interfaces.pages.demo.DemoUtilityPage;

public class DemoPageCommand extends AbstractCommand<Object> {
  private static final Map<String, Function<PlayerRef, BasicCustomUIPage>> PAGES =
      new LinkedHashMap<>();
  private static final String[] PRIMARY_NAMES = {
      "grid",
      "inputs",
      "modal",
      "pagination",
      "rows",
      "stats",
      "tabs",
      "toast",
      "toolbar",
      "utility"
  };

  static {
    register("grid", DemoGridPage::new, "demogrid");
    register("inputs", DemoInputsPage::new, "demoinputs");
    register("modal", DemoModalPage::new, "demomodal");
    register("pagination", DemoPaginationPage::new, "demopagination");
    register("rows", DemoRowsPage::new, "demorows");
    register("stats", DemoStatsPage::new, "demostats");
    register("tabs", DemoTabsPage::new, "demotabs");
    register("toast", DemoToastPage::new, "demotoast");
    register("toolbar", DemoToolbarPage::new, "demotoolbar");
    register("utility", DemoUtilityPage::new, "demoutility");
  }

  private static void register(
      @Nonnull String primaryKey,
      @Nonnull Function<PlayerRef, BasicCustomUIPage> factory,
      @Nonnull String... aliases) {
    PAGES.put(primaryKey, factory);
    for (String alias : aliases) {
      PAGES.put(alias, factory);
    }
  }

  private final RequiredArg<String> pageArg;

  public DemoPageCommand() {
    super("demo", "Opens a demo UI page by name", false, (ref, store) -> new Object());
    this.pageArg = withRequiredArg("page", "Demo page name", ArgTypes.STRING);
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

    String pageName = context.get(pageArg);
    if (pageName == null || pageName.trim().isEmpty()) {
      context.sendMessage(Message.raw("Usage: /demo <page>. Available: " + availablePages()));
      return;
    }

    String key = pageName.trim().toLowerCase(Locale.ROOT);
    Function<PlayerRef, BasicCustomUIPage> factory = PAGES.get(key);
    if (factory == null) {
      context.sendMessage(
          Message.raw("Unknown demo page '" + pageName + "'. Available: " + availablePages()));
      return;
    }

    BasicCustomUIPage page = factory.apply(Objects.requireNonNull(playerRef, "playerRef"));
    player.getPageManager().openCustomPage(Objects.requireNonNull(ref, "ref"), store, page);
  }

  public static String availablePages() {
    return String.join(", ", Arrays.asList(PRIMARY_NAMES));
  }
}
