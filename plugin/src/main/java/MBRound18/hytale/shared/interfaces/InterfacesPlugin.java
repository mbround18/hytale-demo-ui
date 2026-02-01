package MBRound18.hytale.shared.interfaces;

import java.util.logging.Level;
import java.util.Objects;

import javax.annotation.Nonnull;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import MBRound18.hytale.shared.interfaces.commands.DemoPageCommand;
import MBRound18.hytale.shared.interfaces.commands.DemoListCommand;
import MBRound18.hytale.shared.interfaces.commands.DemoHudCommand;

public class InterfacesPlugin extends JavaPlugin {
  public InterfacesPlugin(@Nonnull JavaPluginInit init) {
    super(init);
  }

  @Override
  protected void setup() {
    Level info = Objects.requireNonNull(Level.INFO, "Level.INFO");
    getLogger().at(info).log("InterfacesPlugin setup complete");

    getCommandRegistry().registerCommand(new DemoPageCommand());
    getCommandRegistry().registerCommand(new DemoListCommand());
    getCommandRegistry().registerCommand(new DemoHudCommand());

    getLogger().at(info).log("InterfacesPlugin commands registered\n/demo <page>, /dlist, /dhud <name|reset> to test!");
  }

}
