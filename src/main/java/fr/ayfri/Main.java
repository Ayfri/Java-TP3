package fr.ayfri;

import fr.ayfri.inputs.CharInputManager;
import fr.ayfri.tp3.exercice1.Exercice1;
import fr.ayfri.tp3.exercice2.GraphicalMenu;
import fr.ayfri.tp3.exercice3.YuGiOhExercice;
import org.jetbrains.annotations.NotNull;

/**
 * La classe principale pour lancer le menu des exercices.
 *
 * @author Pierre
 */
public final class Main {
	/**
	 * Caractère pour quitter le menu de sélection d'exercices.
	 */
	private static final char EXIT_CHAR = 'q';

	/**
	 * Un constructeur privé pour empêcher l'instanciation de la classe, la méthode {@link Main#main} doit être utilisée.
	 */
	private Main() {}

	/**
	 * La méthode principale pour lancer le menu des exercices.
	 * Crée un gestionnaire d'entrées de caractères et affiche le menu des exercices.
	 * L'utilisateur peut choisir un exercice en entrant le numéro correspondant et l'exercice s'exécute.
	 *
	 * @param args Les arguments de la ligne de commande (non utilisés).
	 */
	public static void main(final String @NotNull [] args) {
		final var menu = new Menu();
		menu.addExercice(new Exercice1());
		menu.addExercice(new GraphicalMenu());
		menu.addExercice(new YuGiOhExercice());

		final var numberOfExercices = menu.getExerciceCount();
		final var validChars = new char[numberOfExercices + 1];
		validChars[0] = EXIT_CHAR;

		for (var i = 0; i < numberOfExercices; i++) {
			validChars[i + 1] = String.valueOf(i + 1).charAt(0);
		}

		final var inputManager = new CharInputManager(
				"Entrez le numéro de l'exercice à exécuter (q pour quitter): ",
				"Numéro d'exercice invalide.",
				validChars
		);
		inputManager.setCaseSensitive(false);
		char input;
		do {
			menu.showMenu();
			input = inputManager.getChar();
			if (input != EXIT_CHAR) {
				menu.execute(input - 1 - '0');
				System.out.println("Fin du programme.");
			}
		} while (input != EXIT_CHAR);
		System.out.println("Au revoir !");
	}
}
