package fr.ayfri.tp3.exercice3.types;

import org.jetbrains.annotations.NotNull;

/**
 * L'énumération Type permettant de créer des types pour les cartes de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public enum Type {
	AQUA("Aqua"),
	BEAST("B\u00EAte"),
	BEAST_WARRIOR("B\u00EAte-Guerrier"),
	CREATOR_GOD("Dieu cr\u00E9ateur"),
	CYBERSE("Cyberse"),
	DINOSAUR("Dinosaure"),
	DIVINE_BEAST("B\u00EAte divine"),
	DRAGON("Dragon"),
	FAIRY("F\u00E9e"),
	FIEND("D\u00E9mon"),
	FISH("Poisson"),
	INSECT("Insecte"),
	MACHINE("Machine"),
	PLANT("Plante"),
	PSYCHIC("Psy"),
	PYRO("Pyro"),
	REPTILE("Reptile"),
	ROCK("Rocher"),
	SEA_SERPENT("Serpent de mer"),
	SPELLCASTER("Lanceur de sorts"),
	THUNDER("Tonnerre"),
	WARRIOR("Guerrier"),
	WINGED_BEAST("B\u00EAte ail\u00E9e"),
	WYRM("Dragon \u00E0 cornes"),
	ZOMBIE("Zombie");

	/**
	 * La traduction du type.
	 */
	private final @NotNull String translation;

	/**
	 * Constructeur de l'énumération Type.
	 *
	 * @param translation La traduction du type.
	 */
	Type(final @NotNull String translation) {
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
