package com.github.tehsteel.minigameapi.game.model;

import com.github.tehsteel.minigameapi.MiniGameLib;
import com.github.tehsteel.minigameapi.api.game.GameStartEvent;
import com.github.tehsteel.minigameapi.arena.model.Arena;
import com.google.common.annotations.Beta;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

@Beta
public abstract class GameRound extends Game implements Runnable, Listener {

	@Getter @Setter private int roundSeconds;
	@Getter @Setter private int rounds;
	@Getter @Setter private long runEveryTick;
	@Getter private int runnableTaskId;

	public GameRound(final Arena arena) {
		super(arena);


	}

	public abstract boolean shouldNextRoundStart();

	public abstract void startNextRound();

	public abstract void onTick();

	public abstract void onEnd();

	protected void onError() {
	}


	private void runTask() {
		final BukkitTask task = Bukkit.getScheduler().runTaskTimer(MiniGameLib.getPlugin(), this, runEveryTick, runEveryTick);

		runnableTaskId = task.getTaskId();
	}

	@EventHandler
	public void onGameStart(final GameStartEvent event) {
		if (event.getGame() == this) {
			runTask();
		}
	}


	@Override
	public void run() {

	}
}
