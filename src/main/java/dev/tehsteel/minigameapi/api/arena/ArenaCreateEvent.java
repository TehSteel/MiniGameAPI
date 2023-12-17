package dev.tehsteel.minigameapi.api.arena;

import dev.tehsteel.minigameapi.api.arena.model.ArenaEvent;
import dev.tehsteel.minigameapi.arena.model.Arena;

public final class ArenaCreateEvent extends ArenaEvent {

	public ArenaCreateEvent(final Arena arena) {
		super(arena);
	}
}
