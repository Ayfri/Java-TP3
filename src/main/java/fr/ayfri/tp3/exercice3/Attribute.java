package fr.ayfri.tp3.exercice3;

import org.jetbrains.annotations.NotNull;

public enum Attribute {
	DARK("Ténèbres"),
	LIGHT("Lumière"),
	DIVINE("Divin"),
	EARTH("Terre"),
	WATER("Eau"),
	FIRE("Feu"),
	WIND("Vent");

	private final @NotNull String translation;

	Attribute(@NotNull final String translation) {
		this.translation = translation;
	}

	public @NotNull String getTranslation() {
		return translation;
	}
}
