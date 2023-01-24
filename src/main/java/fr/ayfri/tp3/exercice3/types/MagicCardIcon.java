package fr.ayfri.tp3.exercice3.types;

import org.jetbrains.annotations.NotNull;

/**
 * L'énumération MagicCardIcon permettant de créer des icônes pour les cartes de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public enum MagicCardIcon implements SpecialCardIcon {
	NORMAL("Normal"),
	CONTINUOUS("Continue"),
	EQUIP("\u00C9quipement"),
	FIELD("Terrain"),
	QUICK_PLAY("Jeu rapide"),
	RITUAL("Rituel");

	/**
	 * La traduction de l'icône.
	 */
	private final @NotNull String translation;

	/**
	 * Constructeur de l'énumération MagicCardIcon.
	 *
	 * @param translation La traduction de l'icône.
	 */
	MagicCardIcon(@NotNull final String translation) {
		this.translation = translation;
	}

	@Override
	public @NotNull String getTranslation() {
		return translation;
	}
}
