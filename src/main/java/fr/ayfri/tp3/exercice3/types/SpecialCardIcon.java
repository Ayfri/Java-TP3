package fr.ayfri.tp3.exercice3.types;

import org.jetbrains.annotations.NotNull;

/**
 * L'interface SpecialCardIcon permettant de créer des icônes spéciales pour les cartes de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public interface SpecialCardIcon {
	/**
	 * Le nom de l'icône.
	 *
	 * @return Le nom de l'icône.
	 */
	@NotNull String name();

	/**
	 * La traduction de l'icône.
	 *
	 * @return La traduction de l'icône.
	 */
	@NotNull String getTranslation();
}
