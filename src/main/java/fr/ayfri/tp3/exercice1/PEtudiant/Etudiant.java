package fr.ayfri.tp3.exercice1.PEtudiant;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Etudiant implements Serializable {
	@Serial
	private static final long serialVersionUID = -2011185525818839524L;
	private final @NotNull String firstName;

	private final @NotNull String lastName;

	private final @NotNull Map<String, List<Double>> notes = new HashMap<>();

	public Etudiant(final @NotNull String firstName, final @NotNull String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void afficherNote() {
		System.out.printf("Moyenne générale : %d%n", moyenne());
	}

	public int moyenne() {
		final var sum = notes.values().stream().mapToInt(list -> (int) list.stream().mapToDouble(Double::doubleValue).average().orElse(0)).sum();
		return sum / notes.size();
	}

	public int moyenne(@NotNull String subject) {
		final var notesList = notes.get(subject);
		if (notesList == null) return 0;

		final var sum = notesList.stream().mapToInt(Double::intValue).sum();
		return sum / notesList.size();
	}

	public void afficherNote(@NotNull String subject) {
		final var notesList = notes.get(subject);
		if (notesList == null) return;

		System.out.printf("Moyenne en %s : %d%n", subject, moyenne(subject));
	}

	public void setNote(final @NotNull String subject, final double note) {
		setNote(subject, 20, note);
	}

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

	public @NotNull String getFirstName() {
		return firstName;
	}

	public @NotNull String getLastName() {
		return lastName;
	}
}
