package fr.ayfri.tp3.exercice2;

import fr.ayfri.tp3.GraphicalExercice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public final class JButtonExercice extends GraphicalExercice {
	public JButtonExercice() {
		title = "Test des boutons";
		description = "Afficher 2 boutons dans une fen\u00EAtre.";
	}

	public static void main(final String @NotNull [] args) {
		new JButtonExercice().run();
	}

	private static void createSimpleButton(final @NotNull JFrame root, final @NotNull String text) {
		createSimpleButton(root, text, null);
	}

	private static void createSimpleButton(final @NotNull JFrame root, final @NotNull String text, final @Nullable ImageIcon image) {
		final var button = new JButton(text, image);
		button.addActionListener(e ->
				JOptionPane.showMessageDialog(root,
						"Vous avez appuy\u00E9 sur : " + ((JButton) e.getSource()).getText(),
						"Message",
						JOptionPane.INFORMATION_MESSAGE
				)
		);
		root.add(button);
	}

	private static ImageIcon resize(final @NotNull ImageIcon imageIcon, final int width, final int height) {
		final var image = imageIcon.getImage();
		final var resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	private static ImageIcon image(final @NotNull String filename, final int width, final int height) {
		final var imageIcon = new ImageIcon("src/main/resources/" + filename);
		imageIcon.setDescription("Location : src/main/resources/%s | Size : %dx%d".formatted(filename, width, height));
		return resize(imageIcon, width, height);
	}

	@Override
	public void run(final @NotNull JFrame frame) {
		frame.setSize(450, 200);
		frame.setLayout(new GridBagLayout());

		createSimpleButton(frame, "Bouton simple");
		frame.add(Box.createRigidArea(new Dimension(10, 0)));
		createSimpleButton(frame, "Bouton avec ic\u00F4ne", image("fantasy_btn.png", 32, 32));
	}
}
