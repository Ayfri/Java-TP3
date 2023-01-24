package fr.ayfri.tp3.exercice3.gui;

import fr.ayfri.tp3.exercice3.YuGiOhExercice;
import fr.ayfri.tp3.exercice3.board.Deck;
import fr.ayfri.tp3.exercice3.cards.AMonstre;
import fr.ayfri.tp3.exercice3.cards.APiegeEtMagie;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public record DeckGUI<T extends ICarteYuGiOh>(@NotNull JFrame root, @NotNull PlayerGUI playerGUI, @NotNull Deck<T> deck) {
	public void display() throws IOException {
		final var margin = 30;
		final var cardsPerRow = (int) Math.floor((YuGiOhExercice.SCREEN_SIZE.width * .95 + margin) / (PlayerGUI.CARD_SIZE.width + margin));
		final var rowsCount = (int) Math.ceil(deck.size() / (double) cardsPerRow);

		final var panel = new JPanel();
		panel.setBorder(new EmptyBorder(margin, margin, margin, margin));
		panel.setLayout(new GridLayout(rowsCount, cardsPerRow, margin - 5, margin - 5));
		panel.setBackground(new Color(0, 0, 0, .8f));

		final var scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(12);

		final var isPlayerSelectingCards = playerGUI.isSelecting();
		final var selectedMonsters = playerGUI.getPlayer().getField().getMonsterArea();
		final var selectedSpecial = playerGUI.getPlayer().getField().getSpecialArea();

		final var pane = root.getContentPane();
		for (var i = 0; i < deck.size(); i++) {
			final var card = deck.get(i);
			final var cardPanel = new JPanel();
			cardPanel.setLayout(new GridBagLayout());
			cardPanel.setOpaque(false);

			final var label = Utils.getImage(card);
			label.setOpaque(false);
			label.setBorder(new EmptyBorder(4, 4, 4, 4));
			cardPanel.add(label);
			panel.add(cardPanel);


			final boolean canceled;
			if (card instanceof AMonstre monster) {
				canceled = selectedMonsters.contains(monster) || playerGUI.hasMaxMonsters();
			} else if (card instanceof APiegeEtMagie special) {
				canceled = selectedSpecial.contains(special) || playerGUI.hasMaxSpecials();
			} else {
				canceled = false;
			}

			final var isCardSelectionCanceled = isPlayerSelectingCards && canceled;

			label.addMouseListener(new MouseAdapterBordered(isCardSelectionCanceled) {
				@Override
				public void mouseClicked(final @NotNull MouseEvent e) {
					if (isCardSelectionCanceled) return;

					pane.remove(scrollPane);
					pane.revalidate();
					pane.repaint();

					if (isPlayerSelectingCards) {
						final var field = playerGUI.getPlayer().getField();

						if (card instanceof AMonstre monsterCard) field.getMonsterArea().add(monsterCard);
						else if (card instanceof APiegeEtMagie specialCard) field.getSpecialArea().add(specialCard);

						playerGUI.updateCards();
						return;
					}

					final var cardGUI = new CardGUI<>(root, card);
					cardGUI.display();
				}
			});
		}

		pane.add(scrollPane, 0);
		pane.revalidate();

		scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> pane.repaint());

		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final @NotNull MouseEvent e) {
				pane.remove(scrollPane);
				pane.revalidate();
				pane.repaint();
			}
		});
	}
}
