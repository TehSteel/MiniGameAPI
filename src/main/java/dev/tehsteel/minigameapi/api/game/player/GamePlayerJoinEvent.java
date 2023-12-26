package dev.tehsteel.minigameapi.api.game.player;

import dev.tehsteel.minigameapi.api.game.model.GameEvent;
import dev.tehsteel.minigameapi.game.model.Game;
import lombok.Getter;
import org.bukkit.entity.Player;


/**
 * Represents an event when a player joins a game.
 */
@Getter
public class GamePlayerJoinEvent extends GameEvent {

	private final Player player;

	public GamePlayerJoinEvent(final Game game, final Player player) {
		super(game);
		this.player = player;
	}
}