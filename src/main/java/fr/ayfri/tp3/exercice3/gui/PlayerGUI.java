package fr.ayfri.tp3.exercice3.gui;

import fr.ayfri.tp3.exercice3.board.Deck;
import fr.ayfri.tp3.exercice3.board.Player;
import fr.ayfri.tp3.exercice3.cards.AMonstre;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
		selectCardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		selectCardButton.addActionListener(e -> {
			selectingCard = !selectingCard;
			if (selectingCard) selectCardButton.setBackground(new Color(30, 30, 50));
			else selectCardButton.setBackground(null);
		});

		final var pane = root.getContentPane();
		pane.add(selectCardButton);

		pane.add(getImage(firstDeckCard, mainDeck, getMainDeckPos()));
		pane.add(getImage(extraDeckCard, extraDeck, getExtraDeckPos()));
		pane.add(getImage(sideDeckCard, sideDeck, getSideDeckPos(), true));
		final JPanel monsterCards = getMonsterCards(player.getField().getMonsterArea());
		pane.add(monsterCards);
		monsterCards.setLocation(274, 211);
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
						final var deckGui = new DeckGUI<>(root, PlayerGUI.this, deck);
						deckGui.display();
					} catch (final IOException ex) {
						throw new RuntimeException(ex);
					}
				}
			});
		}

		return label;
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

	private @NotNull JPanel getMonsterCards(final @NotNull List<AMonstre> cards) {
		final var panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setName("Monsters");

		final var firstCardPos = new Point(274, 211);
		final var gap = 78;
		var index = 0;
		for (final var monster : cards) {
			final var label = Utils.getImage(monster, CARD_SIZE);
			final var monsterPos = new Point(firstCardPos.x + index * (gap + CARD_SIZE.width), firstCardPos.y);

			label.setLocation(monsterPos);
			panel.add(label);
			index++;
		}
		return panel;
	}

	public void updateMonsterCards() {
		final var pane = root.getContentPane();
		for (final var component : pane.getComponents()) {
			@Nullable final var name = component.getName();
			if (Objects.equals(name, "Monsters")) pane.remove(component);
		}
		final var monsterCards = getMonsterCards(player.getField().getMonsterArea());
		pane.add(monsterCards);
		pane.setComponentZOrder(monsterCards, 0);
	}

	private @NotNull Point getSideDeckPos() {
		return isFirst ? new Point(42, 18) : new Point(300, 300);
	}
}
