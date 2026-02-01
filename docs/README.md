# Hytale Modding Template

This template keeps mod code and UI assets inside the plugin project:

- **plugin/**: Java plugin project (commands, UI pages, macros, and resources).

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
- Output jar lands in `plugin/build/libs/SharedInterfaces-<version>.jar`.
- For local testing, run `./gradlew installToServerMods` to copy the jar into `data/server/Server/mods/`.

## Local testing

- Run `./gradlew start` to install the plugin jar and launch `docker compose up` in the foreground so you can watch logs and interact.
- In-game, use Direct Connect to `127.0.0.1` to hit the locally running server.
- Stop with `Ctrl+C` once you're done testing; rerun `./gradlew start` after code or asset changes.

## Shared interfaces demo

- Plugin entrypoint is `plugin/src/main/java/MBRound18/hytale/shared/interfaces/InterfacesPlugin.java`.
- Demo commands: `/demo <page>`, `/dlist`, `/dhud <name|reset>`.
- Plugin metadata is in `plugin/src/main/resources/plugin.properties` and jar manifest entries are filled via Gradle.

## Common tasks

- Refresh server files: delete `data/server/Server/HytaleServer.jar` then rerun `gradle setup`.
- Refresh assets: delete `data/assets` and rerun `gradle setup` to re-unzip from `Assets.zip`.
- Update API docs: remove `data/unpacked` and rerun `gradle setup` to reclone.

## Acknowledgments

Special thanks to:

- **Ranork** - [Hytale-Server-Unpacked](https://github.com/Ranork/Hytale-Server-Unpacked) for decompiled server assets and API documentation
- **mbround18** - [mbround18/hytale](https://hub.docker.com/r/mbround18/hytale) Docker image for streamlined local development and testing
