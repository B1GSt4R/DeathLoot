package de.B1GSt4R.Main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class system extends JavaPlugin {
	
	public File itemFile = new File("plugins/"+this.getDescription().getName(), "PlayerInvs.yml");
	public YamlConfiguration itemCfg = YamlConfiguration.loadConfiguration(itemFile);
	
	@Override
	public void onEnable() {
		this.getServer().getConsoleSender().sendMessage("§aDeathLoot Plugin Loaded by B1GSt4R");
		
		new de.B1GSt4R.Listener.deathEvent(this);
		
		getCommand("rev").setExecutor(new de.B1GSt4R.Commands.AdminCommands(this));
	}
	
	@Override
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage("§cDeathLoot Plugin Unloaded by B1GSt4R");
	}
	
	public void loadCfg() {
		try {
			itemCfg.load(itemFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void saveCfg() {
		try {
			itemCfg.save(itemFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
