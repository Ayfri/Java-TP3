package fr.ayfri.tp3.exercice3.gui;

import fr.ayfri.tp3.exercice3.cards.AMonstre;
import fr.ayfri.tp3.exercice3.cards.APiegeEtMagie;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Le record CardGUI permettant d'afficher une carte ainsi que ses informations.
 * Permet aussi de sauvegarder l'image dans un JSON.
 *
 * @param root La fenêtre principale.
 * @param card La carte à afficher.
 * @param <T> Le type de carte.
 *
 * @author Ayfri
 */
public record CardGUI<T extends ICarteYuGiOh>(@NotNull JFrame root, @NotNull T card) {
	/**
	 * La police d'écriture pour les petites informations.
	 */
	private static final @NotNull Font SMALL_FONT = new Font("Arial", Font.PLAIN, 20);
	/**
	 * La police d'écriture pour les grandes informations.
	 */
	private static final @NotNull Font BIG_FONT = new Font("Arial", Font.BOLD, 25);

	/**
	 * Affiche la carte.
	 */
	public void display() {
		final var panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 0, 0, .8f));
		panel.setSize(root.getContentPane().getWidth(), root.getContentPane().getHeight());

		final var centerWidth = panel.getWidth() / 2;
		final var centerHeight = panel.getHeight() / 2;

		final var cardSize = new Dimension((int) (PlayerGUI.CARD_SIZE.width * 2.75), (int) (PlayerGUI.CARD_SIZE.height * 2.75));
		final var imageLabel = Utils.getImage(card, cardSize);
		panel.add(imageLabel);
		imageLabel.setLocation(centerWidth - imageLabel.getWidth() / 2, centerHeight - imageLabel.getHeight() / 2);

		panel.add(displayText(card.getDescription(), 210, 160, 385, 570));
		final var name = displayText(card.getName(), 645, 75, 600, 50, true);
		final var size = name.getFontMetrics(name.getFont()).stringWidth(name.getText());
		name.setLocation(panel.getWidth() / 2 - size / 2, 75);
		panel.add(name);

		panel.add(displayText("Type : " + card.getType(), 1000, 160, 400, 40));
		panel.add(displayText("Num\u00E9ro : " + card.getId(), 1000, 200, 400, 40));

		if (card instanceof AMonstre monsterCard) {
			panel.add(displayText("Attribut : " + monsterCard.getAttribute().getTranslation(), 1000, 240, 400, 40));
			panel.add(displayText("Attaque : " + monsterCard.getAttack(), 1000, 280, 400, 40));
			panel.add(displayText("D\u00E9fense : " + monsterCard.getDefense(), 1000, 320, 400, 40));
			panel.add(displayText("Niveau : " + monsterCard.getLevel(), 1000, 360, 400, 40));
		} else if (card instanceof APiegeEtMagie specialCard)
			panel.add(displayText("Ic\u00F4ne : " + specialCard.getIcon().getTranslation(), 1000, 240, 400, 40));

		final var saveBtn = new JButton("Sauvegarder");
		panel.add(saveBtn);

		saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		saveBtn.setSize(200, 50);
		saveBtn.setLocation(panel.getWidth() / 2 - saveBtn.getWidth() / 2, panel.getHeight() - 100);

		saveBtn.addActionListener(e -> {
			try {
				final var fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				final var resultCode = fileChooser.showDialog(saveBtn, "Sauvegarder");

				if (resultCode == JFileChooser.APPROVE_OPTION) {
					final var file = fileChooser.getSelectedFile();
					if (file == null) return;

					card.saveTo(new File(file, "card.json"));
				}
			} catch (final IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final @NotNull MouseEvent e) {
				root.getContentPane().remove(panel);
				root.getContentPane().revalidate();
				root.getContentPane().repaint();
			}
		});

		root.getContentPane().add(panel, 0);
	}

	/**
	 * Affiche du texte en petit.
	 *
	 * @param text Le texte à afficher.
	 * @param x La position en X.
	 * @param y La position en Y.
	 * @param width La largeur.
	 * @param height La hauteur.
	 *
	 * @return Le composant.
	 */
	private static @NotNull JTextArea displayText(final @NotNull String text, final int x, final int y, final int width, final int height) {
		return displayText(text, x, y, width, height, false);
	}

	/**
	 * Affiche du texte.
	 *
	 * @param text Le texte à afficher.
	 * @param x La position en X.
	 * @param y La position en Y.
	 * @param width La largeur.
	 * @param height La hauteur.
	 * @param big Si le texte est gros.
	 *
	 * @return Le composant.
	 */
	private static @NotNull JTextArea displayText(
		final @NotNull String text, final int x, final int y, final int width, final int height, final boolean big
	) {
		final var textArea = new JTextArea();
		textArea.setBounds(x, y, width, height);
		textArea.setOpaque(false);
		textArea.setText(text);
		textArea.setFocusable(false);
		textArea.setFont(big ? BIG_FONT : SMALL_FONT);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);

		return textArea;
	}
}
