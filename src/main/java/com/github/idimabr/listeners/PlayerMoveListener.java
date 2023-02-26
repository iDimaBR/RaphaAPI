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
        final Player player = e.getPlayer();
        final Location from = e.getFrom();
        final Location to = e.getTo();
        if(from.getChunk() == to.getChunk()) return;

        final PlayerChangeChunkEvent event = new PlayerChangeChunkEvent(player, from, to);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled()) e.setCancelled(true);
    }
}
