package plugmod.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

/***
 * Simple class to wrap and handle configs.
 * Class is commonly used by creating instance of either {@link SimpleConfig} or {@link ComplexConfig},
 * but can be used on its own or easily extended to provide further management of configuration files.
 */
public class ConfigHandler {
	
	protected FileConfiguration config;
	protected File file;
	
	/**
	 * Constructs a new ConfigHandler using 
	 * a configuration implementation (can be empty, but not null)
	 * and a file to read and write to.
	 * @param config
	 * @param file
	 */
	public ConfigHandler(FileConfiguration config, File file) {
		setFile(file);
		setConfig(config);
	}
	
	/**
	 * Gets the wrapped abstract of the file configuration.
	 * @return the wrapped FileConfiguration.
	 */
	public FileConfiguration getConfig() {
		return config;
	}

	/**
	 * Sets the wrapped config.
	 * @param config
	 */
	public void setConfig(FileConfiguration config) {
		this.config = config;
	}

	/**
	 * Gets the interface of the default configuration belonging to config.
	 * @return the default Configuration inside config.
	 */
	public Configuration getDefaultConfig() {
		return config.getDefaults();
	}

	/**
	 * Sets the defaults of the config to the specified default Configuration.
	 * @param defaultConfig
	 */
	public void setDefaultConfig(Configuration defaultConfig) {
		config.setDefaults(defaultConfig);
	}

	/**
	 * Gets the file where the config is read from and written to.
	 * @return the stored File.
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * Sets the file where the config is read from and written to.
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Convenience method to setup the config, 
	 * store the defaults if the file was not present
	 * and read in the config from file.
	 */
	public void setup() {
		init();
		saveDefaults();
		reload();
	}
	
	/**
	 * Further sets the config up.
	 * This function should be called when loading.
	 */
	public void init() { };
	
	/**
	 * Saves the defaultConfig to file. 
	 * <br/><br/>
	 * <em>Note: this implementation works by temporarily forcing options().copyDefaults() to true
	 * and then saving the config. Options inside the config will also be stored and all comments
	 * from the defaultConfig, except the header, are lost.</em> 
	 */
	public void saveDefaults() {
		if (!file.exists()) {
			boolean saveDefaults = config.options().copyDefaults();
			config.options().copyDefaults(true);
			save();
			config.options().copyDefaults(saveDefaults);
		}
	}
	
	/**
	 * Reloads (reads) the config from file.
	 */
	public void reload() {
		try {
			config.load(file);
		} catch (FileNotFoundException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Config " + file.getName() + " not found!", e);
		}  catch (InvalidConfigurationException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Config " + file.getName() + " is not valid!", e);
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Error reading " + file.getName() + ".", e);
		}
	}
	
	/**
	 * Saves the config to file.
	 */
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Error writing " + file.getName() + ".", e);
		}
	}
	
}
