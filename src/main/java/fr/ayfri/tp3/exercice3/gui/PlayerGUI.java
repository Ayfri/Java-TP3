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
import java.util.Collection;
import java.util.Objects;

public final class PlayerGUI {
	public static final @NotNull Dimension CARD_SIZE = new Dimension(145, 205);
	private final @NotNull JFrame root;
	private final @NotNull Player player;
	private boolean isSelecting = false;
	private @Nullable JButton selectCardButton;

	public PlayerGUI(@NotNull final JFrame root, @NotNull final Player player) {
		this.root = root;
		this.player = player;
	}

	public boolean isSelecting() {
		return isSelecting;
	}

	public void display() throws IOException {
		final var mainDeck = player.getField().getMainDeck();
		final var firstDeckCard = mainDeck.first();

		final var extraDeck = player.getField().getExtraDeck();
		final var extraDeckCard = extraDeck.first();

		final var sideDeck = player.getField().getSideDeck();
		final var sideDeckCard = sideDeck.first();
		if (firstDeckCard == null || extraDeckCard == null || sideDeckCard == null) return;

		final var pane = root.getContentPane();

		pane.add(getImage(firstDeckCard, mainDeck, new Point(1403, 627)));
		pane.add(getImage(extraDeckCard, extraDeck, new Point(35, 627)));
		pane.add(getImage(sideDeckCard, sideDeck, new Point(42, 18), true));

		selectCardButton = new JButton("S\u00E9lectionner");
		selectCardButton.setSize(200, 50);
		selectCardButton.setLocation(376, 728);
		selectCardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		selectCardButton.addActionListener(e -> {
			if (!selectCardButton.isEnabled()) return;

			isSelecting = !isSelecting;
			selectCardButton.setBackground(isSelecting ? new Color(30, 60, 45) : null);
		});

		updateCards();

		pane.add(selectCardButton);
	}

	public void updateCards() {
		final var pane = root.getContentPane();
		for (final var component : pane.getComponents()) {
			@Nullable final var name = component.getName();
			if (Objects.equals(name, "Cards")) pane.remove(component);
		}

		pane.add(getCards(), 0);

		selectCardButton.setEnabled(!(hasMaxMonsters() && hasMaxSpecials()));
		if (!selectCardButton.isEnabled()) isSelecting = false;
	}

	public @NotNull Player getPlayer() {return player;}

	public boolean hasMaxMonsters() {
		return player.getField().getMonsterArea().size() >= 5;
	}

	public boolean hasMaxSpecials() {
		return player.getField().getSpecialArea().size() >= 5;
	}

	private <T extends ICarteYuGiOh> @NotNull JLabel getImage(
			final @NotNull ICarteYuGiOh card,
			final @NotNull Deck<T> deck,
			final @NotNull Point position
	) {
		return getImage(card, deck, position, false);
	}

	private <T extends ICarteYuGiOh> @NotNull JLabel getImage(
			final @NotNull ICarteYuGiOh card,
			final @Nullable Deck<T> deck,
			final @NotNull Point position,
			final boolean tilted
	) {
		final var label = Utils.getImage(card, tilted);
		label.setLocation(position);

		if (deck != null) label.addMouseListener(new MouseAdapterBordered() {
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

		return label;
	}

	private @NotNull JPanel getCards() {
		final var panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setName("Cards");

		setupCardsDisplay(player.getField().getMonsterArea(), panel, new Point(274, 211), 77);
		setupCardsDisplay(player.getField().getSpecialArea(), panel, new Point(315, 446), 58);

		return panel;
	}

	private void setupCardsDisplay(
			final @NotNull Collection<? extends ICarteYuGiOh> cards,
			final @NotNull JPanel panel,
			final @NotNull Point firstCardPos,
			final int gap
	) {
		var index = 0;
		for (final var card : cards) {
			final var label = Utils.getImage(card, CARD_SIZE);
			final var position = new Point(firstCardPos.x + index * (gap + CARD_SIZE.width), firstCardPos.y);

			label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			label.setLocation(position);
			panel.add(label);

			index++;
		}
	}
}
