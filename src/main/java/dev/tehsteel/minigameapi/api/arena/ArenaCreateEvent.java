package dev.tehsteel.minigameapi.api.arena;

import dev.tehsteel.minigameapi.api.arena.model.ArenaEvent;
import dev.tehsteel.minigameapi.arena.model.Arena;

/**
 * Represents an event triggered when an arena is created.
 * This event is fired when a new arena is created.
 */
public final class ArenaCreateEvent extends ArenaEvent {
	public ArenaCreateEvent(final Arena arena) {
		super(arena);
	}
}
