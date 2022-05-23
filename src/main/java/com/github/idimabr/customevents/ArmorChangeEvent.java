package com.github.idimabr.customevents;

import com.github.idimabr.customevents.base.BaseEventCancellable;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ArmorChangeEvent extends BaseEventCancellable {

    private final HandlerList HANDLERS = new HandlerList();
    private Player player;

    private ItemStack item;

    private ItemStack[] armor;
    private boolean cancelled;

    public ArmorChangeEvent(Player player, ItemStack item, ItemStack[] armor) {
        this.player = player;
        this.item = item;
        this.armor = armor;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer(){
        return player;
    }

    public ItemStack getChangedArmor(){
        return item;
    }

    public ItemStack[] getArmorContents(){
        return armor;
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
