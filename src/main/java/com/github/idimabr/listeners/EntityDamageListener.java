package com.github.idimabr.listeners;

import com.github.idimabr.customevents.EntityPreDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof LivingEntity)) return;
        final LivingEntity entity = (LivingEntity) e.getEntity();

        if( (entity.getHealth() - (e.getDamage())) <= 0){
            final EntityDamageEvent.DamageCause cause = e.getCause();
            final EntityPreDeathEvent event = new EntityPreDeathEvent(entity, cause);
            Bukkit.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled()) e.setDamage(0);
        }
    }
}
