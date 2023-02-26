package com.github.idimabr.customevents;

import com.github.idimabr.customevents.base.BaseEventCancellable;
import com.github.idimabr.customevents.enums.ChangeType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ArmorChangeEvent extends BaseEventCancellable {

    private final HandlerList HANDLERS = new HandlerList();
    private Player player;

    private ItemStack item;

    private ChangeType type;

    private ItemStack[] armor;
    private boolean cancelled;

    public ArmorChangeEvent(Player player, ItemStack item, ItemStack[] armor, ChangeType type) {
        this.player = player;
        this.item = item;
        this.armor = armor;
        this.type = type;
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

    public ChangeType getChangeType() {
        return type;
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
