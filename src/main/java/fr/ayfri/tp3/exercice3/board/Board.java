package fr.ayfri.tp3.exercice3.board;

import org.jetbrains.annotations.NotNull;

public final class Board {
	private final @NotNull Player firstPlayer = new Player();
	private final @NotNull Player secondPlayer = new Player();

	public @NotNull Player getFirstPlayer() {
		return firstPlayer;
	}

	public @NotNull Player getSecondPlayer() {
		return secondPlayer;
	}
}
