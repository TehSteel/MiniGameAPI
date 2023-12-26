package dev.tehsteel.minigameapi.api.game;


import dev.tehsteel.minigameapi.api.game.model.GameEvent;
import dev.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;

/**
 * Represents an event triggered when a game has ended.
 * This event is fired when a game has ended.
 */
@Getter
public class GameEndEvent extends GameEvent {

	private final boolean forceStopped;

	public GameEndEvent(final Game game, final boolean forceStopped) {
		super(game);
		this.forceStopped = forceStopped;
	}
}