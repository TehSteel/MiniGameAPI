package dev.tehsteel.minigameapi.game.model;

import dev.tehsteel.minigameapi.arena.model.Arena;

public abstract class TimeBasedGame extends Game {
	public TimeBasedGame(final Arena arena) {
		super(arena);
	}

	@Override
	public abstract boolean shouldGameEnd();
}
