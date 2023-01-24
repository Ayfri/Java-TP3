package fr.ayfri.tp3.exercice3.types;

import org.jetbrains.annotations.NotNull;

/**
 * L'énumération SpecialCardType permettant de créer des types spéciaux pour les cartes de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public enum SpecialCardType {
	SPELL("Magie"),
	TRAP("Piège");

	/**
	 * La traduction du type.
	 */
	private final @NotNull String translation;

	/**
	 * Constructeur de l'énumération SpecialCardType.
	 *
	 * @param translation La traduction du type.
	 */
	SpecialCardType(@NotNull final String translation) {
		this.translation = translation;
	}

	/**
	 * Getter de la traduction du type.
	 *
	 * @return La traduction du type.
	 */
	public @NotNull String getTranslation() {
		return translation;
	}
}
