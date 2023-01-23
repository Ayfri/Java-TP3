package fr.ayfri.tp3.exercice3.board;

import fr.ayfri.tp3.exercice3.cards.AMonstre;
import fr.ayfri.tp3.exercice3.cards.APiegeEtMagie;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class Field {
	private final @NotNull List<AMonstre> monsterArea = new ArrayList<>(5);
	private final @NotNull List<APiegeEtMagie> specialArea = new ArrayList<>(5);
	private final @NotNull List<ICarteYuGiOh> graveyard = new ArrayList<>();
	private final @NotNull List<ICarteYuGiOh> hand = new ArrayList<>();
	private final @NotNull Deck<ICarteYuGiOh> mainDeck = new Deck.Factory<>(ICarteYuGiOh.class).createDeck(40);
	private final @NotNull Deck<AMonstre> extraDeck = new Deck.Factory<>(AMonstre.class).createDeck(15);
	private final @NotNull Deck<ICarteYuGiOh> sideDeck = new Deck.Factory<>(ICarteYuGiOh.class).createDeck(15);

	public @NotNull List<AMonstre> getMonsterArea() {
		return monsterArea;
	}

	public @NotNull List<APiegeEtMagie> getSpecialArea() {
		return specialArea;
	}

	public @NotNull List<ICarteYuGiOh> getGraveyard() {
		return graveyard;
	}

	public @NotNull List<ICarteYuGiOh> getHand() {
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
