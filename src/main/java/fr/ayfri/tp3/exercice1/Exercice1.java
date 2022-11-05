package fr.ayfri.tp3.exercice1;

import fr.ayfri.Exercice;
import fr.ayfri.inputs.BooleanInputManager;
import fr.ayfri.inputs.NumberInputManager;
import fr.ayfri.inputs.StringInputManager;
import fr.ayfri.tp3.exercice1.PClasse.Classe;
import fr.ayfri.tp3.exercice1.PEtudiant.Etudiant;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Exercice1 extends Exercice<NumberInputManager> {
	public Exercice1() {
		title = "TP 3.1 : Classe & Héritage -> Etudiant";
		description = """
				1. Coder la classe Etudiant. – Il faut rajouter les attributs et le ou les constructeurs.
				2. Tester les fonctionnalités de la classe
				3. Coder la classe Classe contenant des étudiants
					– Créer une méthode de la classe Classe qui sauve la liste des étudiants dans un fichier
					– Créer une méthode de la classe Classe qui lit la liste des étudiants dans un fichier
				4. Tester les fonctionnalités de la classe
			""";
		inputManager = new NumberInputManager("", 0, 20);
	}

	public static void main(String @NotNull [] args) {
		new Exercice1().run();
	}

	@Override
	public void run() {

		final var nameInputManager = new StringInputManager("");
		final var getDataFromOldFileInputManager = new BooleanInputManager("Voulez-vous récupérer les données d'un fichier ? (y/n) : ");
		if (getDataFromOldFileInputManager.getBoolean()) {
			nameInputManager.setPrompt("Entrez le nom du fichier (q pour quitter) : ");

			while (true) {
				final var fileName = nameInputManager.getString().trim();

				if (fileName.equals("q")) break;
				final Classe classe;
				try {
					classe = Classe.load(fileName);
				} catch (IOException | ClassNotFoundException e) {
					System.out.println("Impossible de charger le fichier " + fileName + " !");
					continue;
				}

				if (classe != null) {
					classe.afficher();
					return;
				}

				System.out.println("Le fichier n'existe pas !");
			}
		}

		nameInputManager.setPrompt("Entrez le nom de la classe : ");
		final var classe = new Classe(nameInputManager.getString());

		final var studentInputManager = new StringInputManager("Entrez le nom et le prénom de l'étudiant : ");
		final var student = new Etudiant(studentInputManager.getString(), studentInputManager.getString());

		var subject = "Maths";
		inputManager.setPrompt("Entrez la note de " + subject + " : ");
		student.setNote(subject, inputManager.getDouble());
		student.setNote(subject, inputManager.getDouble());
		student.afficherNote(subject);

		subject = "Français";
		inputManager.setPrompt("Entrez la note de " + subject + " : ");
		student.setNote(subject, inputManager.getDouble());

		subject = "Anglais";
		final var max = 40;
		inputManager.setMaximum(max);
		inputManager.setPrompt("Entrez la note de %s (max %d) : ".formatted(subject, max));
		student.setNote(subject, max, inputManager.getDouble());

		student.afficherNote();
		classe.setEtudiant(student);
		classe.afficher();

		nameInputManager.setPrompt("Entrez le nom du fichier dans lequel sauvegarder la classe : ");
		final var fileName = nameInputManager.getString();
		classe.save(fileName);
	}
}
