package fr.ayfri.tp3.exercice3.types;

import org.jetbrains.annotations.NotNull;

public enum MagicCardIcon implements SpecialCardIcon {
	NORMAL("Normal"),
	CONTINUOUS("Continue"),
	EQUIP("Équipement"),
	FIELD("Terrain"),
	QUICK_PLAY("Jeu rapide"),
	RITUAL("Rituel");

	private final @NotNull String translation;

	MagicCardIcon(@NotNull final String translation) {
		this.translation = translation;
	}

	@Override
	public @NotNull String getTranslation() {
		return translation;
	}
}
