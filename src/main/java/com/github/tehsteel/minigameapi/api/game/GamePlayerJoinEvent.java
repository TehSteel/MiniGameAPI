package com.github.tehsteel.minigameapi.api.game;

import com.github.tehsteel.minigameapi.api.game.model.GameEvent;
import com.github.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * Represents an event when a player joins a game.
 */
public class GamePlayerJoinEvent extends GameEvent {

	/**
	 * The player who joined the game.
	 */
	@Getter
	private final Player player;

	/**
	 * Constructs a GamePlayerJoinEvent.
	 *
	 * @param game   The game associated with this event.
	 * @param player The player who joined the game.
	 */
	public GamePlayerJoinEvent(final Game game, final Player player) {
		super(game);
		this.player = player;
	}
}
