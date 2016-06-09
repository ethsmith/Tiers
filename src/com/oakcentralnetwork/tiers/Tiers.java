package com.oakcentralnetwork.tiers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Tiers extends JavaPlugin implements Listener {

	private ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

	public void onEnable() {

		getServer().getPluginManager().registerEvents(this, this);
		try {
			saveConfig();
			setupConfig(getConfig());
			saveConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Bukkit.getServer().getLogger().info("[Tiers] Enabled!");
	}

	public void onDisable() {
		Bukkit.getServer().getLogger().info("[Tiers] Disabled!");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("tiers")) {
			player.sendMessage(ChatColor.GREEN + "Opening GUI...");
			openGUI(player);
		} else if(cmd.getName().equalsIgnoreCase("tier")) {
			player.sendMessage(ChatColor.GREEN + "Opening GUI...");
			openGUI(player);
		}
		return true;
	}

	public void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Tiers");

		ItemStack tier1 = new ItemStack(Material.WOOD_SWORD);
		ItemMeta tier1Meta = tier1.getItemMeta();
		ItemStack tier2 = new ItemStack(Material.GOLD_SWORD);
		ItemMeta tier2Meta = tier2.getItemMeta();
		ItemStack tier3 = new ItemStack(Material.STONE_SWORD);
		ItemMeta tier3Meta = tier3.getItemMeta();
		ItemStack tier4 = new ItemStack(Material.IRON_SWORD);
		ItemMeta tier4Meta = tier4.getItemMeta();
		ItemStack tier5 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta tier5Meta = tier5.getItemMeta();

		tier1Meta.setDisplayName(ChatColor.BLUE + "Tier 1");
		tier1.setItemMeta(tier1Meta);
		tier2Meta.setDisplayName(ChatColor.BLUE + "Tier 2");
		tier2.setItemMeta(tier2Meta);
		tier3Meta.setDisplayName(ChatColor.BLUE + "Tier 3");
		tier3.setItemMeta(tier3Meta);
		tier4Meta.setDisplayName(ChatColor.BLUE + "Tier 4");
		tier4.setItemMeta(tier4Meta);
		tier5Meta.setDisplayName(ChatColor.BLUE + "Tier 5");
		tier5.setItemMeta(tier5Meta);

		inv.setItem(11, tier1);
		inv.setItem(12, tier2);
		inv.setItem(13, tier3);
		inv.setItem(14, tier4);
		inv.setItem(15, tier5);

		player.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Tiers")) {
			return;
		}

		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR
				|| !event.getCurrentItem().hasItemMeta()) {
			player.closeInventory();
			return;
		}

		switch (event.getCurrentItem().getType()) {
		case WOOD_SWORD:
			String[] commands = getConfig().getString("Tier1.Commands").split(",");
			for (String command : commands) {
				if (command != null) {
					Bukkit.dispatchCommand(console, command);
				}
			}
			player.closeInventory();
			break;
		case GOLD_SWORD:
			String[] commands2 = getConfig().getString("Tier2.Commands").split(",");
			for (String command : commands2) {
				if (command != null) {
					Bukkit.dispatchCommand(console, command);
				}
			}
			player.closeInventory();
			break;
		case STONE_SWORD:
			String[] commands3 = getConfig().getString("Tier3.Commands").split(",");
			for (String command : commands3) {
				if (command != null) {
					Bukkit.dispatchCommand(console, command);
				}
			}
			player.closeInventory();
			break;
		case IRON_SWORD:
			String[] commands4 = getConfig().getString("Tier4.Commands").split(",");
			for (String command : commands4) {
				if (command != null) {
					Bukkit.dispatchCommand(console, command);
				}
			}
			player.closeInventory();
			break;
		case DIAMOND_SWORD:
			String[] commands5 = getConfig().getString("Tier5.Commands").split(",");
			for (String command : commands5) {
				if (command != null) {
					Bukkit.dispatchCommand(console, command);
				}
			}
			player.closeInventory();
			break;
		default:
			player.closeInventory();
			break;
		}
	}

	private void setupConfig(FileConfiguration config) throws IOException {
		if (!new File(getDataFolder(), "RESET.FILE").exists()) {
			new File(getDataFolder(), "RESET.FILE").createNewFile();

			config.set("Tier1.Commands", "broadcast hi,broadcast hello");
			config.set("Tier2.Commands", "broadcast whats up,broadcast ethan was here");
			config.set("Tier3.Commands", "broadcast how are you,broadcast im doing good");
			config.set("Tier4.Commands", "broadcast hows life,broadcast meh");
			config.set("Tier5.Commands", "broadcast i like bukkit,broadcast me too");
			
			config.set("Tier1.Price", 5000);
			config.set("Tier2.Price", 15000);
			config.set("Tier3.Price", 35000);
			config.set("Tier4.Price", 75000);
			config.set("Tier5.Price", 125000);

			new File(getDataFolder(), "RESET.FILE").createNewFile();
		}
	}

	/**
	 * This commented out code is just in case it's requested.
	 */

	// @EventHandler
	// public void onPlayerJoin(PlayerJoinEvent event) {
	// event.getPlayer().getInventory().addItem(new
	// ItemStack(Material.COMPASS));
	// }
	//
	// @EventHandler
	// public void onPlayerInteract(PlayerInteractEvent event) {
	// Action a = event.getAction();
	// ItemStack is = event.getItem();
	//
	// if(a == Action.PHYSICAL || is == null || is.getType() == Material.AIR) {
	// return;
	// }
	//
	// if(is.getType() == Material.COMPASS) {
	// openGUI(event.getPlayer());
	// }
	// }
}