package com.github.idimabr.listeners;

import com.github.idimabr.customevents.PlayerChangeChunkEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMoveChunk(PlayerMoveEvent e){
        Player player = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();

        if(from.getChunk() == to.getChunk()) return;

        PlayerChangeChunkEvent event = new PlayerChangeChunkEvent(player, from, to);
        Bukkit.getServer().getPluginManager().callEvent(event);
        System.out.println("Chamou o evento PlayerChangeChunkEvent");
        if(event.isCancelled())
            e.setCancelled(true);
    }
}
