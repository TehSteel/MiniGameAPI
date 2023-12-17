package dev.tehsteel.minigameapi.arena;

import dev.tehsteel.minigameapi.arena.model.Arena;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

/**
 * A builder class for constructing Arena easily with various properties.
 */
public final class SimpleArenaBuilder {
	private final String name;
	private Location waitingLocation;
	private int maxPlayers;
	private int minPlayers;
	private ArenaState state = ArenaState.READY;
	private Set<Location> spawnLocations = new HashSet<>();

	/**
	 * Private constructor for SimpleArenaBuilder.
	 *
	 * @param name The name of the arena.
	 */
	private SimpleArenaBuilder(final String name) {
		this.name = name;
	}

	/**
	 * Static factory method to create an instance of SimpleArenaBuilder.
	 *
	 * @param name The name of the arena.
	 * @return A new instance of SimpleArenaBuilder.
	 */
	public static SimpleArenaBuilder of(final String name) {
		return new SimpleArenaBuilder(name);
	}

	/**
	 * Sets the waiting location for the arena.
	 *
	 * @param waitingLocation The location where players wait before starting the game.
	 * @return This SimpleArenaBuilder instance.
	 */
	public SimpleArenaBuilder withWaitingLocation(final Location waitingLocation) {
		this.waitingLocation = waitingLocation;

		return this;
	}

	/**
	 * Sets the maximum number of players allowed in the arena.
	 *
	 * @param maxPlayers The maximum number of players allowed.
	 * @return This SimpleArenaBuilder instance.
	 */
	public SimpleArenaBuilder withMaxPlayers(final int maxPlayers) {
		this.maxPlayers = maxPlayers;

		return this;
	}

	/**
	 * Sets the minimum number of players required to start the game in the arena.
	 *
	 * @param minPlayers The minimum number of players required.
	 * @return This SimpleArenaBuilder instance.
	 */
	public SimpleArenaBuilder withMinPlayers(final int minPlayers) {
		this.minPlayers = minPlayers;

		return this;
	}

	/**
	 * Sets the state of the arena.
	 *
	 * @param state The state of the arena.
	 * @return This SimpleArenaBuilder instance.
	 */
	public SimpleArenaBuilder withState(final ArenaState state) {
		this.state = state;

		return this;
	}

	/**
	 * Sets the spawn locations for the arena.
	 *
	 * @param spawnLocations The set of spawn locations for players in the arena.
	 * @return This SimpleArenaBuilder instance.
	 */
	public SimpleArenaBuilder withSpawnLocations(final Set<Location> spawnLocations) {
		this.spawnLocations = spawnLocations;

		return this;
	}

	/**
	 * Adds a spawn location for players in the arena.
	 *
	 * @param spawnLocation The spawn location to be added.
	 * @return This SimpleArenaBuilder instance.
	 */
	public SimpleArenaBuilder withSpawnLocation(final Location spawnLocation) {
		this.spawnLocations.add(spawnLocation);

		return this;
	}

	/**
	 * Builds and returns the configured Arena object.
	 *
	 * @return The constructed Arena object.
	 */
	public Arena build() {
		final Arena arena = new Arena(name);

		arena.setWaitingLocation(waitingLocation);
		arena.setMaxPlayers(maxPlayers);
		arena.setMinPlayers(minPlayers);
		arena.setState(state);
		arena.setSpawnLocations(spawnLocations);

		return arena;
	}
}
