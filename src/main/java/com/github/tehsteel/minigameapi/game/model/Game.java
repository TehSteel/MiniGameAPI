package com.github.tehsteel.minigameapi.game.model;

import com.github.tehsteel.minigameapi.api.game.*;
import com.github.tehsteel.minigameapi.api.game.model.GameEvent;
import com.github.tehsteel.minigameapi.arena.model.Arena;
import com.github.tehsteel.minigameapi.game.GameState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public abstract class Game {

	@Getter private final Arena arena;
	@Getter private final Set<Player> players = new HashSet<>();
	@Getter @Setter private GameState state = GameState.WAITING;
	@Getter @Setter private int countdown = 10;


	/**
	 * Starts the game if there are enough players or if forceStart is set to true.
	 * Triggers a GameStartEvent with the specified forceStart parameter.
	 * The game will not start if the number of players is less than the required minimum and forceStart is false.
	 *
	 * @param forceStart If true, the game will start regardless of the number of players. If false,
	 *                   the game will only start if the minimum required players are present.
	 * @see GameStartEvent
	 */
	public final void startGame(final boolean forceStart) {
		if (players.size() < arena.getMinPlayers() && !forceStart) return;
		fireGameEvent(new GameStartEvent(this, forceStart));
	}

	/**
	 * Initiates a countdown before starting the game if there are enough players or if forceStart is set to true.
	 * Triggers a GameCountdownEvent with the specified countdown duration and forceStart parameter.
	 * The countdown will not initiate if the number of players is less than the required minimum and forceStart is false.
	 *
	 * @param forceStart If true, the countdown and game start will proceed regardless of the number of players. If false,
	 *                   the countdown will only initiate if the minimum required players are present.
	 * @see GameCountdownEvent
	 */
	public final void startCountdown(final boolean forceStart) {
		if (players.size() < arena.getMinPlayers() && !forceStart) return;
		fireGameEvent(new GameCountdownEvent(this, countdown, forceStart));
	}

	/**
	 * Ends the game, triggering a GameEndEvent.
	 * If forceStop is true, the game will end regardless of its current state.
	 * If forceStop is false, the game will only end if the conditions for ending are met.
	 *
	 * @param forceStop If true, the game will end immediately. If false, the game will end only if the
	 *                  conditions for ending are met.
	 * @see GameEndEvent
	 */
	public final void endGame(final boolean forceStop) {
		if (!forceStop && !shouldGameEnd()) return;
		fireGameEvent(new GameEndEvent(this, forceStop));
		state = GameState.ENDGAME;
	}

	/**
	 * Forces the immediate start of the game by triggering a GameStartEvent with forceStart set to true.
	 * This bypasses any player count requirements.
	 *
	 * @see GameStartEvent
	 */
	public final void forceStartGame() {
		fireGameEvent(new GameStartEvent(this, true));
	}

	/**
	 * Checks if the game is in a state where players can join freely.
	 * The game is considered free if it's in the WAITING state or in the COUNTDOWN state
	 * with the number of players less than the maximum allowed.
	 *
	 * @return True if the game is in a free state, otherwise false.
	 */
	public final boolean isGameFree() {
		return state == GameState.WAITING || state == GameState.COUNTDOWN && players.size() < arena.getMaxPlayers();
	}

	/**
	 * Adds a player to the game and triggers a GamePlayerJoinEvent.
	 * Ensure not to trigger twice to prevent an endless loop.
	 *
	 * @param player The player to be added to the game. This will also be included in the GamePlayerJoinEvent.
	 */
	public final void addPlayer(final Player player) {
		players.add(player);
		fireGameEvent(new GamePlayerJoinEvent(this, player));
		startCountdown(false);
	}

	/**
	 * Removes a player from the game and triggers a GamePlayerQuitEvent.
	 *
	 * @param player The player to be removed from the game. This will also be included in the GamePlayerQuitEvent.
	 */
	public final void removePlayer(final Player player) {
		players.remove(player);
		fireGameEvent(new GamePlayerQuitEvent(this, player));
	}

	/**
	 * Determines whether the game should end based on specific conditions.
	 *
	 * @return True if the game should end, otherwise false.
	 */
	public abstract boolean shouldGameEnd();

	/**
	 * Retrieves the game data in the form of a Map.
	 *
	 * @return A Map containing the game data. The keys and values may vary depending on the specific game implementation.
	 */
	public abstract Map<?, ?> getGameData();

	/**
	 * Fires a custom GameEvent by calling the Bukkit event system.
	 *
	 * @param event The GameEvent to be fired.
	 * @param <T>   The type of GameEvent to be fired.
	 */
	private <T extends GameEvent> void fireGameEvent(final T event) {
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	/**
	 * Retrieves data from a Game object based on a specified key and class type.
	 *
	 * @param key   The key used to access the desired data.
	 * @param clazz The class type that the retrieved data should be cast to.
	 * @param <T>   The generic type for the return value.
	 * @return The retrieved data casted to the specified class type, or null if the data is not of the correct type.
	 */
	@Nullable
	public <T> T getData(final Object key, final Class<T> clazz) {
		final Object value = getGameData().get(key);

		return clazz.isInstance(value) ? clazz.cast(value) : null;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Game game = (Game) o;
		return countdown == game.countdown && Objects.equals(arena, game.arena) && Objects.equals(players, game.players) && state == game.state;
	}
}
