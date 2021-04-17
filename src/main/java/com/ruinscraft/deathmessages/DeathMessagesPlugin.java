package com.ruinscraft.deathmessages;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class DeathMessagesPlugin extends JavaPlugin {

    public static String CAN_BEHEAD;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DeathListener(), this);

        saveDefaultConfig();

        this.CAN_BEHEAD = this.getConfig().getString("beheadPermission");
        BadWords.loadWords(this.getConfig().getStringList("badwords"));
    }

}
