package fr.ayfri.tp3.exercice2;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.ayfri.tp3.Exercice;
import fr.ayfri.tp3.GraphicalExercice;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permettant de lancer le sélecteur d'exercices pour l'exercice 2.
 *
 * @author Ayfri
 */
public final class GraphicalMenu extends Exercice {
	private final @NotNull List<GraphicalExercice> exercices = new ArrayList<>();

	/**
	 * Créé le menu graphique.
	 */
	public GraphicalMenu() {
		title = "Exercice 2";
		description = """
			    Affiche le menu graphique pour l'exercice 2.
			""";
	}

	/**
	 * Créé le menu de sélection des exercices.
	 *
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main(final String @NotNull [] args) {
		new GraphicalMenu().run();
	}

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
			UIManager.put("TextComponent.arc", 5);
		} catch (final UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		GraphicalExercice.setUIFont(new FontUIResource("Arial", Font.PLAIN, 15));

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
}
