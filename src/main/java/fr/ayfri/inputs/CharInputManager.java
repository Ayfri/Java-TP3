package fr.ayfri.inputs;

import org.jetbrains.annotations.NotNull;

/**
 * CharInputManager est une classe qui permet de demander un caractère à l'utilisateur.
 *
 * @author Pierre
 */
public final class CharInputManager extends AbstractInputManager {
	/**
	 * La liste des caractères valides.
	 */
	private final char @NotNull [] allowedChars;
	/**
	 * Message à afficher si l'utilisateur entre un caractère invalide.
	 */
	private final @NotNull String invalidInputMessage;
	/**
	 * Propriété pour savoir si le caractère peut ignorer la casse.
	 */
	private boolean caseSensitive = true;

	/**
	 * Crée un nouveau gestionnaire d'entrées pour les caractères.
	 *
	 * @param message      Le message à afficher pour demander une entrée.
	 * @param allowedChars La liste des caractères valides.
	 */
	public CharInputManager(final @NotNull String message, final char @NotNull [] allowedChars) {
		this(message, "Erreur, la valeur entrée est invalide.", allowedChars);
	}

	/**
	 * Crée un nouveau gestionnaire d'entrées pour les caractères.
	 *
	 * @param message             Le message à afficher pour demander une entrée.
	 * @param invalidInputMessage Le message à afficher si l'utilisateur entre un caractère invalide.
	 * @param allowedChars        La liste des caractères valides.
	 */
	public CharInputManager(final @NotNull String message, final @NotNull String invalidInputMessage, final char @NotNull [] allowedChars) {
		this.message = message;
		this.invalidInputMessage = invalidInputMessage;
		this.allowedChars = allowedChars;
	}

	/**
	 * Demande à l'utilisateur d'entrer un caractère et le retourne.
	 *
	 * @return Le caractère entré par l'utilisateur.
	 */
	public char getChar() {
		char value;
		do {
			System.out.print(message);
			final var line = scanner.next();
			value = line.length() == 0 ? '\0' : line.charAt(0);

			if (isInvalidInput(value)) System.out.println(invalidInputMessage);
		} while (isInvalidInput(value));

		return value;
	}

	/**
	 * Vérifie si l'entrée est valide.
	 *
	 * @param input Le caractère à vérifier.
	 *
	 * @return {@code true} si le caractère est valide, {@code false} sinon.
	 */
	public boolean isInvalidInput(final char input) {
		for (final char validInput : allowedChars) {
			if (caseSensitive) {
				if (Character.toLowerCase(input) == Character.toLowerCase(validInput)) return false;
			} else if (input == validInput) return false;
		}

		return true;
	}

	/**
	 * Getter de la propriété pour savoir si le caractère peut ignorer la casse.
	 *
	 * @return La propriété pour savoir si le caractère peut ignorer la casse.
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * Setter de la propriété pour savoir si le caractère peut ignorer la casse.
	 *
	 * @param caseSensitive La propriété pour savoir si le caractère peut ignorer la casse.
	 */
	public void setCaseSensitive(final boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
}
