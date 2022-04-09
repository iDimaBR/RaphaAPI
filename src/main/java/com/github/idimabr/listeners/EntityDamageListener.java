package com.github.idimabr.listeners;

import com.github.idimabr.customevents.EntityPreDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        Entity entity = e.getEntity();
        EntityDamageEvent.DamageCause cause = e.getCause();
        double damage = e.getDamage();
        if(!(entity instanceof LivingEntity)) return;

        LivingEntity livingEntity = (LivingEntity) entity;

        if(livingEntity.getHealth() - damage <= 0) {
            e.setCancelled(true);
            e.setDamage(0);

            EntityPreDeathEvent preDeath = new EntityPreDeathEvent(entity, cause);
            Bukkit.getServer().getPluginManager().callEvent(preDeath);
        }
    }
}
