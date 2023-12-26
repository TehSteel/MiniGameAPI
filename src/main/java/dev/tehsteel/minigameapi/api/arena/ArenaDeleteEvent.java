package dev.tehsteel.minigameapi.api.arena;

import dev.tehsteel.minigameapi.api.arena.model.ArenaEvent;
import dev.tehsteel.minigameapi.arena.model.Arena;

/**
 * Represents an event triggered when an arena is deleted.
 * This event is fired when a new arena is deleted.
 */
public class ArenaDeleteEvent extends ArenaEvent {
	public ArenaDeleteEvent(final Arena arena) {
		super(arena);
	}
}
