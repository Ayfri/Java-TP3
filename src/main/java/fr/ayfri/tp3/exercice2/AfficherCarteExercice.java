package fr.ayfri.tp3.exercice2;

import fr.ayfri.tp3.GraphicalExercice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

public final class AfficherCarteExercice extends GraphicalExercice {
	private final @NotNull BufferedImage cardsAtlas;
	private final @NotNull Map<Card, BufferedImage> allCards = new HashMap<>();
	private final @NotNull List<Card> takenCards = new ArrayList<>();

	public AfficherCarteExercice() {
		title = "Afficher cartes";
		description = "Afficher des cartes.";

		try {
			cardsAtlas = ImageIO.read(new File("src/main/resources/cards.png"));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String @NotNull [] args) {
		new AfficherCarteExercice().run();
	}

	@Override
	public void run(final @NotNull JFrame frame) {
		createCards();
		frame.setSize((int) (cardsAtlas.getWidth() * 1.1), (int) (cardsAtlas.getHeight() * 1.3));

		final var imagesPanel = new JPanel();
		frame.add(imagesPanel);

		final var buttonPanel = new JPanel();
		frame.add(buttonPanel, BorderLayout.SOUTH);

		createDrawButton(buttonPanel, imagesPanel);
	}

	private void createCards() {
		final var cardHeight = cardsAtlas.getHeight() / 4;
		final var cardWidth = cardsAtlas.getWidth() / 13;
		final var values = Card.CardType.values();
		final var horizontalCards = cardsAtlas.getWidth() / cardWidth;
		final var verticalCards = cardsAtlas.getHeight() / cardHeight;

		for (var y = 0; y < verticalCards; y++) {
			for (var x = 0; x < horizontalCards; x++) {
				final var card = new Card(x, values[y]);
				final var cardImage = cardsAtlas.getSubimage(x * cardWidth, y * cardHeight, cardWidth, cardHeight);
				allCards.put(card, cardImage);
			}
		}
	}

	private void createDrawButton(final @NotNull JComponent root, final @NotNull JComponent imagesPanel) {
		final var button = new JButton("Tirer une carte");
		final var random = new Random();
		button.addActionListener(e -> {
			final var card = takeRandomCard(random);
			assert card != null;
			if (takenCards.size() == allCards.size()) {
				button.setText("Toutes les cartes sont tir\u00E9es !");
				button.setEnabled(false);
				button.removeActionListener(button.getActionListeners()[0]);
			}
			displayCard(imagesPanel, card);
		});
		root.add(button);
	}

	private @Nullable Card takeRandomCard(final @NotNull Random random) {
		final var availableCards = new HashSet<>(allCards.keySet());
		takenCards.forEach(availableCards::remove);
		try {
			final var cards = new ArrayList<>(availableCards);
			Collections.shuffle(cards, random);
			takenCards.add(cards.get(0));
			return cards.get(0);
		} catch (final IndexOutOfBoundsException e) {
			return null;
		}
	}

	private void displayCard(final @NotNull JComponent root, final @NotNull Card card) {
		final var cardImage = allCards.get(card);
		final var image = new JLabel(new ImageIcon(cardImage));
		root.add(image);
		root.revalidate();
		root.repaint();
	}
}
