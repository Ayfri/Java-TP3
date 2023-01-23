package fr.ayfri.tp3.exercice3.types;

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

	private final String translation;

	Type(final String translation) {
		this.translation = translation;
	}

	public String getTranslation() {
		return translation;
	}
}
