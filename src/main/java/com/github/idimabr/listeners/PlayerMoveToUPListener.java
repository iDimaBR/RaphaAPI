package com.github.idimabr.listeners;

import com.github.idimabr.customevents.PlayerChangeChunkEvent;
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
        Player player = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();

        if(from.getBlockY() == to.getBlockY()) return;
        if(from.getBlockY() > to.getBlockY()) return;

        PlayerJumpEvent event = new PlayerJumpEvent(player, from, to);
        Bukkit.getServer().getPluginManager().callEvent(event);
        System.out.println("Chamou o evento PlayerJumpEvent");
        if(event.isCancelled())
            e.setCancelled(true);
    }
}
