package com.github.tehsteel.minigameapi.arena.model;


import com.github.tehsteel.minigameapi.MiniGameLib;
import com.github.tehsteel.minigameapi.arena.ArenaException;
import com.github.tehsteel.minigameapi.arena.ArenaState;
import com.github.tehsteel.minigameapi.util.CustomLocation;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Arena {

	@Getter private final String name;
	@Getter @Setter private Location waitingLocation;
	@Getter @Setter private int maxPlayers;
	@Getter @Setter private int minPlayers;
	@Getter @Setter private ArenaState state = ArenaState.READY;
	@Getter @Setter private Set<Location> spawnLocations = new HashSet<>();

	@Getter private File configFile;
	@Getter private YamlConfiguration config = new YamlConfiguration();

	/**
	 * Creates a new Arena with the specified name.
	 *
	 * @param name The name of the arena.
	 * @throws RuntimeException If an ArenaException occurs during arena file creation.
	 */
	public Arena(final String name) {
		this.name = name;

		try {
			createArenaFile();
		} catch (final ArenaException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates a new Arena by copying attributes from an existing Arena.
	 *
	 * @param arena The existing Arena to copy attributes from.
	 * @throws RuntimeException If an ArenaException occurs during arena file creation.
	 */
	public Arena(final Arena arena) {
		this.name = arena.name;
		this.waitingLocation = arena.waitingLocation;
		this.maxPlayers = arena.maxPlayers;
		this.minPlayers = arena.minPlayers;
		this.state = ArenaState.READY;
		this.configFile = arena.configFile;
		this.config = arena.config;

		try {
			createArenaFile();
		} catch (final ArenaException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates a new Arena with specified attributes.
	 *
	 * @param name            The name of the arena.
	 * @param waitingLocation The waiting location for players.
	 * @param maxPlayers      The maximum number of players allowed in the arena.
	 * @param minPlayers      The minimum number of players required to start the game.
	 * @throws RuntimeException If an ArenaException occurs during arena file creation.
	 */
	public Arena(final String name, final Location waitingLocation, final int maxPlayers, final int minPlayers) {
		this.name = name;
		this.waitingLocation = waitingLocation;
		this.maxPlayers = maxPlayers;
		this.minPlayers = minPlayers;

		try {
			createArenaFile();
		} catch (final ArenaException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Deserializes an Arena from a configuration file.
	 *
	 * @param config The FileConfiguration object containing the serialized Arena data.
	 * @return The deserialized Arena.
	 */
	public static Arena deserialize(final FileConfiguration config) {

		final Arena arena = new Arena(config.getString("ArenaData.name"));

		if (config.getString("ArenaData.waitingLocation") != null)
			arena.setWaitingLocation(CustomLocation.deserialize(Objects.requireNonNull(config.getString("ArenaData.waitingLocation"))).toBukkitLocation());

		arena.setMaxPlayers(config.getInt("ArenaData.maxPlayers"));
		arena.setMinPlayers(config.getInt("ArenaData.minPlayers"));

		final ConfigurationSection section = config.getConfigurationSection("ArenaData.spawnLocations");

		if (section != null) {
			for (int i = 0; i < section.getKeys(false).size(); i++) {
				arena.getSpawnLocations().add(CustomLocation.deserialize(Objects.requireNonNull(section.getString(String.valueOf(i)))).toBukkitLocation());
			}
		}

		return arena;
	}

	/**
	 * Saves the arena configuration to the file system.
	 *
	 * @throws ArenaException If an error occurs during the saving process.
	 */
	public void saveConfig() throws ArenaException {
		try {
			config.save(configFile);
		} catch (final IOException e) {
			throw new ArenaException(e.getMessage());
		}
	}

	/**
	 * Deletes the arena configuration file from the file system.
	 */
	public void deleteArenaConfig() {
		configFile.delete();
	}

	/**
	 * Checks if the arena is in a ready state for gameplay.
	 * <p>
	 * The arena is considered ready if its state is set to ArenaState.READY and a waiting location is defined.
	 *
	 * @return True if the arena is ready, otherwise false.
	 */
	public boolean isArenaReady() {
		return state == ArenaState.READY && waitingLocation != null;
	}

	/**
	 * Creates or loads an arena configuration file.
	 *
	 * @throws ArenaException If an error occurs during the creation or loading process.
	 */
	private void createArenaFile() throws ArenaException {

		// Define the file path for the arena configuration file
		configFile = new File(MiniGameLib.getPlugin().getDataFolder() + "/arenas", name + ".yml");

		// If the configuration file does not exist, create a new one
		if (!configFile.exists()) {

			// Ensure the parent directory exists; create if necessary
			if (!configFile.getParentFile().exists() && !configFile.getParentFile().mkdirs()) {
				throw new ArenaException("Failed to create 'arenas' directory.");
			}

			try {
				// Create a new file and load the configuration
				configFile.createNewFile();
				config.load(configFile);
			} catch (IOException | InvalidConfigurationException e) {
				throw new ArenaException("Failed to create arena file: " + e.getMessage());
			}
		} else {
			// Load the existing configuration file
			try {
				config.load(configFile);
			} catch (IOException | InvalidConfigurationException e) {
				throw new ArenaException("Failed to load arena config: " + e.getMessage());
			}
		}
	}

	/**
	 * Serializes the Arena data to a configuration file.
	 *
	 * @throws ArenaException If an error occurs during the serialization process.
	 */
	public void serialize() throws ArenaException {
		config.set("ArenaData.name", name);

		if (waitingLocation != null) {
			config.set("ArenaData.waitingLocation", waitingLocation.serialize());
		}

		config.set("ArenaData.maxPlayers", maxPlayers);
		config.set("ArenaData.minPlayers", minPlayers);

		if (spawnLocations != null && !spawnLocations.isEmpty()) {
			for (int i = 0; i < spawnLocations.size(); i++) {
				config.set("ArenaData.spawnLocations." + i, spawnLocations.stream().toList().get(i).serialize());
			}
		}
		saveConfig();
	}
}