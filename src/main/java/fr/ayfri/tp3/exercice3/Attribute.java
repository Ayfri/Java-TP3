package fr.ayfri.tp3.exercice3;

import org.jetbrains.annotations.NotNull;

/**
 * L'énumération Attribute permettant de créer des attributs pour les cartes de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public enum Attribute {
	DARK("T\u00E9n\u00E8bres"),
	LIGHT("Lumi\u00E8re"),
	DIVINE("Divin"),
	EARTH("Terre"),
	WATER("Eau"),
	FIRE("Feu"),
	WIND("Vent");

	/**
	 * La traduction de l'attribut.
	 */
	private final @NotNull String translation;

	/**
	 * Constructeur de l'énumération Attribute.
	 *
	 * @param translation La traduction de l'attribut.
	 */
	Attribute(@NotNull final String translation) {
		this.translation = translation;
	}

	/**
	 * Getter de la traduction de l'attribut.
	 *
	 * @return La traduction de l'attribut.
	 */
	public @NotNull String getTranslation() {
		return translation;
	}
}
