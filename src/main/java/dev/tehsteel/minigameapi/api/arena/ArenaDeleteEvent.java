package dev.tehsteel.minigameapi.api.arena;

import dev.tehsteel.minigameapi.api.arena.model.ArenaEvent;
import dev.tehsteel.minigameapi.arena.model.Arena;

public class ArenaDeleteEvent extends ArenaEvent {
	public ArenaDeleteEvent(final Arena arena) {
		super(arena);
	}
}
