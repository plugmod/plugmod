package plugmod;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import plugmod.config.ConfigHandler;
import plugmod.config.simple.SimpleConfig;
import plugmod.dataplace.DataPlace;
import plugmod.dataplace.impl.DBConfig;
import plugmod.dataplace.impl.DBConfig.DBRemoteConfig;
import plugmod.dataplace.impl.DBType;

public class PlugModPlugin extends PlugMod {

	//Make static reference.
	private static PlugModPlugin INSTANCE;
	public static PlugModPlugin getInstance() {
		return INSTANCE;
	}
	
	public ConfigHandler plugmodConfig;
	public FileConfiguration config;
	
	public PlugModPlugin() {
		INSTANCE = this;
		plugmodConfig = new SimpleConfig(this);
	}
	
	@Override
	public void onLoad() {
		plugmodConfig.setup();
		config = plugmodConfig.getConfig();
		DataPlace.getInstance().init(new DBConfig(
				DBType.valueOf(config.getString("DataStore.type").toUpperCase()),
				config.getString("DataStore.database"),
				new DBRemoteConfig(
						config.getString("DataStore.host"),
						config.getInt("DataStore.port"),
						config.getString("DataStore.username"),
						config.getString("DataStore.password")
				)
			)
		);
	}
	
	@Override
	public void onEnable() {
		plugmodConfig.getConfig().set("Item", new ItemStack(Material.GRASS));
		Bukkit.getLogger().log(Level.INFO, "Database test: " + plugmodConfig.getConfig().getString("DataStore.type"));
		plugmodConfig.getConfig().getValues(true).forEach((key, value) -> {
			System.out.println(key + ": " + value);
		});
		plugmodConfig.getConfig().getValues(false).forEach((key, value) -> {
			if(value instanceof MemorySection) {
				System.out.println(key + ": ");
				((MemorySection) value).getValues(false).forEach((key1, value1) -> {
					if(value1 instanceof MemorySection) {
						System.out.println(" " + key1 + ": ");
						((MemorySection) value1).getValues(true).forEach((key2, value2) -> {
							System.out.println("  " + key2 + ": " + value2 + " - " + value2.getClass());
						});
					} else {
						System.out.println(" " + key1 + ": " + value1 + " - " + value1.getClass());
					}
				});
			} else {
				System.out.println(key + ": " + value + " - " + value.getClass());
			}
		});
	}
	
	@Override
	public void onDisable() {
		
	}

	@Override
	public void onStartup() { }

	@Override
	public void onShutdown() { }
	
}
