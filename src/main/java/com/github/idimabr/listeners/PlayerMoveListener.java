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
    public void onMone(PlayerMoveEvent e){
        Player player = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();

        if(from.getChunk() == to.getChunk()) return;

        PlayerChangeChunkEvent chunkEvent = new PlayerChangeChunkEvent(player, from, to);
        Bukkit.getServer().getPluginManager().callEvent(chunkEvent);
        if(chunkEvent.isCancelled())
            e.setCancelled(true);
    }
}
