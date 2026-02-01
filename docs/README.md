# Hytale Demo UI Library

This repo is a **demo UI pack** and **macro library** to help other teams build polished Hytale UIs faster. It includes:

- A curated macro system for common layout and widget patterns.
- Demo pages and HUDs that showcase how to compose those macros.
- A Java plugin that wires the demos to in-game commands.

**Tooling note:** These UI assets were built with the VS Code extension
[marketplace.visualstudio.com/items?itemName=MBRound18.hytale-ui-ultimate](https://marketplace.visualstudio.com/items?itemName=MBRound18.hytale-ui-ultimate).
We backfed the extension through extensive trial-and-error while building this library, and the macros represent the distilled best patterns we found.

## Prerequisites

- Docker and Docker Compose (v2 syntax).
- Java 25 installed (for Gradle wrapper).
- `unzip` available on PATH.

## Setup

Run the setup task from the repo root (wrapper downloads Gradle 8.7 automatically):

```sh
./gradlew setup
```

What `setup` does:

- Ensures `data/server`, `data/assets`, and `data/unpacked` exist and are owned by `1000:1000`.
- If `data/server/Server/HytaleServer.jar` is missing, starts `docker compose -f compose.yml up` and waits until the jar is produced, then tears the stack down.
- Unzips `data/server/Assets.zip` into `data/assets` (overwrites existing files).
- Unpacks `data/server/Server/HytaleServer.jar` into `data/unpacked` for local API browsing.

## Working in the template

- Place your Java plugin sources under `plugin/`. Configure your Gradle build there to produce the plugin jar.
- Place Hytale data that should be bundled (e.g., `manifest.json`, `Server/`, `Common/`) under `plugin/src/main/resources/` so it is included in the jar.
- The `data/` directory is for local provisioning only and is ignored from version control; it is recreated by `setup`.

## Build and distribution

- Run `./gradlew build` to produce the plugin jar.
- Output jar lands in `dist/SharedInterfaces-<version>.jar`.
- For local testing, run `./gradlew installToServerMods` (or `./gradlew build`) to copy the jar into `data/server/Server/mods/`.

## Local testing

- Run `./gradlew start` to install the plugin jar and launch `docker compose up` in the foreground so you can watch logs and interact.
- In-game, use Direct Connect to `127.0.0.1` to hit the locally running server.
- Stop with `Ctrl+C` once you're done testing; rerun `./gradlew start` after code or asset changes.

## Demo UI entrypoints

- Plugin entrypoint is in [plugin/src/main/java/MBRound18/hytale/shared/interfaces/InterfacesPlugin.java](../plugin/src/main/java/MBRound18/hytale/shared/interfaces/InterfacesPlugin.java).
- Demo commands: `/demo <page>`, `/dlist`, `/dhud <name|reset|list>`.
- Plugin metadata is in [plugin/src/main/resources/plugin.properties](../plugin/src/main/resources/plugin.properties) and jar manifest entries are filled via Gradle.

## UI macros: overview

The UI system is organized into reusable macro libraries under
[plugin/src/main/resources/Common/UI/Custom/Macros](../plugin/src/main/resources/Common/UI/Custom/Macros).
Each macro file defines named templates (prefixed with `@`) that you can invoke from any `.ui` page or HUD.

**Import pattern** (at the top of a `.ui` file):

```ui
$L = "../Macros/Layout.ui";
$I = "../Macros/Inputs.ui";
$R = "../Macros/Rows.ui";
$U = "../Macros/Utility.ui";
$T = "../Macros/Tabs.ui";
$G = "../Macros/Grid.ui";
$M = "../Macros/Modal.ui";
$B = "../Macros/Toolbar.ui";
$S = "../Macros/Stats.ui";
$Tt = "../Macros/Toast.ui";
$P = "../Macros/Pagination.ui";
$C = "../Common.ui";
```

**Invoke a macro** with `$Alias.@MacroName #InstanceId { ... }`. Override any `@Property` the macro defines.

```ui
$L.@Section #SettingsSection {
  @Title = "Settings";
}
```

### Macro library catalog

**Layout** — [Layout.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Layout.ui)

- `@PageFrame`: Centered frame with padding + background.
- `@Panel`: Generic padded panel.
- `@Section`: Labeled section with title + body slot.
- `@HeaderBar`: Title bar.
- `@Spacer`: Vertical spacing.
- `@Divider`: Hairline separator.
- `@ButtonRow`: Right-aligned action row.
- `@TwoColumn`: Split row with left/right groups.
- `@ScrollPanel`: Top-scrolling panel with custom scrollbar style.

**Inputs** — [Inputs.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Inputs.ui)

- `@SearchField`: Text field with placeholder.
- `@LabeledTextField`: Label + text input.
- `@LabeledNumberField`: Label + numeric input.

**Rows** — [Rows.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Rows.ui)

- `@AvatarRow`: Avatar + name + status.
- `@InfoRow`: Label + value.
- `@KeyValueRow`: Compact key/value row.
- `@ToggleRow`: Checkbox row.

**Utility** — [Utility.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Utility.ui)

- `@EmptyState`: Title + body empty-state block.
- `@Badge`: Small capsule badge.
- `@IconLabelRow`: Icon + text row.
- `@PillButton`: Tag-like button.

**Tabs** — [Tabs.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Tabs.ui)

- `@TabBar`: Tab container row.
- `@TabButton`: Tab button with active indicator.

**Grid** — [Grid.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Grid.ui)

- `@TileGrid`: Vertical grid container.
- `@TileRow`: Horizontal row of tiles.
- `@Tile`: Square tile with label.

**Modal** — [Modal.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Modal.ui)

- `@ModalShell`: Modal frame.
- `@ModalHeader`: Modal header title.
- `@ModalFooter`: Footer row for actions.

**Toolbar** — [Toolbar.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Toolbar.ui)

- `@Toolbar`: Left/right aligned toolbar containers.
- `@IconButton`: Square icon button.

**Stats** — [Stats.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Stats.ui)

- `@StatCard`: Stat tile with label + value.

**Toast** — [Toast.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Toast.ui)

- `@Toast`: Compact notification chip.

**Pagination** — [Pagination.ui](../plugin/src/main/resources/Common/UI/Custom/Macros/Pagination.ui)

- `@Pagination`: Page indicator with prev/next buttons.

## Demo pages (full screens)

Each demo page lives in [plugin/src/main/resources/Common/UI/Custom/Demos/Pages](../plugin/src/main/resources/Common/UI/Custom/Demos/Pages)
and is wired to a command in [DemoPageCommand.java](../plugin/src/main/java/MBRound18/hytale/shared/interfaces/commands/DemoPageCommand.java).

Run `./gradlew start` and use these in-game:

- `/demo grid` → DemoGrid
- `/demo inputs` → DemoInputs
- `/demo modal` → DemoModal
- `/demo pagination` → DemoPagination
- `/demo rows` → DemoRows
- `/demo stats` → DemoStats
- `/demo tabs` → DemoTabs
- `/demo toast` → DemoToast
- `/demo toolbar` → DemoToolbar
- `/demo utility` → DemoUtility

There’s also a complete showcase page at
[WidgetExamples.ui](../plugin/src/main/resources/Common/UI/Custom/Pages/WidgetExamples.ui) that combines all macro groups.

## Demo HUDs (in-world overlays)

HUDs live in [plugin/src/main/resources/Common/UI/Custom/Demos/Huds](../plugin/src/main/resources/Common/UI/Custom/Demos/Huds)
and are wired in [DemoHudCommand.java](../plugin/src/main/java/MBRound18/hytale/shared/interfaces/commands/DemoHudCommand.java).

Use these in-game:

- `/dhud widgetstrip` → compact toolbar + badges
- `/dhud objectives` → objectives list
- `/dhud partystatus` → party status panel
- `/dhud quickactions` → quick action buttons
- `/dhud stats` → vitals panel
- `/dhud reset` → clears the custom HUD
- `/dhud list` → prints available HUD names

## Usage patterns

### 1) Build a consistent page layout

```ui
$L = "../Macros/Layout.ui";
$I = "../Macros/Inputs.ui";

Group #MyPage {
  Anchor: (Full: 0);
  LayoutMode: Center;
  Background: #000000(0.55);

  $L.@PageFrame #Frame {
    @Width = 640;
    @Height = 420;

    $L.@HeaderBar #Header { @Text = "My Page"; }
    $L.@Spacer #Space1 { @Height = 8; }

    $L.@Section #Filters {
      @Title = "Filters";
      $I.@SearchField #Search { @Placeholder = "Search..."; }
    }
  }
}
```

### 2) Create reusable HUD strips

```ui
$L = "../../Macros/Layout.ui";
$B = "../../Macros/Toolbar.ui";

Group #HudStrip {
  Anchor: (Bottom: 40, Left: 40, Width: 300, Height: 90);
  LayoutMode: Top;
  Background: #1f2a36(0.85);
  Padding: (Full: 10);

  $L.@HeaderBar #Header { @Text = "Actions"; }
  $L.@Spacer #Space1 { @Height = 6; }

  $B.@Toolbar #Toolbar {
    Group #Left { $B.@IconButton #A { @Icon = #c6d5e5; } }
    Group #Right { $B.@IconButton #B { @Icon = #5fc4b6; } }
  }
}
```

### 3) Java wiring pattern

Pages and HUDs are thin Java wrappers that just point at a `.ui` file:

- Pages extend [AbstractDemoPage.java](../plugin/src/main/java/MBRound18/hytale/shared/interfaces/pages/demo/AbstractDemoPage.java)
- HUDs extend [AbstractCustomUIHud.java](../plugin/src/main/java/MBRound18/hytale/shared/interfaces/abstracts/AbstractCustomUIHud.java)

For interactive pages, use [AbstractInteractivePage.java](../plugin/src/main/java/MBRound18/hytale/shared/interfaces/abstracts/AbstractInteractivePage.java)
and override `build(...)` to attach UI events.

## Common tasks

- Refresh server files: delete `data/server/Server/HytaleServer.jar` then rerun `gradle setup`.
- Refresh assets: delete `data/assets` and rerun `gradle setup` to re-unzip from `Assets.zip`.
- Update API docs: remove `data/unpacked` and rerun `gradle setup` to reclone.

## Acknowledgments

Special thanks to:

- **mbround18** - [mbround18/hytale](https://hub.docker.com/r/mbround18/hytale) Docker image for streamlined local development and testing
