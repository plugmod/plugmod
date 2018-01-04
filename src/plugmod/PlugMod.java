package plugmod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class PlugMod extends JavaPlugin {
	
	//=====================================================\\
	//                  	  Static    	               \\
	//=====================================================\\
	
	private static Map<String, PlugMod> plugmods = new ConcurrentHashMap<>();
	
	/***
	 * Registers a PlugMod.
	 * This is automatically done if onEnable is not overridden in your PlugMod.
	 * @param plugmod a JavaPlugMod
	 */
	public static void registerPlugMod(PlugMod plugmod) {
		plugmods.put(plugmod.getName(), plugmod);
	}
	
	/***
	 * Checks if the server has a certain PlugMod.
	 * @param name the name of the plugmod
	 * @return true, if the server has plugmod name.
	 */
	public static boolean hasPlugMod(String name) {
		return plugmods.containsKey(name);
	}
	
	/***
	 * Gets a PlugMod based on given name.
	 * @param name
	 * @return the registered PlugMod belonging to name.
	 */
	public static PlugMod getPlugMod(String name) {
		return plugmods.get(name);
	}
	
	//=====================================================\\
	//                  Startup / Shutdown                 \\
	//=====================================================\\
	// This section overrules the bukkit enable / disable functions to add PlugMod capabilities.
	@Override
	public void onLoad() {
		onSetup();
	}
	
	@Override
	public void onEnable() {
		PlugMod.registerPlugMod(this);
		onStartup();
	}
	
	@Override
	public void onDisable() {
		onShutdown();
	}
	
	public void onSetup() {};
	
	public abstract void onStartup();
	
	public abstract void onShutdown();
	
	
	//=====================================================\\
	//             	  	   Convenience          	       \\
	//=====================================================\\
	
}
