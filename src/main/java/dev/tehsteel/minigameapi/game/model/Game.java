package dev.tehsteel.minigameapi.game.model;

import dev.tehsteel.minigameapi.api.game.GameCountdownEvent;
import dev.tehsteel.minigameapi.api.game.GameEndEvent;
import dev.tehsteel.minigameapi.api.game.GameStartEvent;
import dev.tehsteel.minigameapi.api.game.model.GameEvent;
import dev.tehsteel.minigameapi.api.game.player.GamePlayerJoinEvent;
import dev.tehsteel.minigameapi.api.game.player.GamePlayerLoseEvent;
import dev.tehsteel.minigameapi.api.game.player.GamePlayerQuitEvent;
import dev.tehsteel.minigameapi.api.game.player.GamePlayerWinEvent;
import dev.tehsteel.minigameapi.arena.ArenaState;
import dev.tehsteel.minigameapi.arena.model.Arena;
import dev.tehsteel.minigameapi.game.GameState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public abstract class Game {

	private final Arena arena;
	private final Set<Player> players = new HashSet<>();
	@Setter private GameState state = GameState.WAITING;
	@Setter private int countdown = 10;

	/**
	 * Starts the game if there are enough players or if forceStart is set to true.
	 * Triggers a GameStartEvent with the specified forceStart parameter.
	 * The game will not start if the number of players is less than the required minimum and forceStart is false.
	 *
	 * @param forceStart If true, the game will start regardless of the number of players. If false,
	 *                   the game will only start if the minimum required players are present.
	 * @see GameStartEvent
	 */
	public void startGame(final boolean forceStart) {
		if (players.size() < arena.getMinPlayers() && !forceStart) return;
		fireGameEvent(new GameStartEvent(this, forceStart));
		getArena().setState(ArenaState.INGAME);
		setState(GameState.INGAME);
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
	public void startCountdown(final boolean forceStart) {
		if (players.size() < arena.getMinPlayers() && !forceStart) return;
		fireGameEvent(new GameCountdownEvent(this, countdown, forceStart));
		setState(GameState.COUNTDOWN);
		getArena().setState(ArenaState.INGAME);
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
	public void endGame(final boolean forceStop) {
		if (!forceStop && !shouldGameEnd()) return;
		fireGameEvent(new GameEndEvent(this, forceStop));
		setState(GameState.ENDGAME);
	}

	/**
	 * Forces the immediate start of the game by triggering a GameStartEvent with forceStart set to true.
	 * This bypasses any player count requirements.
	 *
	 * @see GameStartEvent
	 */
	public void forceStartGame() {
		fireGameEvent(new GameStartEvent(this, true));
		getArena().setState(ArenaState.INGAME);
	}


	/**
	 * Checks if the game is in a state where players can join freely.
	 * The game is considered free if it's in the WAITING state or in the COUNTDOWN state
	 * with the number of players less than the maximum allowed.
	 *
	 * @return True if the game is in a free state, otherwise false.
	 */
	public boolean isGameFree() {
		return state == GameState.WAITING || state == GameState.COUNTDOWN && players.size() < arena.getMaxPlayers();
	}

	/**
	 * Adds a player to the game and triggers a GamePlayerJoinEvent.
	 * Ensure not to trigger twice to prevent an endless loop.
	 *
	 * @param player The player to be added to the game. This will also be included in the GamePlayerJoinEvent.
	 */
	public void addPlayer(final Player player) {
		players.add(player);
		fireGameEvent(new GamePlayerJoinEvent(this, player));
		startCountdown(false);
	}

	/**
	 * Removes a player from the game and triggers a GamePlayerQuitEvent.
	 *
	 * @param player The player to be removed from the game. This will also be included in the GamePlayerQuitEvent.
	 */
	public void removePlayer(final Player player) {
		players.remove(player);
		fireGameEvent(new GamePlayerQuitEvent(this, player));
	}

	/**
	 * Triggers a game event indicating that the specified player has won the game.
	 *
	 * @param player The player who won the game.
	 */
	public void onPlayerWin(final Player player) {
		fireGameEvent(new GamePlayerWinEvent(this, player));
	}

	/**
	 * Triggers a game event indicating that the specified player has lost the game.
	 *
	 * @param player The player that lost the game.
	 */
	public void onPlayerLose(final Player player) {
		fireGameEvent(new GamePlayerLoseEvent(this, player));
	}

	/**
	 * Resets the game to its initial state, allowing for a fresh start.
	 * This method should clear any in-progress game data and reinitialize
	 * all relevant game parameters and variables.
	 */
	public void resetGame() {

	}

	/**
	 * Determines whether the game should end based on specific conditions.
	 *
	 * @return True if the game should end, otherwise false.
	 */
	public abstract boolean shouldGameEnd();

	/**
	 * Fires a custom GameEvent by calling the Bukkit event system.
	 *
	 * @param event The GameEvent to be fired.
	 * @param <T>   The type of GameEvent to be fired.
	 */
	protected <T extends GameEvent> void fireGameEvent(final T event) {
		if (event.isCancelled()) return;
		Bukkit.getServer().getPluginManager().callEvent(event);
	}
}
