package com.github.idimabr.listeners;

import com.github.idimabr.customevents.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.stream.Collectors;

public class PlayerMoveToUPListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        final Player player = e.getPlayer();
        final Vector velocity = player.getVelocity();
        if (velocity.getY() <= 0) return;

        double jumpVelocity = 0.42F;
        if (player.hasPotionEffect(PotionEffectType.JUMP)) jumpVelocity += (double) ((float) getAmplifier(player) + 1) * 0.1F;

        if (player.getLocation().getBlock().getType() != Material.LADDER && Double.compare(velocity.getY(), jumpVelocity) == 0) {
            final PlayerJumpEvent event = new PlayerJumpEvent(player, velocity);
            Bukkit.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled()) e.setCancelled(true);
        }
    }

    private int getAmplifier(Player player){
        return player.getActivePotionEffects().stream().filter($ -> $.getType() == PotionEffectType.JUMP).collect(Collectors.toList()).get(0).getAmplifier();
    }
}
