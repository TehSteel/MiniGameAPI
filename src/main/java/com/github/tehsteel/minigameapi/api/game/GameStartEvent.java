package com.github.tehsteel.minigameapi.api.game;

import com.github.tehsteel.minigameapi.api.game.model.GameEvent;
import com.github.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;

/**
 * Represents an event when a game starts.
 */
public final class GameStartEvent extends GameEvent {

	/**
	 * Indicates if the game was forcefully started.
	 */
	@Getter
	private boolean forceStart;

	/**
	 * Constructs a GameStartEvent.
	 *
	 * @param game       The game associated with this event.
	 * @param forceStart Indicates whether the game was forcefully started.
	 */
	public GameStartEvent(final Game game, final boolean forceStart) {
		super(game);
		this.forceStart = forceStart;
	}
}
