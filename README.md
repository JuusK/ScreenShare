# ScreenShare
**Advanced Freeze & Screen-share Control Plugin for Minecraft**

ScreenShare is a configurable Minecraft plugin designed to screen-share players.

## Core Features

### Control Modules
*   **Freeze System:** Freeze players instantly to prevent movement, interaction, or commands.
*   **Automated Teleportation:** Automatically teleports frozen players to a designated investigation area and returns them upon release.
*   **Command Filtering:** Dynamically restricts players' commands while frozen, allowing only essential communication (e.g., /msg, /site) as defined in `config.yml`.
*   **Title/Subtitle Alerts:** Sends clear, visual warnings to frozen players to ensure they are aware of the situation.

### Interaction Features
*   **Anti-Escape (Leave = Ban) System:** Automatically executes configurable actions (like banning) if a player disconnects while frozen.
*   **Punishment Compatibility:** Designed to work with standard punishment plugins (LiteBans, EssentialsX, etc.) for timed bans.
*   **Flexible Teleportation:** Configurable locations for both freeze and unfreeze events.

## Getting Started

### Prerequisites
- **Java Development Kit (JDK) 21 or higher.** 
  > [!IMPORTANT]
  > This plugin is built with JDK 21 features and will **not** work on lower Java versions.
- A Minecraft Server running version 1.21.x.
- (Optional) A punishment plugin (e.g., LiteBans, EssentialsX) for timed ban support.

### Installation
1.  Download the `ScreenShare.jar`.
2.  Place it into your server's `plugins/` folder.
3.  Reload your server.(If won't work, restart your server)
4.  Configure the features in `config.yml`.

## Commands & Permissions

| Command | Description | Aliases |
| :--- | :--- | :--- |
| `/ssfreeze <player>` | Freezes a player for investigation (For Turkish: `/kontçek`) |
| `/ssunfreeze <player>` | Releases a frozen player (For Turkish: `/kontbırak`) |

**Permission:** `screenshare.*` (Required for both commands)

## Support & Feedback
If you encountered any bugs or have feature suggestions, feel free to reach out:

- **Discord:** `mandalinasslee`
