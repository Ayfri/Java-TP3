package fr.ayfri.tp3.exercice3.board;

import org.jetbrains.annotations.NotNull;

/**
 * La classe Board permettant de créer un plateau de jeu pour Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public final class Board {
	/**
	 * Le premier joueur du plateau de jeu.
	 */
	private final @NotNull Player firstPlayer = new Player();
	/**
	 * Le second joueur du plateau de jeu.
	 */
	private final @NotNull Player secondPlayer = new Player();

	/**
	 * Récupère le premier joueur du plateau de jeu.
	 *
	 * @return Le premier joueur du plateau de jeu.
	 */
	public @NotNull Player getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * Récupère le second joueur du plateau de jeu.
	 *
	 * @return Le second joueur du plateau de jeu.
	 */
	public @NotNull Player getSecondPlayer() {
		return secondPlayer;
	}
}
