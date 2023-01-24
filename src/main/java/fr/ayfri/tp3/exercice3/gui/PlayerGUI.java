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

/**
 * La classe PlayerGUI permettant de créer une interface graphique pour un joueur de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public final class PlayerGUI {
	/**
	 * La taille des cartes.
	 */
	public static final @NotNull Dimension CARD_SIZE = new Dimension(145, 205);
	/**
	 * La fenêtre racine.
	 */
	private final @NotNull JFrame root;
	/**
	 * Le joueur.
	 */
	private final @NotNull Player player;
	/**
	 * Si le joueur est mode sélection.
	 */
	private boolean isSelecting = false;
	/**
	 * Le bouton de sélection pour activer ou désactiver le mode sélection.
	 */
	private @Nullable JButton selectCardButton;

	/**
	 * Crée une interface graphique pour un joueur de Yu-Gi-Oh.
	 *
	 * @param root La fenêtre racine.
	 * @param player Le joueur.
	 */
	public PlayerGUI(@NotNull final JFrame root, @NotNull final Player player) {
		this.root = root;
		this.player = player;
	}

	/**
	 * Getter de la sélection.
	 *
	 * @return Si le joueur est mode sélection.
	 */
	public boolean isSelecting() {
		return isSelecting;
	}

	/**
	 * Affiche l'interface graphique du joueur.<br>
	 * <b>Le chargement d'une image via un fichier JSON n'est pas implémenté.</b>
	 *
	 * @throws IOException Si une erreur d'entrée-sortie est survenue notamment lors de la récupération des images.
	 */
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
		selectCardButton.setLocation(375, 728);
		selectCardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		selectCardButton.addActionListener(e -> {
			if (!selectCardButton.isEnabled()) return;

			isSelecting = !isSelecting;
			selectCardButton.setBackground(isSelecting ? new Color(30, 60, 45) : null);
		});

		final var loadBtn = new JButton("Charger carte");
		loadBtn.setSize(200, 50);
		loadBtn.setLocation(1025, 728);
		loadBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loadBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(
				root,
				"D\u00E9sol\u00E9, la fonctionnalit\u00E9 de r\u00E9cup\u00E9rer une carte depuis un JSON ne marche pas."
			);

			/*
			final var fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setFileFilter(new FileNameExtensionFilter("JSON", "json"));
			final var resultCode = fileChooser.showDialog(loadBtn, "Charger");

			if (resultCode == JFileChooser.APPROVE_OPTION) {
				final var file = fileChooser.getSelectedFile();
				if (file == null) return;

				try {
					final var card = ICarteYuGiOh.loadFrom(file);
					System.out.println(card);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			} */
		});

		updateCards();

		pane.add(loadBtn);
		pane.add(selectCardButton);
	}

	/**
	 * Récupère un JLabel contenant l'image de la carte.
	 *
	 * @param card La carte.
	 * @param deck Le deck de la carte.
	 * @param position La position de l'image.
	 * @param <T> Le type de la carte.
	 *
	 * @return Le JLabel contenant l'image de la carte.
	 */
	private <T extends ICarteYuGiOh> @NotNull JLabel getImage(
		final @NotNull ICarteYuGiOh card, final @NotNull Deck<T> deck, final @NotNull Point position
	) {
		return getImage(card, deck, position, false);
	}

	/**
	 * Récupère un JLabel contenant l'image de la carte, avec possibilité de la tourner.
	 *
	 * @param card La carte.
	 * @param deck Le deck de la carte.
	 * @param position La position de l'image.
	 * @param tilted Si la carte doit être tournée à 90°.
	 * @param <T> Le type de la carte.
	 *
	 * @return Le JLabel contenant l'image de la carte.
	 */
	private <T extends ICarteYuGiOh> @NotNull JLabel getImage(
		final @NotNull ICarteYuGiOh card, final @Nullable Deck<T> deck, final @NotNull Point position, final boolean tilted
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

	/**
	 * Met à jour l'affichage des cartes jouées.
	 */
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

	/**
	 * Récupère un panel contenant les cartes jouées.
	 *
	 * @return Le panel contenant les cartes jouées.
	 */
	private @NotNull JPanel getCards() {
		final var panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setName("Cards");

		setupCardsDisplay(player.getField().getMonsterArea(), panel, new Point(274, 211), 77);
		setupCardsDisplay(player.getField().getSpecialArea(), panel, new Point(315, 446), 58);

		return panel;
	}

	/**
	 * Getter pour savoir si le joueur a atteint le nombre maximum de monstres joués.
	 *
	 * @return Si le joueur a atteint le nombre maximum de monstres joués.
	 */
	public boolean hasMaxMonsters() {
		return player.getField().getMonsterArea().size() >= 5;
	}

	/**
	 * Getter pour savoir si le joueur a atteint le nombre maximum de cartes spéciales jouées.
	 *
	 * @return Si le joueur a atteint le nombre maximum de cartes spéciales jouées.
	 */
	public boolean hasMaxSpecials() {
		return player.getField().getSpecialArea().size() >= 5;
	}

	/**
	 * Met en place l'affichage d'une liste de cartes à jouer.
	 *
	 * @param cards La liste de cartes à jouer.
	 * @param panel Le panel dans lequel afficher les cartes.
	 * @param firstCardPos La position de la première carte.
	 * @param gap L'écart entre les cartes.
	 */
	private static void setupCardsDisplay(
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

	/**
	 * Getter du joueur.
	 *
	 * @return Le joueur.
	 */
	public @NotNull Player getPlayer() {return player;}
}
