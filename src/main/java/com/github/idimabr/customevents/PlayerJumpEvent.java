package com.github.idimabr.customevents;

import com.github.idimabr.customevents.base.BaseEventCancellable;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;

public class PlayerJumpEvent extends BaseEventCancellable {

    private final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private Vector velocity;
    private boolean cancelled;

    public PlayerJumpEvent(Player player, Vector velocity) {
        this.player = player;
        this.velocity = velocity;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Vector getVelocity(){
        return this.velocity;
    }

    public Block getBlock(){
        return player.getWorld().getBlockAt(this.velocity.getBlockX(),this.velocity.getBlockY(),this.velocity.getBlockZ());
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
