package fr.ayfri.tp3.exercice3.types;

import org.jetbrains.annotations.NotNull;

public enum TrapCardIcon implements SpecialCardIcon {
	NORMAL("Normal"),
	CONTINUOUS("Continue"),
	COUNTER("Contre-Pi\u00E8ge");

	private final @NotNull String translation;

	TrapCardIcon(@NotNull final String translation) {
		this.translation = translation;
	}

	@Override
	public @NotNull String getTranslation() {
		return translation;
	}
}
