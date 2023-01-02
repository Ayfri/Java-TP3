package fr.ayfri;

import org.jetbrains.annotations.NotNull;

/**
 * La classe abstraite GraphicExercice permettant de créer des exercices graphiques.
 */
public abstract class Exercice {

	/**
	 * La description de l'exercice.
	 */
	protected @NotNull String description;
	/**
	 * Le titre de l'exercice.
	 */
	protected @NotNull String title;

	/**
	 * La méthode pour exécuter l'exercice.
	 */
	public abstract void run();

	/**
	 * Getter de la description.
	 *
	 * @return La description de l'article.
	 */
	public @NotNull String getDescription() {
		return description;
	}

	/**
	 * Getter du titre.
	 *
	 * @return Le titre de l'article.
	 */
	public @NotNull String getTitle() {
		return title;
	}
}
