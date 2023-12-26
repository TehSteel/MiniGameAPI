package dev.tehsteel.minigameapi.api.game;


import dev.tehsteel.minigameapi.api.game.model.GameEvent;
import dev.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;

/**
 * Represents an event triggered when a game has started.
 * This event is fired when a game has started.
 */
@Getter
public class GameStartEvent extends GameEvent {

	private final boolean forceStart;

	public GameStartEvent(final Game game, final boolean forceStart) {
		super(game);
		this.forceStart = forceStart;
	}
}