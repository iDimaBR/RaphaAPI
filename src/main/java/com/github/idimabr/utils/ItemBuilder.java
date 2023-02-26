package com.github.idimabr.utils;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ItemBuilder {

	private ItemStack item;
	private final Map<String, ItemMeta> cacheSkull = Maps.newHashMap();

	public ItemBuilder(Material material) {
		this(material, 1);
	}

	public ItemBuilder(String material){
		final boolean skull = material.startsWith("eyJ0");
		item = new ItemStack(skull ? Material.SKULL_ITEM : Material.valueOf(material), 1);
		if(skull){
			item.setDurability((short) 3);
			setSkull(material);
		}
	}

	public ItemBuilder(ItemStack item) {
		this.item = item;
	}

	public ItemBuilder(Material material, int amount) {
		item = new ItemStack(material, amount);
	}

	public ItemBuilder(Material material, int amount, byte durability) {
		item = new ItemStack(material, amount, durability);
	}

	public ItemBuilder clone() {
		return new ItemBuilder(item);
	}

	public ItemBuilder setDurability(short durability) {
		item.setDurability(durability);
		return this;
	}


	public ItemBuilder setNBT(String key, String value){
		final NBTItem nbt = new NBTItem(item);
		nbt.setString(key, value);
		item = nbt.getItem();
		return this;
	}

	public ItemBuilder setNBTKey(String key){
		final NBTItem nbt = new NBTItem(item);
		nbt.setString(key, "");
		item = nbt.getItem();
		return this;
	}

	public ItemBuilder setPotion(PotionEffectType type, int duration, int amplifier) {
		final PotionMeta meta = (PotionMeta) item.getItemMeta();
		meta.addCustomEffect(new PotionEffect(type, duration, amplifier), true);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setName(String nome) {
		final ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder removeEnchant(Enchantment enchant) {
		item.removeEnchantment(enchant);
		return this;
	}

	public ItemBuilder setSkullOwner(String owner) {
		final ItemMeta meta = item.getItemMeta();
		if(!(meta instanceof SkullMeta)) return this;

		final SkullMeta skullMeta = (SkullMeta) meta;
		skullMeta.setOwner(owner);
		item.setItemMeta(skullMeta);
		item.setDurability((short) 3);
		return this;
	}

	public ItemBuilder addEnchant(Enchantment enchant, int level, boolean unsafe) {
		if(unsafe){
			item.addUnsafeEnchantment(enchant, level);
			return this;
		}

		final ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchant, level, true);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder addEnchant(Enchantment enchant, int level) {
		final ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchant, level, true);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setAmount(int amount){
		item.setAmount(amount);
		return this;
	}

	public ItemBuilder addEnchantmentMap(Map<Enchantment, Integer> enchantments) {
		item.addEnchantments(enchantments);
		return this;
	}

	public ItemBuilder setMaxDurability() {
		item.setDurability(Short.MAX_VALUE);
		return this;

	}

	public ItemBuilder addItemFlag(ItemFlag flag) {
		item.getItemMeta().addItemFlags(flag);
		return this;
	}

	public ItemBuilder setLore(String... lore) {
		final ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder replaceLore(String key, String value){
		final ItemMeta meta = item.getItemMeta();
		final List<String> lore = meta.getLore()
				.stream()
				.map($ -> $.replace(key, value))
				.collect(Collectors.toList());
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		final ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setLoreIf(boolean condition, String... lore) {
		if(!condition) return this;
		setLore(lore);
		return this;
	}

	public ItemBuilder setLoreIf(boolean condition, List<String> lore) {
		if(!condition) return this;
		setLore(lore);
		return this;
	}

	public ItemBuilder removeLoreLine(String line) {
		final ItemMeta meta = item.getItemMeta();
		if(!meta.hasLore()) return this;

		List<String> lore = new ArrayList<>(meta.getLore());
		if (!lore.contains(line)) return this;

		lore.remove(line);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder removeLoreLine(int index) {
		final ItemMeta meta = item.getItemMeta();
		if(!meta.hasLore()) return this;

		final List<String> lore = new ArrayList<>(meta.getLore());
		if (index < 0 || index > lore.size()) return this;

		lore.remove(index);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder addLoreIf(boolean condition, String newLine) {
		if(!condition) return this;
		addLoreLine(newLine);
		return this;
	}

	public ItemBuilder addLoreLine(String newLine) {
		final ItemMeta meta = item.getItemMeta();
		if(!meta.hasLore()) return this;

		List<String> lore = new ArrayList<>();
		if (meta.hasLore()) lore = new ArrayList<>(meta.getLore());
		lore.add(newLine);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder addLoreLine(int pos, String string) {
		final ItemMeta meta = item.getItemMeta();
		final List<String> lore = new ArrayList<>(meta.getLore());
		lore.set(pos, string);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	@Deprecated
	public ItemBuilder setDyeColor(DyeColor cor) {
		this.item.setDurability(cor.getDyeData());
		return this;
	}

	@Deprecated
	public ItemBuilder setWoolColor(DyeColor cor) {
		if (!item.getType().toString().contains("WOOL")) return this;
		this.item.setDurability(cor.getWoolData());
		return this;
	}

	public ItemBuilder setLeatherArmorColor(Color color) {
		if(!(item.getItemMeta() instanceof LeatherArmorMeta)) return this;

		final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(color);
		item.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setSkull(String url){
		final SkullMeta headMeta = (SkullMeta) item.getItemMeta();
		if(cacheSkull.containsKey(url)){
			item.setItemMeta(cacheSkull.get(url));
			return this;
		}

		final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", url));
		final Field profileField;
		try {
			profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ignored) {
			return this;
		}
		item.setItemMeta(headMeta);
		cacheSkull.put(url, headMeta);
		return this;
	}

	public ItemStack build() {
		return item;
	}

    public ItemBuilder removeLastLoreLine() {
		final ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>(meta.getLore());
		lore.remove(lore.size()-1);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
    }
}