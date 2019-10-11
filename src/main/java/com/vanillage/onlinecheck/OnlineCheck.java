package com.vanillage.onlinecheck;

import com.vanillage.minecraftalphaserver.event.PlayerLoggedInListener;
import com.vanillage.minecraftalphaserver.plugin.Plugin;

public final class OnlineCheck extends Plugin {
    @Override
    protected void onEnable() {
        getMinecraftServer().getPluginManager().getEventManager().registerListener((PlayerLoggedInListener) new OnlineCheckPlayerLoggedInListener(this));
        getMinecraftServer().log(getName() + " enabled");
    }

    @Override
    protected void onDisable() {
        getMinecraftServer().log(getName() + " disabled");
    }
}
