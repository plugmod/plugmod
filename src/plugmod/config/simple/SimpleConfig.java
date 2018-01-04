package plugmod.config.simple;

import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import plugmod.PlugMod;
import plugmod.PlugModPlugin;
import plugmod.config.ConfigHandler;
import plugmod.util.java.FileUtils;

/***
 * Implementation of ConfigHandler to store a yaml configuration for a small PlugMod.
 * Each plugmod can store just one SimpleConfig which is saved in the plugmod directory under the plugmods name.
 * This implementation supports comments, but only when saving the config from defaults.
 * <br/><br/>
 * <em>Note: This config should be used as your main and only config for a small plugin.
 * If you require a lot of options or have more configs for your plugmod,
 * use either the bukkit's default implementation or {@link ComplexConfig} 
 * which can have a custom name and saves too to the plugmods own datafolder.</em>
 */
public class SimpleConfig extends ConfigHandler {
	
	private File defaultsFile;
	
	/**
	 * Constructs a new SimpleConfig handler.
	 * The default config should be saved in the root as .yml using the plugmod's name.
	 * @param plugmod
	 */
	public SimpleConfig(PlugMod plugmod) {
		this(plugmod, plugmod.getName() + ".yml");
	}
	
	/**
	 * Constructs a new SimpleConfig handler with custom defaultConfig path.
	 * @param plugmod
	 * @param defaultConfig
	 */
	public SimpleConfig(PlugMod plugmod, String defaultConfig) {
		super(new YamlConfiguration(), new File(PlugModPlugin.getInstance().getDataFolder(), plugmod.getName() + ".yml"));
		try {
			defaultsFile = new File(plugmod.getClass().getClassLoader().getResource(defaultConfig).toURI());
		} catch (URISyntaxException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Error defining defaults file: " + defaultConfig + " - malformed URI!", e);
		}
	}

	/**
	 * Further initializes the handler by reading the default config in from file. 
	 */
	@Override
	public void init() {
		setDefaultConfig(YamlConfiguration.loadConfiguration(defaultsFile));
	}
	
	/**
	 * Copies the default config file to the config file if the config wasn't there yet.
	 */
	@Override
	public void saveDefaults() {
		if (!file.exists()) {
			forceSaveDefaults();
		}
	}
	
	/**
	 * Overrides or creates config by directly copying the defaults file.
	 */
	public void forceSaveDefaults() {
		FileUtils.copyFile(defaultsFile, file);
	}
	
}
