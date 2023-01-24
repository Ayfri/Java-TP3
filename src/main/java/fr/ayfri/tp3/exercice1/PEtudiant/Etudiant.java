package fr.ayfri.tp3.exercice1.PEtudiant;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe Etudiant permettant de créer des étudiants.
 */
public final class Etudiant implements Serializable {
	/**
	 * Le numéro de série de l'étudiant, pour la sérialisation.
	 */
	@Serial
	private static final long serialVersionUID = -2011185525818839524L;
	/**
	 * Le prénom de l'étudiant.
	 */
	private final @NotNull String firstName;

	/**
	 * Le nom de l'étudiant.
	 */
	private final @NotNull String lastName;

	/**
	 * La liste des notes de l'étudiant.
	 */
	private final @NotNull Map<String, List<Double>> notes = new HashMap<>();

	/**
	 * Constructeur de la classe Etudiant.
	 *
	 * @param firstName Le prénom de l'étudiant.
	 * @param lastName Le nom de l'étudiant.
	 */
	public Etudiant(final @NotNull String firstName, final @NotNull String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Affiche la moyenne générale de l'étudiant dans la console.
	 */
	public void afficherNote() {
		System.out.printf("Moyenne générale : %d%n", moyenne());
	}

	/**
	 * Calcule la moyenne générale de l'étudiant.
	 *
	 * @return La moyenne générale de l'étudiant.
	 */
	public int moyenne() {
		final var sum = notes.values()
		                     .stream()
		                     .mapToInt(list -> (int) list.stream().mapToDouble(Double::doubleValue).average().orElse(0))
		                     .sum();
		return sum / notes.size();
	}

	/**
	 * Affiche la moyenne de l'étudiant dans une matière dans la console.
	 *
	 * @param subject La matière.
	 */
	public void afficherNote(@NotNull final String subject) {
		final var notesList = notes.get(subject);
		if (notesList == null) return;

		System.out.printf("Moyenne en %s : %d%n", subject, moyenne(subject));
	}

	/**
	 * Calcule la moyenne de l'étudiant dans une matière.
	 *
	 * @param subject La matière.
	 *
	 * @return La moyenne de l'étudiant dans la matière.
	 */
	public int moyenne(@NotNull final String subject) {
		final var notesList = notes.get(subject);
		if (notesList == null) return 0;

		final var sum = notesList.stream().mapToInt(Double::intValue).sum();
		return sum / notesList.size();
	}

	/**
	 * Ajoute une note à l'étudiant.
	 *
	 * @param subject La matière.
	 * @param note La note.
	 */
	public void setNote(final @NotNull String subject, final double note) {
		setNote(subject, 20, note);
	}

	/**
	 * Ajoute une note à l'étudiant.
	 *
	 * @param subject La matière.
	 * @param max La note maximale.
	 * @param note La note.
	 */
	public void setNote(final @NotNull String subject, final int max, final double note) {
		if (note > max) throw new IllegalArgumentException("La note ne peut pas être supérieure à la note maximale.");
		if (note < 0) throw new IllegalArgumentException("La note ne peut pas être inférieure à 0.");
		if (max <= 0) throw new IllegalArgumentException("La note maximale ne peut pas être inférieure ou égale à 0.");

		final var notesList = notes.get(subject);
		final var list = notesList == null ? new ArrayList<Double>() : notesList;
		list.add(note * 20 / max);
		notes.put(subject, list);
	}

	@Override
	public @NotNull String toString() {
		return "%s %s %s".formatted(firstName, lastName, notes);
	}

	/**
	 * Retourne le prénom de l'étudiant.
	 *
	 * @return Le prénom de l'étudiant.
	 */
	public @NotNull String getFirstName() {
		return firstName;
	}

	/**
	 * Retourne le nom de l'étudiant.
	 *
	 * @return Le nom de l'étudiant.
	 */
	public @NotNull String getLastName() {
		return lastName;
	}

	/**
	 * Désérialise l'étudiant.
	 *
	 * @param in Le flux d'entrée.
	 *
	 * @throws IOException Si une erreur d'entrée/sortie survient.
	 * @throws ClassNotFoundException Si la dé-sérialisation échoue.
	 */
	@Serial
	private void readObject(@NotNull final ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}

	/**
	 * Sérialise l'étudiant.
	 *
	 * @param out Le flux de sortie.
	 *
	 * @throws IOException Si une erreur d'entrée/sortie survient.
	 */
	@Serial
	private void writeObject(@NotNull final ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
}
