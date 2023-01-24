package fr.ayfri.tp3.exercice3.board;

import fr.ayfri.tp3.exercice3.board.Deck.Factory;
import fr.ayfri.tp3.exercice3.cards.AMonstre;
import fr.ayfri.tp3.exercice3.cards.APiegeEtMagie;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * La classe Field représente le terrain de bataille d'un joueur.
 * Elle contient les zones de jeu, le cimetière, la main et les decks.
 *
 * @author Ayfri
 */
public final class Field {
	/**
	 * La zone monstre.
	 */
	private final @NotNull Set<AMonstre> monsterArea = new HashSet<>(5);
	/**
	 * La zone piège et magie.
	 */
	private final @NotNull Set<APiegeEtMagie> specialArea = new HashSet<>(5);
	/**
	 * Le cimetière.
	 */
	private final @NotNull Set<ICarteYuGiOh> graveyard = new HashSet<>();
	/**
	 * La main.
	 */
	private final @NotNull Set<ICarteYuGiOh> hand = new HashSet<>();
	/**
	 * Le deck principal.
	 */
	private final @NotNull Deck<ICarteYuGiOh> mainDeck = new Factory<>(ICarteYuGiOh.class).createDeck(40);
	/**
	 * Le deck extra.
	 */
	private final @NotNull Deck<AMonstre> extraDeck = new Factory<>(AMonstre.class).createDeck(15);
	/**
	 * Le deck secondaire.
	 */
	private final @NotNull Deck<ICarteYuGiOh> sideDeck = new Factory<>(ICarteYuGiOh.class).createDeck(15);

	/**
	 * Getter de la zone monstre.
	 *
	 * @return La zone monstre.
	 */
	public @NotNull Set<AMonstre> getMonsterArea() {
		return monsterArea;
	}

	/**
	 * Getter de la zone piège et magie.
	 *
	 * @return La zone piège et magie.
	 */
	public @NotNull Set<APiegeEtMagie> getSpecialArea() {
		return specialArea;
	}

	/**
	 * Getter du cimetière.
	 *
	 * @return Le cimetière.
	 */
	public @NotNull Set<ICarteYuGiOh> getGraveyard() {
		return graveyard;
	}

	/**
	 * Getter de la main.
	 *
	 * @return La main.
	 */
	public @NotNull Set<ICarteYuGiOh> getHand() {
		return hand;
	}

	/**
	 * Getter du deck principal.
	 *
	 * @return Le deck principal.
	 */
	public @NotNull Deck<ICarteYuGiOh> getMainDeck() {
		return mainDeck;
	}

	/**
	 * Getter du deck extra.
	 *
	 * @return Le deck extra.
	 */
	public @NotNull Deck<AMonstre> getExtraDeck() {
		return extraDeck;
	}

	/**
	 * Getter du deck secondaire.
	 *
	 * @return Le deck secondaire.
	 */
	public @NotNull Deck<ICarteYuGiOh> getSideDeck() {
		return sideDeck;
	}
}
