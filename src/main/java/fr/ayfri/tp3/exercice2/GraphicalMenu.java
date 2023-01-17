package fr.ayfri.tp3.exercice2;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.ayfri.Exercice;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class GraphicalMenu extends Exercice {
	private final @NotNull List<GraphicalExercice> exercices = new ArrayList<>();

	public GraphicalMenu() {
		title = "Exercice 2";
		description = """
				    Affiche le menu graphique pour l'exercice 2.
				""";
	}

	public static void main(final String @NotNull [] args) {
		new GraphicalMenu().run();
	}

	public static void setUIFont(@NotNull final FontUIResource f) {
		for (final var key : UIManager.getDefaults().keySet()) {
			final var value = UIManager.get(key);
			if (value instanceof FontUIResource orig) {
				final var font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}

	/**
	 * Créé le menu de sélection des exercices.
	 * Via des boutons, on peut choisir l'exercice à lancer.
	 *
	 * @param panel Le panel où afficher le menu.
	 */
	private void createExercicesMenu(final @NotNull JPanel panel) {
		for (final var exercice : exercices) {
			final var buttonPanel = new JPanel();
			buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			buttonPanel.setMaximumSize(new Dimension(300, 100));

			final var button = new JButton(exercice.getTitle());
			button.addActionListener(e -> exercice.run());
			button.setToolTipText(exercice.getDescription());
			button.setPreferredSize(new Dimension(180, 45));

			buttonPanel.add(button);
			panel.add(buttonPanel);
		}
	}

	public void run() {
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
			UIManager.put("TextComponent.arc", 5);
		} catch (final UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setUIFont(new FontUIResource("Arial", Font.PLAIN, 15));

		exercices.add(new JButtonExercice());
		exercices.add(new BoxLayoutExercice());
		exercices.add(new AfficherCarteExercice());

		final var frame = new JFrame("Menu de s\u00E9lection");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		final var centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(80, 20, 80, 20));
		createExercicesMenu(centerPanel);
		frame.add(centerPanel);

		while (frame.isVisible()) {
			try {
				Thread.sleep(100);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
