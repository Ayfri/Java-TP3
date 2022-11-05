package fr.ayfri.tp3.exercice1.PClasse;

import fr.ayfri.tp3.exercice1.PEtudiant.Etudiant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Classe implements Serializable {

	@Serial
	private static final long serialVersionUID = -902522407687904528L;

	private final @NotNull String name;

	private final @NotNull List<Etudiant> students = new ArrayList<>();

	public Classe(final @NotNull String name) {this.name = name;}

	public static @Nullable Classe load(@NotNull String fileName) throws IOException, ClassNotFoundException {
		final var file = new File(fileName);
		if (!file.exists()) return null;

		try (final var ois = new ObjectInputStream(new FileInputStream(file))) {
			return (Classe) ois.readObject();
		}
	}

	public void afficher() {
		final var formattedList = students.stream()
			                          .map(etudiant -> etudiant.getFirstName() + " " + etudiant.getLastName() + " : " + etudiant.moyenne())
			                          .reduce("%s%n %s"::formatted)
			                          .orElse("Aucun élève");

		System.out.printf("""
		                  Moyenne de la classe '%s' : %f
		                  Liste des élèves : %n%s%n
		                     """.stripIndent(), name, moyenneClasse(), formattedList);
	}

	public float moyenneClasse() {
		final var sum = students.stream().mapToInt(Etudiant::moyenne).sum();
		final var count = students.size();

		return (float) sum / count;
	}

	public @Nullable Etudiant getEtudiant(final @NotNull String firstName, final @NotNull String lastName) {
		return students.stream().filter(student -> student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)).findFirst().orElse(null);
	}

	public void save(@NotNull String fileName) {
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

	public void setEtudiant(final @NotNull Etudiant student) {
		students.add(student);
	}
}
