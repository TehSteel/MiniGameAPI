package com.github.tehsteel.minigameapi.api.game.model;

import com.github.tehsteel.minigameapi.game.model.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@AllArgsConstructor
public class GameEvent extends Event {
	private static final HandlerList HANDLERS = new HandlerList();

	@Getter private Game game;

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}


}
