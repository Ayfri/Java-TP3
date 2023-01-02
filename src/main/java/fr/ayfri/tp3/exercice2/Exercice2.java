package fr.ayfri.tp3.exercice2;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.ayfri.Exercice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class Exercice2 extends Exercice {
	public Exercice2() {
		title = "Exercice 2";
		description = """
					Afficher 2 boutons dans une fenêtre.
				""";
	}

	public static void main(final String @NotNull [] args) {
		new Exercice2().run();
	}

	public static void setUIFont(@NotNull final FontUIResource f) {
		final var keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			final var key = keys.nextElement();
			final var value = UIManager.get(key);
			if (value instanceof FontUIResource orig) {
				final var font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}

	private static void createSimpleButton(final @NotNull JFrame root, final @NotNull String text) {
		createSimpleButton(root, text, null);
	}

	private static void createSimpleButton(final @NotNull JFrame root, final @NotNull String text, final @Nullable ImageIcon image) {
		final var button = new JButton(text, image);
		button.addActionListener(e -> JOptionPane.showMessageDialog(root,
				"Vous avez appuy\u00E9 sur : " + ((JButton) e.getSource()).getText(),
				"Message",
				JOptionPane.INFORMATION_MESSAGE
		));
		root.add(button);
	}

	private static ImageIcon resize(final @NotNull ImageIcon imageIcon, final int width, final int height) {
		final var image = imageIcon.getImage();
		final var resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	private static ImageIcon image(final @NotNull String filename, final int width, final int height) {
		final var imageIcon = new ImageIcon("src/main/resources/" + filename);
		imageIcon.setDescription("Location : src/main/resources/" + filename + " | Size : " + width + "x" + height);
		return resize(imageIcon, width, height);
	}

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
			UIManager.put("TextComponent.arc", 5);
		} catch (final UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setUIFont(new FontUIResource("Arial", Font.PLAIN, 15));

		final var frame = new JFrame("Test des boutons");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());

		createSimpleButton(frame, "Bouton simple");
		frame.add(Box.createRigidArea(new Dimension(10, 0)));
		createSimpleButton(frame, "Bouton avec ic\u00F4ne", image("fantasy_btn.png", 32, 32));

		while (frame.isVisible()) {
			try {
				Thread.sleep(100);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
