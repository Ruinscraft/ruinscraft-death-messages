package com.ruinscraft.deathmessages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    // https://minecraft.gamepedia.com/Death_messages
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        String message = event.getDeathMessage();

        // "using [item]"
        if (message.contains("using [")) {
            String itemPart = message.substring(message.lastIndexOf("using ["), message.length() - 1);
            if (BadWords.badWordsFound(itemPart)) {
                int usingIndex = message.indexOf("using [");
                message = message.substring(0, usingIndex - 1);
            }
        }

        // if the death was the result of a Player killer
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();

            // check for player heads integration
            if (killer.hasPermission(DeathMessagesPlugin.CAN_BEHEAD)) {
                message = adaptMessageToPlayerHeads(message);
            }
        }

        // color it red
        message = ChatColor.RED + message;

        event.setDeathMessage(message);
    }

    private String adaptMessageToPlayerHeads(String message) {
        if (message.contains(" was ")) {
            String[] parts = message.split(" was ");
            return parts[0] + " was beheaded and " + parts[1];
        }

        return message;
    }

}
