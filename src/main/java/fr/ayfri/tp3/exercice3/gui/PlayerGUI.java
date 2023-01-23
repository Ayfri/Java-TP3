package fr.ayfri.tp3.exercice3.gui;

import fr.ayfri.tp3.exercice3.board.Deck;
import fr.ayfri.tp3.exercice3.board.Player;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public record PlayerGUI(@NotNull JFrame root, @NotNull Player player, boolean isFirst) {
	public static final @NotNull Dimension CARD_SIZE = new Dimension(145, 205);

	private static boolean selectingCard = false;

	public static boolean isSelectingCard() {
		return selectingCard;
	}

	public void display() throws IOException {
		final var mainDeck = player.getField().getMainDeck();
		final var firstDeckCard = mainDeck.first();

		final var extraDeck = player.getField().getExtraDeck();
		final var extraDeckCard = extraDeck.first();

		final var sideDeck = player.getField().getSideDeck();
		final var sideDeckCard = sideDeck.first();
		if (firstDeckCard == null || extraDeckCard == null || sideDeckCard == null) return;

		final var selectCardButton = new JButton("S\u00E9lectionner");
		selectCardButton.setSize(200, 50);
		selectCardButton.setLocation(376, 728);
		selectCardButton.addActionListener(e -> {
			selectingCard = !selectingCard;
			selectCardButton.setFocusPainted(selectingCard);
		});
		System.out.printf("selected card button : %s\n", selectCardButton);
		root.getContentPane().add(selectCardButton);

		root.getContentPane().add(getImage(firstDeckCard, mainDeck, getMainDeckPos()));
		root.getContentPane().add(getImage(extraDeckCard, extraDeck, getExtraDeckPos()));
		root.getContentPane().add(getImage(sideDeckCard, sideDeck, getSideDeckPos(), true));
	}

	private <T extends ICarteYuGiOh> JLabel getImage(
			final @NotNull ICarteYuGiOh card,
			final @NotNull Deck<T> deck,
			final @NotNull Point position
	) {
		return getImage(card, deck, position, false);
	}

	private @NotNull Point getMainDeckPos() {
		return isFirst ? new Point(1403, 627) : new Point(300, 300);
	}

	private @NotNull Point getExtraDeckPos() {
		return isFirst ? new Point(35, 627) : new Point(300, 300);
	}

	private <T extends ICarteYuGiOh> JLabel getImage(
			final @NotNull ICarteYuGiOh card,
			final @Nullable Deck<T> deck,
			final @NotNull Point position,
			final boolean tilted
	) {
		final var label = Utils.getImage(card, tilted);
		label.setLocation(position);

		if (deck != null) {
			label.addMouseListener(new MouseAdapterBordered() {
				@Override
				public void mouseClicked(final @NotNull MouseEvent e) {
					try {
						final var deckGui = new DeckGUI<>(root, deck);
						deckGui.display();
					} catch (final IOException ex) {
						throw new RuntimeException(ex);
					}
				}
			});
		}

		return label;
	}

	private @NotNull Point getSideDeckPos() {
		return isFirst ? new Point(42, 18) : new Point(300, 300);
	}
}
