package com.github.idimabr.listeners;

import com.github.idimabr.customevents.ArmorChangeEvent;
import com.github.idimabr.customevents.PlayerChangeChunkEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerArmorListener implements Listener {

    @EventHandler
    public void onMoveChunk(InventoryClickEvent e){
        if(!e.getSlotType().equals(InventoryType.SlotType.ARMOR)) return;

        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if(item == null) return;

        PlayerInventory inventory = player.getInventory();

        ArmorChangeEvent event = new ArmorChangeEvent(player, item, inventory.getArmorContents());
        Bukkit.getServer().getPluginManager().callEvent(event);
        System.out.println("Chamou o evento ArmorChangeEvent");
        if(event.isCancelled())
            e.setCancelled(true);
    }
}
