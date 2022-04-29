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
    public void onDamage(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof LivingEntity)) return;

        LivingEntity entity = (LivingEntity) e.getEntity();
        if(!entity.hasMetadata("stacked")) return;
        if(entity.getMetadata("stacked").size() == 0) return;

        if( (entity.getHealth() - (e.getDamage())) <= 0){
            e.setCancelled(true);
            e.setDamage(0);

            EntityDamageEvent.DamageCause cause = e.getCause();
            EntityPreDeathEvent preDeath = new EntityPreDeathEvent(entity, cause);
            Bukkit.getServer().getPluginManager().callEvent(preDeath);
        }
    }
}
