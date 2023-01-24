package fr.ayfri.tp3.exercice1.PClasse;

import fr.ayfri.tp3.exercice1.PEtudiant.Etudiant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Classe permettant de créer des classes.
 */
public final class Classe implements Serializable {

	/**
	 * Le numéro de série de la classe, pour la sérialisation.
	 */
	@Serial
	private static final long serialVersionUID = -902522407687904528L;

	/**
	 * Le nom de la classe.
	 */
	private final @NotNull String name;

	/**
	 * La liste des étudiants de la classe.
	 */
	private final @NotNull List<Etudiant> students = new ArrayList<>();

	/**
	 * Constructeur de la classe Classe.
	 *
	 * @param name Le nom de la classe.
	 */
	public Classe(final @NotNull String name) {this.name = name;}

	/**
	 * Charge une classe depuis un fichier.
	 *
	 * @param fileName Le nom du fichier.
	 *
	 * @return La classe chargée.
	 * @throws IOException Si une erreur d'entrée/sortie est survenue.
	 * @throws ClassNotFoundException Si la dé-sérialisation a échoué.
	 */
	public static @Nullable Classe load(@NotNull final String fileName) throws IOException, ClassNotFoundException {
		final var file = new File(fileName);
		if (!file.exists()) return null;

		try (final var ois = new ObjectInputStream(new FileInputStream(file))) {
			return (Classe) ois.readObject();
		}
	}

	/**
	 * Affiche la classe dans la console.
	 */
	public void afficher() {
		final var formattedList = students.stream()
		                                  .map(etudiant -> etudiant.getFirstName() + " " + etudiant.getLastName() + " : " +
		                                                   etudiant.moyenne())
		                                  .reduce("%s%n %s"::formatted)
		                                  .orElse("Aucun élève");

		System.out.printf("""
				Moyenne de la classe '%s' : %f
				Liste des élèves : %n%s%n
			""".stripIndent(), name, moyenneClasse(), formattedList);
	}

	/**
	 * Récupère la moyenne de la classe.
	 *
	 * @return La moyenne de la classe.
	 */
	public float moyenneClasse() {
		final var sum = students.stream().mapToInt(Etudiant::moyenne).sum();
		final var count = students.size();

		return (float) sum / count;
	}

	/**
	 * Récupère un étudiant de la classe.
	 *
	 * @param firstName Le prénom de l'étudiant.
	 * @param lastName Le nom de l'étudiant.
	 *
	 * @return L'étudiant trouvé.
	 */
	public @Nullable Etudiant getEtudiant(final @NotNull String firstName, final @NotNull String lastName) {
		return students.stream()
		               .filter(student -> student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))
		               .findFirst()
		               .orElse(null);
	}

	/**
	 * Sauvegarde la classe dans un fichier.
	 *
	 * @param fileName Le nom du fichier.
	 */
	public void save(@NotNull final String fileName) {
		final var file = new File(fileName);
		try (final var oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(this);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public @NotNull String toString() {
		return String.format("%s %s", name, students);
	}

	/**
	 * Ajoute un étudiant à la classe.
	 *
	 * @param student L'étudiant à ajouter.
	 */
	public void setEtudiant(final @NotNull Etudiant student) {
		students.add(student);
	}

	/**
	 * Désérialise la classe.
	 *
	 * @param in Le flux d'entrée.
	 *
	 * @throws IOException Si une erreur d'entrée/sortie est survenue.
	 * @throws ClassNotFoundException Si la dé-sérialisation a échoué.
	 */
	@Serial
	private void readObject(@NotNull final ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}

	/**
	 * Sérialise la classe.
	 *
	 * @param out Le flux de sortie.
	 *
	 * @throws IOException Si une erreur d'entrée/sortie est survenue.
	 */
	@Serial
	private void writeObject(@NotNull final ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
}
