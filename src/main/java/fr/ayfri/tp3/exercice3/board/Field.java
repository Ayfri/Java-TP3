package fr.ayfri.tp3.exercice3.board;

import fr.ayfri.tp3.exercice3.cards.AMonstre;
import fr.ayfri.tp3.exercice3.cards.APiegeEtMagie;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class Field {
	private final @NotNull Set<AMonstre> monsterArea = new HashSet<>(5);
	private final @NotNull Set<APiegeEtMagie> specialArea = new HashSet<>(5);
	private final @NotNull Set<ICarteYuGiOh> graveyard = new HashSet<>();
	private final @NotNull Set<ICarteYuGiOh> hand = new HashSet<>();
	private final @NotNull Deck<ICarteYuGiOh> mainDeck = new Deck.Factory<>(ICarteYuGiOh.class).createDeck(40);
	private final @NotNull Deck<AMonstre> extraDeck = new Deck.Factory<>(AMonstre.class).createDeck(15);
	private final @NotNull Deck<ICarteYuGiOh> sideDeck = new Deck.Factory<>(ICarteYuGiOh.class).createDeck(15);

	public @NotNull Set<AMonstre> getMonsterArea() {
		return monsterArea;
	}

	public @NotNull Set<APiegeEtMagie> getSpecialArea() {
		return specialArea;
	}

	public @NotNull Set<ICarteYuGiOh> getGraveyard() {
		return graveyard;
	}

	public @NotNull Set<ICarteYuGiOh> getHand() {
		return hand;
	}

	public @NotNull Deck<ICarteYuGiOh> getMainDeck() {
		return mainDeck;
	}

	public @NotNull Deck<AMonstre> getExtraDeck() {
		return extraDeck;
	}

	public @NotNull Deck<ICarteYuGiOh> getSideDeck() {
		return sideDeck;
	}
}
