package com.github.idimabr.listeners;

import com.github.idimabr.customevents.ArmorChangeEvent;
import com.github.idimabr.enums.ChangeType;
import com.github.idimabr.enums.ArmorType;
import net.minecraft.server.v1_8_R3.ItemArmor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerArmorListener implements Listener {

    @EventHandler
    public void onClickArmorInventory(InventoryClickEvent e){
        if(!e.getSlotType().equals(InventoryType.SlotType.ARMOR)) return;
        final Player player = (Player) e.getWhoClicked();
        final ItemStack item = e.getCurrentItem();
        if(item == null) return;

        final PlayerInventory inventory = player.getInventory();
        final ArmorChangeEvent event = new ArmorChangeEvent(player, item, inventory.getArmorContents(), ChangeType.INVENTORY);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled()) e.setCancelled(true);
    }

    @EventHandler
    public void onClickArmorHand(PlayerInteractEvent e){
        final Player player = e.getPlayer();
        final ItemStack item = e.getItem();
        if(!e.getAction().toString().contains("RIGHT_CLICK")) return;
        if(item == null) return;
        if(!isArmor(item)) return;
        if(!haveSpace(player, item)) return;

        final PlayerInventory inventory = player.getInventory();

        final ArmorChangeEvent event = new ArmorChangeEvent(player, item, inventory.getArmorContents(), ChangeType.HAND);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled()) e.setCancelled(true);
    }

    private boolean haveSpace(Player player, ItemStack item){
        final ArmorType type = ArmorType.valueOf(item.getType().name().split("_")[1]);
        final ItemStack slotItem = player.getInventory().getArmorContents()[type.getId()];
        return slotItem == null || slotItem.getType() == Material.AIR;
    }

    private boolean isArmor(ItemStack item){
        return item.getItemMeta() instanceof ItemArmor;
    }

}
