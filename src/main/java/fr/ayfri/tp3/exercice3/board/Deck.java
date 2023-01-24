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

	@Contract(pure = true)
	@Override
	public @NotNull String toString() {
		return "Deck{" + "size=" + size + ", cards=" + cards + '}';
	}

	public void addCard(final @NotNull T card) {
		final var count = (int) cards.stream().filter(c -> c.getName().equals(card.getName())).count();

		if (count >= 3 || cards.size() >= size) return;

		cards.add(card);
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
			final var isSimple = !entityBeanType.isAssignableFrom(ICarteYuGiOh.class);
			final var maxSize = isSimple ? size : (int) Math.ceil(size / 2d);
			var monstersSize = 0;
			var specialsSize = 0;

			while (i < CARDS.size()) {
				final var card = CARDS.get(i);
				final var isCard = entityBeanType.isAssignableFrom(card.getClass()) &&
				                   (
						                   entityBeanType.isAssignableFrom(AMonstre.class) ||
						                   entityBeanType.isAssignableFrom(APiegeEtMagie.class)
				                   );

				final var canAddMonster = card instanceof AMonstre && monstersSize < maxSize;
				final var canAddSpecial = card instanceof APiegeEtMagie && specialsSize < maxSize;
				final var canAdd = isCard && (canAddMonster || canAddSpecial);

				if (isSimple && deck.cardsCount() == maxSize) break;

				if (monstersSize + specialsSize == size) break;

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
