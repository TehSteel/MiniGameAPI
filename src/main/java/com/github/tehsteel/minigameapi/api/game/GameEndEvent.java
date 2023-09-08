package com.github.tehsteel.minigameapi.api.game;

import com.github.tehsteel.minigameapi.api.game.model.GameEvent;
import com.github.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;

/**
 * Represents an event indicating the end of a game.
 * Extends the base GameEvent class.
 */
public class GameEndEvent extends GameEvent {

	/**
	 * Indicates whether the game was forcefully stopped.
	 */
	@Getter
	private final boolean forceStopped;

	/**
	 * Constructs a GameEndEvent.
	 *
	 * @param game         The game associated with this event.
	 * @param forceStopped Indicates whether the game was forcefully stopped.
	 */
	public GameEndEvent(final Game game, final boolean forceStopped) {
		super(game);
		this.forceStopped = forceStopped;
	}
}
