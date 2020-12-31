package de.B1GSt4R.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.B1GSt4R.Main.system;

public class AdminCommands implements CommandExecutor {

	private de.B1GSt4R.Main.system plugin;
	public AdminCommands(system system) {
		this.plugin = system;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(target == null) {
					p.sendMessage("§cDer Spieler §6" + args[0] + "§c wurde nicht gefunden!");
					return true;
				}
				boolean success = revPlayer(target);
				if(success) {
					p.sendMessage("§aDer Spieler §6" + target.getDisplayName() + " §ahat seine Items wieder erhalten!");
				}else {
					p.sendMessage("§cDer Spieler §6" + target.getDisplayName() + " §chat keine Items!");
				}
				return true;
			}else {
				if(target == null) {
					plugin.getServer().getConsoleSender().sendMessage("§cDer Spieler §6" + args[0] + "§c wurde nicht gefunden!");
					return true;
				}
				boolean success = revPlayer(target);
				if(success) {
					plugin.getServer().getConsoleSender().sendMessage("§aDer Spieler §6" + target.getDisplayName() + " §ahat seine Items wieder erhalten!");
				}else {
					plugin.getServer().getConsoleSender().sendMessage("§cDer Spieler §6" + target.getDisplayName() + " §chat keine Items!");
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean revPlayer(Player target) {
		ArrayList<ItemStack> inventory = (ArrayList<ItemStack>) plugin.itemCfg.get("User." + target.getUniqueId() + ".Inventory");
		if(inventory == null || inventory.size() == 0) {
			return false;
		}
		for (int i = 0; i < 41; i++) {
			if(inventory.get(i) != null) {
				target.getInventory().setItem(i, inventory.get(i));
			}
		}
		plugin.itemCfg.set("User." + target.getUniqueId() + ".Inventory", null);
		plugin.saveCfg();
		return true;
	}
}
