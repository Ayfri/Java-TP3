package fr.ayfri.tp3.exercice3.board;

import fr.ayfri.tp3.exercice3.cards.AMonstre;
import fr.ayfri.tp3.exercice3.cards.APiegeEtMagie;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import fr.ayfri.tp3.exercice3.data.DataManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La classe Deck représente un deck de cartes.
 *
 * @param <T> Le type de carte que le deck peut contenir.
 *
 * @author Ayfri
 */
public final class Deck<T extends ICarteYuGiOh> {
	/**
	 * La taille du deck.
	 */
	private final int size;
	/**
	 * La liste des cartes du deck.
	 */
	private final @NotNull List<T> cards;

	/**
	 * Constructeur de la classe Deck.
	 *
	 * @param size La taille du deck.
	 */
	public Deck(final int size) {
		this.size = size;
		cards = new ArrayList<>(size);
	}

	/**
	 * Getter de la taille du deck.
	 *
	 * @return La taille du deck.
	 */
	public int size() {
		return size;
	}

	/**
	 * Getter de la liste des cartes du deck.
	 *
	 * @return La liste des cartes du deck.
	 */
	public @NotNull List<T> getCards() {
		return cards;
	}

	/**
	 * Récupère une carte du deck à l'index donné.
	 *
	 * @param index L'index de la carte.
	 *
	 * @return La carte à l'index donné.
	 */
	public @Nullable T get(final int index) {
		return cards.get(index);
	}

	/**
	 * Récupère le nombre de cartes du deck.
	 *
	 * @return Le nombre de cartes du deck.
	 */
	public int cardsCount() {
		return cards.size();
	}

	@Contract(pure = true)
	@Override
	public @NotNull String toString() {
		return "Deck{" + "size=" + size + ", cards=" + cards + '}';
	}

	/**
	 * Ajoute une carte au deck.
	 *
	 * @param card La carte à ajouter.
	 */
	public void addCard(final @NotNull T card) {
		final var count = (int) cards.stream().filter(c -> c.getName().equals(card.getName())).count();

		if (count >= 3 || cards.size() >= size) return;

		cards.add(card);
	}

	/**
	 * Ajoute une carte au deck.
	 *
	 * @return La carte à ajouter.
	 */
	public @Nullable T first() {
		return cards.get(0);
	}

	/**
	 * La Fabrique de Deck permettant de créer des decks de cartes.
	 *
	 * @param <T> Le type de carte que le deck peut contenir.
	 *
	 * @author Ayfri
	 */
	static final class Factory<T extends ICarteYuGiOh> {
		/**
		 * La liste des cartes récupérées depuis le DataManager.
		 */
		private static final @NotNull List<ICarteYuGiOh> CARDS = DataManager.getCards();
		/**
		 * La classe Java du type de carte que le deck peut contenir.
		 */
		private final @NotNull Class<T> entityBeanType;

		/**
		 * Constructeur de la classe Factory.
		 * @param entityBeanType La classe Java du type de carte que le deck peut contenir.
		 */
		public Factory(final @NotNull Class<T> entityBeanType) {
			this.entityBeanType = entityBeanType;
		}


		/**
		 * Crée un deck de cartes.
		 * @param size La taille du deck.
		 *
		 * @return Le deck de cartes.
		 */
		public @NotNull Deck<T> createDeck(final int size) {
			Collections.shuffle(CARDS);

			final var deck = new Deck<T>(size);
			var i = 0;
			final var isSimple = !entityBeanType.isAssignableFrom(ICarteYuGiOh.class);
			final var maxSize = isSimple ? size : (int) Math.ceil(size / 2d);
			var monstersSize = 0;
			var specialsSize = 0;

			while (i < CARDS.size()) {
				final var card = CARDS.get(i);
				final var isCard = entityBeanType.isAssignableFrom(card.getClass()) && (
					entityBeanType.isAssignableFrom(AMonstre.class) || entityBeanType.isAssignableFrom(APiegeEtMagie.class)
				);

				final var canAddMonster = card instanceof AMonstre && monstersSize < maxSize;
				final var canAddSpecial = card instanceof APiegeEtMagie && specialsSize < maxSize;
				final var canAdd = isCard && (canAddMonster || canAddSpecial);

				if (isSimple && deck.cardsCount() == maxSize) return deck;

				if (monstersSize + specialsSize == size) return deck;

				if (canAdd) {
					if (card instanceof AMonstre) monstersSize++;
					else specialsSize++;

					//noinspection unchecked
					deck.addCard((T) card);
				}
				i++;
			}

			return deck;
		}
	}
}
