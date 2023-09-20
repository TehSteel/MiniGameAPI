package com.github.tehsteel.minigameapi.api.game.player;

import com.github.tehsteel.minigameapi.api.game.model.GameEvent;
import com.github.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;
import org.bukkit.entity.Player;


@Getter
public final class GamePlayerWinEvent extends GameEvent {

	private final Player player;

	public GamePlayerWinEvent(final Game game, final Player player) {
		super(game);
		this.player = player;
	}
}
