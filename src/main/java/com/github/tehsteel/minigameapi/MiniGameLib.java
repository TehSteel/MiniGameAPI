package com.github.tehsteel.minigameapi;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniGameLib {

	@Getter
	private static JavaPlugin plugin;

	/**
	 * Sets the main JavaPlugin instance for MiniGameLib.
	 *
	 * @param plugin The JavaPlugin instance to be set as the main plugin for MiniGameLib.
	 */
	public static void setPlugin(final JavaPlugin plugin) {
		MiniGameLib.plugin = plugin;
	}

	/**
	 * Sets the main JavaPlugin instance for MiniGameLib and configures debug mode.
	 *
	 * @param plugin    The JavaPlugin instance to be set as the main plugin for MiniGameLib.
	 * @param debugMode Whether debug mode should be enabled or not.
	 */
	public static void setPlugin(final JavaPlugin plugin, final boolean debugMode) {
		MiniGameLib.plugin = plugin;
		Constants.DEBUG_MODE = debugMode;
	}

	/**
	 * Sets the debug mode for MiniGameLib.
	 *
	 * @param debugMode Whether debug mode should be enabled or not.
	 */
	public static void setDebugMode(final boolean debugMode) {
		Constants.DEBUG_MODE = debugMode;
	}

	private MiniGameLib() {
	}
}
