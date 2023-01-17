package fr.ayfri.tp3.exercice2;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

	private void createCards() {
		final var cardHeight = 94;
		final var cardWidth = 69;
		final var values = Card.CardType.values();
		final var horizontalCards = cardsAtlas.getWidth() / (cardWidth + 1);
		final var verticalCards = cardsAtlas.getHeight() / (cardHeight + 1);

		System.out.printf("image: %d x %d, oneCard : %d %d, cards : %d x %d %n", cardsAtlas.getHeight(), cardsAtlas.getWidth(), cardHeight, cardWidth, horizontalCards, verticalCards);
//		cardsAtlas.tile
		for (var i = 0; i < verticalCards; i++) {
			for (var j = 0; j < horizontalCards; j++) {
				final var cardImage = cardsAtlas.getSubimage((i * cardWidth) + 1, (i * cardHeight) + 1, cardWidth, cardHeight);
				final var card = new Card(j, values[i]);
				allCards.put(card, cardImage);
			}
		}
	}

	private @Nullable Card takeRandomCard(final @NotNull Random random) {
		final var availableCards = new HashSet<>(allCards.keySet());
		availableCards.removeAll(takenCards);
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
		final var image = new JLabel(new ImageIcon(allCards.get(card)));
		root.add(image);
	}

	@Override
	public void run(final @NotNull JFrame frame) {
		createCards();

		frame.setSize(cardsAtlas.getWidth(), cardsAtlas.getHeight());
		final var random = new Random();
		final var imagesPanel = new JPanel();
		frame.add(imagesPanel);

		for (var i = 0; i < allCards.size(); i++) {
			final var card = takeRandomCard(random);
			if (card == null) break;
			displayCard(imagesPanel, card);
		}
	}
}
