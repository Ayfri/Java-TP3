package fr.ayfri.tp3.exercice3.types;

import org.jetbrains.annotations.NotNull;

/**
 * L'énumération TrapCardIcon permettant de créer des icônes pour les cartes de Yu-Gi-Oh.
 */
public enum TrapCardIcon implements SpecialCardIcon {
	NORMAL("Normal"),
	CONTINUOUS("Continue"),
	COUNTER("Contre-Pi\u00E8ge");

	/**
	 * La traduction de l'icône.
	 */
	private final @NotNull String translation;

	/**
	 * Constructeur de l'énumération TrapCardIcon.
	 *
	 * @param translation La traduction de l'icône.
	 */
	TrapCardIcon(@NotNull final String translation) {
		this.translation = translation;
	}

	@Override
	public @NotNull String getTranslation() {
		return translation;
	}
}
