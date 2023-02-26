package com.github.idimabr.listeners;

import com.github.idimabr.customevents.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveToUPListener implements Listener {

    @EventHandler
    public void onJump(PlayerMoveEvent e){
        final Player player = e.getPlayer();
        final Location from = e.getFrom();
        final Location to = e.getTo();

        if(from.getBlockY() == to.getBlockY()) return;
        if(from.getBlockY() > to.getBlockY()) return;

        final PlayerJumpEvent event = new PlayerJumpEvent(player, from, to);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
            e.setCancelled(true);
    }
}
