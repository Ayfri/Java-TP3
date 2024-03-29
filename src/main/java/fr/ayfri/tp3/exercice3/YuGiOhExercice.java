package fr.ayfri.tp3.exercice3;

import fr.ayfri.tp3.GraphicalExercice;
import fr.ayfri.tp3.exercice3.board.Board;
import fr.ayfri.tp3.exercice3.gui.PlayerGUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * L'exercice 3 du TP 3 sur les cartes YuGiOh.
 *
 * @author Ayfri
 */
public final class YuGiOhExercice extends GraphicalExercice {
	public static final @NotNull Dimension SCREEN_SIZE = new Dimension(1600, 900);
	private final @Nullable BufferedImage background;

	/**
	 * Constructeur de l'exercice.
	 */
	public YuGiOhExercice() {
		title = "TP 3.3 : Classes -> YuGiOh ";
		description = """
				Affiche un tapis de jeu de YuGiOh.
				Possibilité de placer des cartes.
			""";

		try {
			background = ImageIO.read(new File("src/main/resources/game_mat.jpg"));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Méthode principale pour lancer l'exercice.
	 *
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main(final String @NotNull [] args) {
		new YuGiOhExercice().run();
	}

	@Override
	public void run(final @NotNull JFrame frame) {
		frame.setTitle("YuGiOh");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SCREEN_SIZE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		final var board = new Board();

		try {
			final var firstPlayerGUI = new PlayerGUI(frame, board.getFirstPlayer());
			firstPlayerGUI.display();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		displayBackground(frame);

		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}

	/**
	 * Affiche l'image de fond.
	 *
	 * @param root La fenêtre principale.
	 */
	private void displayBackground(final @NotNull JFrame root) {
		if (background == null) return;
		final var image = new JLabel(new ImageIcon(background));
		root.getContentPane().add(image);
	}
}
