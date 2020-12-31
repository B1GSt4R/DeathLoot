package de.B1GSt4R.Listener;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.B1GSt4R.Main.system;

public class deathEvent implements Listener {

	private de.B1GSt4R.Main.system plugin;
	public deathEvent(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		Inventory inv = p.getInventory();
		ArrayList<ItemStack> inventory = new ArrayList<ItemStack>();
		for (ItemStack item : inv) {
			inventory.add(item);
		}
		plugin.itemCfg.set("User." + p.getUniqueId() + ".Inventory", inventory);
		plugin.saveCfg();
	}
}
