package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.types.MagicCardIcon;
import fr.ayfri.tp3.exercice3.types.SpecialCardType;
import org.jetbrains.annotations.NotNull;

/**
 * La classe MagicCard permettant de créer des cartes de magie et de pièges pour Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public final class MagicCard extends APiegeEtMagie {
	/**
	 * Constructeur de la classe MagicCard.
	 *
	 * @param name Le nom de la carte.
	 * @param description La description de la carte.
	 * @param type Le type de la carte.
	 * @param id L'identifiant de la carte.
	 * @param icon L'icône de la carte.
	 * @param imageURL L'URL de l'image de la carte.
	 */
	public MagicCard(
		final @NotNull String name,
		final @NotNull String description,
		final @NotNull SpecialCardType type,
		final int id,
		final @NotNull MagicCardIcon icon,
		final @NotNull String imageURL
	) {
		super(name, description, type, id, icon, imageURL);
	}
}
