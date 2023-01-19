package fr.ayfri.tp3;

import fr.ayfri.inputs.AbstractInputManager;
import org.jetbrains.annotations.NotNull;

/**
 * La classe abstraite Exercice permettant de créer des exercices.
 *
 * @param <T> Le type du gestionnaire d'entrée.
 *
 * @author Pierre
 */
public abstract class TerminalExercice<T extends AbstractInputManager> extends Exercice {
	/**
	 * Le gestionnaire d'entrées.
	 */
	public @NotNull T inputManager;
}
