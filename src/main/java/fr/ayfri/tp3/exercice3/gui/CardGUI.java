package fr.ayfri.tp3.exercice3.gui;

import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public record CardGUI<T extends ICarteYuGiOh>(@NotNull JFrame root, @NotNull T card) {
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

		final var description = new JTextArea();
		description.setBounds(230, 160, 365, 570);
		description.setOpaque(false);
		description.setText(card.getDescription());
		description.setFocusable(false);
		description.setFont(new Font("Arial", Font.PLAIN, 18));
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);

		panel.add(description);

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
}
