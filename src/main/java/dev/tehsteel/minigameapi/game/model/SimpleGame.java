package dev.tehsteel.minigameapi.game.model;

import dev.tehsteel.minigameapi.arena.model.Arena;

public abstract class SimpleGame extends Game {
	public SimpleGame(final Arena arena) {
		super(arena);
	}

	@Override
	public abstract boolean shouldGameEnd();
}
