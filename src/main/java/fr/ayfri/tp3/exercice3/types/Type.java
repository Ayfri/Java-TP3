package fr.ayfri.tp3.exercice3.types;

public enum Type {
	AQUA("Aqua"),
	BEAST("Bête"),
	BEAST_WARRIOR("Bête-Guerrier"),
	CREATOR_GOD("Dieu créateur"),
	CYBERSE("Cyberse"),
	DINOSAUR("Dinosaure"),
	DIVINE_BEAST("Bête divine"),
	DRAGON("Dragon"),
	FAIRY("Fée"),
	FIEND("Démon"),
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
	WINGED_BEAST("Bête ailée"),
	WYRM("Dragon à cornes"),
	ZOMBIE("Zombie");

	private final String translation;

	Type(final String translation) {
		this.translation = translation;
	}

	public String getTranslation() {
		return translation;
	}
}
