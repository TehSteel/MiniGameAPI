package dev.tehsteel.minigameapi.arena.model;

import com.google.gson.Gson;
import dev.tehsteel.minigameapi.MiniGameLib;
import dev.tehsteel.minigameapi.api.arena.ArenaCreateEvent;
import dev.tehsteel.minigameapi.api.arena.ArenaDeleteEvent;
import dev.tehsteel.minigameapi.api.arena.model.ArenaEvent;
import dev.tehsteel.minigameapi.arena.ArenaException;
import dev.tehsteel.minigameapi.arena.ArenaState;
import dev.tehsteel.minigameapi.util.CustomLocation;
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
import java.util.Set;

/**
 * The Arena class represents an arena object.
 */
@Getter
public class Arena {

	private final String name;
	@Setter private Location waitingLocation;
	@Setter private int maxPlayers;
	@Setter private int minPlayers;
	@Setter private ArenaState state = ArenaState.READY;
	@Setter private Set<Location> spawnLocations = new HashSet<>();

	private File configFile;
	private YamlConfiguration config = new YamlConfiguration();

	/**
	 * Creates a new Arena with the specified name.
	 *
	 * @param name The name of the arena.
	 * @throws ArenaException If an ArenaException occurs during arena file creation.
	 */
	public Arena(final String name) throws ArenaException {
		this.name = name;

		fireArenaEvent(new ArenaCreateEvent(this));

		createArenaFile();
	}

	/**
	 * Creates a new Arena by copying attributes from an existing Arena.
	 *
	 * @param arena The existing Arena to copy attributes from.
	 * @throws ArenaException If an ArenaException occurs during arena file creation.
	 */
	public Arena(final Arena arena) throws ArenaException {
		this.name = arena.name;
		this.waitingLocation = arena.waitingLocation;
		this.maxPlayers = arena.maxPlayers;
		this.minPlayers = arena.minPlayers;
		this.state = ArenaState.READY;
		this.configFile = arena.configFile;
		this.config = arena.config;

		fireArenaEvent(new ArenaCreateEvent(this));

		createArenaFile();
	}

	/**
	 * Creates a new Arena with specified attributes.
	 *
	 * @param name            The name of the arena.
	 * @param waitingLocation The waiting location for players.
	 * @param maxPlayers      The maximum number of players allowed in the arena.
	 * @param minPlayers      The minimum number of players required to start the game.
	 * @throws ArenaException If an ArenaException occurs during arena file creation.
	 */
	public Arena(final String name, final Location waitingLocation, final int maxPlayers, final int minPlayers) throws ArenaException {
		this.name = name;
		this.waitingLocation = waitingLocation;
		this.maxPlayers = maxPlayers;
		this.minPlayers = minPlayers;

		fireArenaEvent(new ArenaCreateEvent(this));

		createArenaFile();
	}

	/**
	 * Deserializes an Arena from a configuration file.
	 *
	 * @param config The FileConfiguration object containing the serialized Arena data.
	 * @return The deserialized Arena.
	 */
	public static Arena deserialize(final FileConfiguration config) throws ArenaException {
		final Gson gson = MiniGameLib.getGson();

		if (config == null) {
			throw new ArenaException("The config hasn't been initialized yet.");
		}

		if (config.getString("ArenaData.name") == null || config.getString("ArenaData.name").isEmpty()) {
			throw new ArenaException("The arena data named does not exist.");
		}

		final Arena arena = new Arena(config.getString("ArenaData.name"));

		if (config.getString("ArenaData.waitingLocation") != null)
			arena.setWaitingLocation(gson.fromJson(config.getString("ArenaData.waitingLocation"), CustomLocation.class).toBukkitLocation());

		arena.setMaxPlayers(config.getInt("ArenaData.maxPlayers"));
		arena.setMinPlayers(config.getInt("ArenaData.minPlayers"));

		final ConfigurationSection section = config.getConfigurationSection("ArenaData.spawnLocations");

		if (section != null) {
			for (int i = 0; i < section.getKeys(false).size(); i++) {
				arena.getSpawnLocations().add(gson.fromJson(section.getString(String.valueOf(i)), CustomLocation.class).toBukkitLocation());
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
		fireArenaEvent(new ArenaDeleteEvent(this));
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
		configFile = new File(MiniGameLib.getPlugin().getDataFolder() + "/arenas", name + ".yml");

		if (!configFile.exists()) {
			if (!configFile.getParentFile().exists() && !configFile.getParentFile().mkdirs()) {
				throw new ArenaException("Failed to create arenas directory.");
			}

			try {
				configFile.createNewFile();
				config.load(configFile);
			} catch (IOException | InvalidConfigurationException e) {
				throw new ArenaException("Failed to create arena file: " + e.getMessage());
			}
		} else {
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
		final Gson gson = MiniGameLib.getGson();
		config.set("ArenaData.name", name);

		if (waitingLocation != null) {
			config.set("ArenaData.waitingLocation", gson.toJson(CustomLocation.fromBukkitLocation(waitingLocation), CustomLocation.class));
		}

		config.set("ArenaData.maxPlayers", maxPlayers);
		config.set("ArenaData.minPlayers", minPlayers);

		if (spawnLocations != null && !spawnLocations.isEmpty()) {
			for (int i = 0; i < spawnLocations.size(); i++) {
				config.set("ArenaData.spawnLocations." + i, gson.toJson(CustomLocation.fromBukkitLocation(spawnLocations.stream().toList().get(i)), CustomLocation.class));
			}
		}
		saveConfig();
	}

	private <T extends ArenaEvent> void fireArenaEvent(final T event) {
		if (event.isCancelled()) return;
		MiniGameLib.getPlugin().getServer().getPluginManager().callEvent(event);
	}
}