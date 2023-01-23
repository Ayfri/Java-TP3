package fr.ayfri.tp3.exercice3.board;

import org.jetbrains.annotations.NotNull;

public final class Player {
	private final @NotNull Field field = new Field();

	public @NotNull Field getField() {
		return field;
	}
}
