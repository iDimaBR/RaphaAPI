package com.github.idimabr.customevents;

import com.github.idimabr.customevents.base.BaseEventCancellable;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityPreDeathEvent extends BaseEventCancellable {

    private final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;
    private Entity entity;
    private EntityType type;
    private Location location;
    private final EntityDamageEvent.DamageCause cause;

    public EntityPreDeathEvent(Entity entity, EntityDamageEvent.DamageCause cause) {
        this.cancelled = false;
        this.entity = entity;
        this.type = entity.getType();
        this.location = entity.getLocation();
        this.cause = cause;
    }

    public EntityDamageEvent.DamageCause getCause() {
        return cause;
    }

    public Entity getEntity() {
        return entity;
    }

    public EntityType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HandlerList getHandlerList() {
        return HANDLERS;
    }
}
