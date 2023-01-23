package fr.ayfri.tp3.exercice3.board;

import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import fr.ayfri.tp3.exercice3.data.DataManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Deck<T extends ICarteYuGiOh> {
	private final int size;
	private final @NotNull List<T> cards;

	public Deck(final int size) {
		this.size = size;
		cards = new ArrayList<>(size);
	}

	public int size() {
		return size;
	}

	public @NotNull List<T> getCards() {
		return cards;
	}

	public @Nullable T get(final int index) {
		return cards.get(index);
	}

	public int cardsCount() {
		return cards.size();
	}

	@Override
	public String toString() {
		return "Deck{" + "size=" + size + ", cards=" + cards + '}';
	}

	public boolean addCard(final @NotNull T card) {
		final var count = (int) cards.stream().filter(c -> c.getName().equals(card.getName())).count();

		if (count >= 3 || cards.size() >= size) return false;

		cards.add(card);
		return true;
	}

	public @Nullable T first() {
		return cards.get(0);
	}

	static final class Factory<T extends ICarteYuGiOh> {
		private static final @NotNull List<ICarteYuGiOh> CARDS = DataManager.getCards();
		private final @NotNull Class<T> entityBeanType;

		public Factory(final @NotNull Class<T> entityBeanType) {
			this.entityBeanType = entityBeanType;
		}

		public @NotNull Deck<T> createDeck(final int size) {
			Collections.shuffle(CARDS);

			final var deck = new Deck<T>(size);
			var i = 0;
			while (deck.cardsCount() < size && i < CARDS.size()) {
				final var card = CARDS.get(i);

				if (entityBeanType.isAssignableFrom(card.getClass())) {
					//noinspection unchecked
					deck.addCard((T) card);
				}
				i++;
			}

			return deck;
		}
	}
}
