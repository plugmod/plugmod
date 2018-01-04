package plugmod.config.complex;

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
 * Implementation of ConfigHandler to store a yaml configuration for a more complex PlugMod.
 * By creating instances of this class you can manage as many configs as you like,
 * including the default config.yml up to custom relative paths.
 * <br/><br/>
 * <em>Note: if you have a plugmod with just one small config file, it is recommended
 * to instead use {@link SimpleConfig} and store to the PlugMod directory.</em>
 */
public class ComplexConfig extends ConfigHandler {

	private File defaultsFile;
	
	/**
	 * Constructs a new ComplexConfig handler with the default values.
	 * The default config should be saved in the root as {@code config.yml}
	 * and is saved too to the config.yml in the plugmod's datafolder.
	 * @param plugmod
	 */
	public ComplexConfig(PlugMod plugmod) {
		this(plugmod, "config.yml", "config.yml");
	}
	
	/**
	 * Constructs a new ComplexConfig handler for a custom config file
	 * with the specified name. The name should end in .yml and the defaults
	 * should be stored under the same name inside the root folder.
	 * @param plugmod
	 * @param config the name of the config (Eg: "ranks.yml")
	 */
	public ComplexConfig(PlugMod plugmod, String config) {
		this(plugmod, config, config);
	}
	
	/**
	 * Constructs a new ComplexConfig handler with custom defaultConfig path and destination path.
	 * The defaultPath is internal in your plugin and the destinationPath is relative to the
	 * plugmod's datafolder. Both should include the config name, ending in {@code .yml}.
	 * @param plugmod
	 * @param defaultPath
	 * @param destinationPath
	 */
	public ComplexConfig(PlugMod plugmod, String defaultPath, String destinationPath) {
		super(new YamlConfiguration(), new File(PlugModPlugin.getInstance().getDataFolder(), destinationPath));
		try {
			defaultsFile = new File(plugmod.getClass().getClassLoader().getResource(defaultPath).toURI());
		} catch (URISyntaxException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Error defining defaults file: " + defaultPath + " - malformed URI!", e);
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
