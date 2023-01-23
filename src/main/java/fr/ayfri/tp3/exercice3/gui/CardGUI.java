package fr.ayfri.tp3.exercice3.gui;

import fr.ayfri.tp3.exercice3.cards.AMonstre;
import fr.ayfri.tp3.exercice3.cards.APiegeEtMagie;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public record CardGUI<T extends ICarteYuGiOh>(@NotNull JFrame root, @NotNull T card) {
	private static final @NotNull Font font = new Font("Arial", Font.PLAIN, 20);
	private static final @NotNull Font bigFont = new Font("Arial", Font.BOLD, 25);

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
		} else if (card instanceof APiegeEtMagie specialCard) {
			panel.add(displayText("Ic\u00F4ne : " + specialCard.getIcon().getTranslation(), 1000, 240, 400, 40));
		}
//		panel.add(displayText("Numéro : " + card.getId(), 1020, 250, 400, 50, true));

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final @NotNull MouseEvent e) {
				root.getContentPane().remove(panel);
				root.getContentPane().revalidate();
				root.getContentPane().repaint();
			}
		});

		root.getContentPane().add(panel);
		root.getContentPane().setComponentZOrder(panel, 0);
	}

	private @NotNull JTextArea displayText(final @NotNull String text, final int x, final int y, final int width, final int height) {
		return displayText(text, x, y, width, height, false);
	}

	private @NotNull JTextArea displayText(final @NotNull String text, final int x, final int y, final int width, final int height, final boolean big) {
		final var textArea = new JTextArea();
		textArea.setBounds(x, y, width, height);
		textArea.setOpaque(false);
		textArea.setText(text);
		textArea.setFocusable(false);
		textArea.setFont(big ? bigFont : font);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);

		return textArea;
	}
}
