package fr.ayfri.tp3.exercice3.types;

import org.jetbrains.annotations.NotNull;

public enum SpecialCardType {
	SPELL("Magie"),
	TRAP("Piège");

	private final @NotNull String translation;

	SpecialCardType(@NotNull final String translation) {
		this.translation = translation;
	}

	public @NotNull String getTranslation() {
		return translation;
	}
}
