package com.ruinscraft.deathmessages;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathMessagesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
    }

}
