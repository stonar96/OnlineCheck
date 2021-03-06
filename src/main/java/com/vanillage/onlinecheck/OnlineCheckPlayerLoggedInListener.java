package com.vanillage.onlinecheck;

import com.vanillage.minecraftalphaserver.event.PlayerLoggedInEvent;
import com.vanillage.minecraftalphaserver.event.PlayerLoggedInListener;

public final class OnlineCheckPlayerLoggedInListener extends PlayerLoggedInListener {
    private final OnlineCheck plugin;
    private String kickReason;

    public OnlineCheckPlayerLoggedInListener(OnlineCheck plugin) {
        this(plugin, -10, null);
    }

    public OnlineCheckPlayerLoggedInListener(OnlineCheck plugin, int priority) {
        this(plugin, priority, null);
    }

    public OnlineCheckPlayerLoggedInListener(OnlineCheck plugin, String kickReason) {
        this(plugin, -10, kickReason);
    }

    public OnlineCheckPlayerLoggedInListener(OnlineCheck plugin, int priority, String kickReason) {
        super(priority);

        if (plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }

        this.plugin = plugin;
        this.kickReason = kickReason == null ? "<username> is already online" : kickReason;
    }

    public OnlineCheck getPlugin() {
        return plugin;
    }

    public String getKickReason() {
        return kickReason;
    }

    public void setKickReason(String kickReason) {
        if (kickReason == null) {
            throw new IllegalArgumentException("kickReason cannot be null");
        }

        this.kickReason = kickReason;
    }

    @Override
    protected void onEvent(PlayerLoggedInEvent event) {
        if (!event.isDenied() && plugin.getMinecraftServer().configManager.getPlayerEntity(event.getPacket().username) != null) {
            event.deny();
            event.setKickReason(kickReason.replace("<username>", event.getPacket().username));
        }
    }
}
