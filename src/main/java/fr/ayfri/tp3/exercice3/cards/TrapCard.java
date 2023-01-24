package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.types.SpecialCardType;
import fr.ayfri.tp3.exercice3.types.TrapCardIcon;
import org.jetbrains.annotations.NotNull;

/**
 * La classe TrapCard permettant de créer des cartes de pièges pour Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public final class TrapCard extends APiegeEtMagie {
	/**
	 * Constructeur de la classe TrapCard.
	 *
	 * @param name Le nom de la carte.
	 * @param description La description de la carte.
	 * @param type Le type de la carte.
	 * @param id L'identifiant de la carte.
	 * @param icon L'icône de la carte.
	 * @param imageURL L'URL de l'image de la carte.
	 */
	public TrapCard(
		final @NotNull String name,
		final @NotNull String description,
		final @NotNull SpecialCardType type,
		final int id,
		final @NotNull TrapCardIcon icon,
		final @NotNull String imageURL
	) {
		super(name, description, type, id, icon, imageURL);
	}
}
