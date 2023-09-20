package com.github.tehsteel.minigameapi.api.game;

import com.github.tehsteel.minigameapi.api.game.model.GameEvent;
import com.github.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;

/**
 * Represents an event for counting down in a game.
 * This event is triggered when the startCountdown method is called.
 */
@Getter
public class GameCountdownEvent extends GameEvent {

	/**
	 * The number of seconds in the countdown.
	 */
	private final int seconds;

	/**
	 * Indicates if the countdown was forcefully started.
	 */
	private final boolean forceStart;

	/**
	 * Constructs a GameCountdownEvent.
	 *
	 * @param game       The game associated with this event.
	 * @param seconds    The number of seconds in the countdown.
	 * @param forceStart Indicates whether the countdown was forcefully started.
	 */
	public GameCountdownEvent(final Game game, final int seconds, final boolean forceStart) {
		super(game);
		this.seconds = seconds;
		this.forceStart = forceStart;
	}
}
