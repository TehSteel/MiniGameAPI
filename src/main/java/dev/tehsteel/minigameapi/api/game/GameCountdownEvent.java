package dev.tehsteel.minigameapi.api.game;

import dev.tehsteel.minigameapi.api.game.model.GameEvent;
import dev.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;

/**
 * Represents an event triggered when a game has started a countdown.
 * This event is fired when a game has a countdown.
 */
@Getter
public class GameCountdownEvent extends GameEvent {

	private final int seconds;
	private final boolean forceStart;

	public GameCountdownEvent(final Game game, final int seconds, final boolean forceStart) {
		super(game);
		this.seconds = seconds;
		this.forceStart = forceStart;
	}
}