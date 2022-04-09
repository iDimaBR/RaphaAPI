package com.github.idimabr.customevents;

import com.github.idimabr.customevents.base.BaseEventCancellable;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChangeChunkEvent extends BaseEventCancellable {

    private final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private Location from;
    private Location to;
    private Chunk toChunk;
    private Chunk fromChunk;
    private boolean cancelled;

    public PlayerChangeChunkEvent(Player player, Location from, Location to) {
        this.player = player;
        this.from = from;
        this.to = to;
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

    public Location getFrom(){
        return this.from;
    }

    public Location getTo(){
        return this.to;
    }

    public Block getBlockFrom(){
        return this.from.getBlock();
    }

    public Block getBlockTo(){
        return this.to.getBlock();
    }

    public Chunk getFromChunk() {
        return this.fromChunk;
    }

    public Chunk getToChunk() {
        return this.toChunk;
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
