package dev.tehsteel.minigameapi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.tehsteel.minigameapi.typeadapter.CustomLocationAdapter;
import dev.tehsteel.minigameapi.util.CustomLocation;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniGameLib {

	@Getter private static JavaPlugin plugin;
	@Getter private static Gson gson = new GsonBuilder()
			.registerTypeAdapter(CustomLocation.class, new CustomLocationAdapter())
			.create();

	/**
	 * Sets the main JavaPlugin instance for MiniGameLib.
	 *
	 * @param plugin The JavaPlugin instance to be set as the main plugin for MiniGameLib.
	 */
	public static void setPlugin(final JavaPlugin plugin) {
		MiniGameLib.plugin = plugin;
	}

	private MiniGameLib() {
	}
}
