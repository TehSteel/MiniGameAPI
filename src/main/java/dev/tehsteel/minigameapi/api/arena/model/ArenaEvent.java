package dev.tehsteel.minigameapi.api.arena.model;

import dev.tehsteel.minigameapi.arena.model.Arena;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Represents an event related to an arena.
 * This event is used within the context of an arena events execution.
 */
@Getter
@RequiredArgsConstructor
public class ArenaEvent extends Event implements Cancellable {
	private static final HandlerList HANDLERS = new HandlerList();

	private final Arena arena;
	@Setter private boolean cancelled;

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
}