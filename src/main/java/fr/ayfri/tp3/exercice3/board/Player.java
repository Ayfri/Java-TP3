package fr.ayfri.tp3.exercice3.board;

import org.jetbrains.annotations.NotNull;

/**
 * La classe Player permettant de créer un joueur pour Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public final class Player {
	/**
	 * Le terrain de bataille du joueur.
	 */
	private final @NotNull Field field = new Field();

	/**
	 * Récupère le champ de bataille du joueur.
	 *
	 * @return Le champ de bataille du joueur.
	 */
	public @NotNull Field getField() {
		return field;
	}
}
