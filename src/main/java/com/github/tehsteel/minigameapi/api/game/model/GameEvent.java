package com.github.tehsteel.minigameapi.api.game.model;

import com.github.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
@RequiredArgsConstructor
public class GameEvent extends Event implements Cancellable {
	private static final HandlerList HANDLERS = new HandlerList();

	private final Game game;
	@Setter private boolean cancelled;

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}


}
