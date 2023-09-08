package com.github.tehsteel.minigameapi.api.game;

import com.github.tehsteel.minigameapi.api.game.model.GameEvent;
import com.github.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * Represents an event when a player quits or leaves a game.
 */
public class GamePlayerQuitEvent extends GameEvent {

	/**
	 * The player who quit or left the game.
	 */
	@Getter
	private final Player player;

	/**
	 * Constructs a GamePlayerQuitEvent.
	 *
	 * @param game   The game associated with this event.
	 * @param player The player who quit or left the game.
	 */
	public GamePlayerQuitEvent(final Game game, final Player player) {
		super(game);
		this.player = player;
	}
}
