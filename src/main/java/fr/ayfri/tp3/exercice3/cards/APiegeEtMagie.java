package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.types.SpecialCardIcon;
import fr.ayfri.tp3.exercice3.types.SpecialCardType;
import org.jetbrains.annotations.NotNull;

/**
 * La classe APiegeEtMagie permettant de créer des cartes de type Piège ou Magie.
 *
 * @author Ayfri
 */
public abstract sealed class APiegeEtMagie implements ICarteYuGiOh permits MagicCard, TrapCard {
	/**
	 * Le nom de la carte.
	 */
	protected final @NotNull String name;
	/**
	 * La description de la carte.
	 */
	protected final @NotNull String description;
	/**
	 * Le type de la carte.
	 */
	protected final @NotNull SpecialCardType type;
	/**
	 * L'identifiant de la carte.
	 */
	protected final int id;
	/**
	 * L'icône de la carte.
	 */
	protected final @NotNull SpecialCardIcon icon;
	/**
	 * L'URL de l'image de la carte.
	 */
	protected final @NotNull String imageURL;

	/**
	 * Constructeur de la classe APiegeEtMagie.
	 *
	 * @param name Le nom de la carte.
	 * @param description La description de la carte.
	 * @param type Le type de la carte.
	 * @param id L'identifiant de la carte.
	 * @param icon L'icône de la carte.
	 * @param imageURL L'URL de l'image de la carte.
	 */
	protected APiegeEtMagie(
		@NotNull final String name,
		@NotNull final String description,
		@NotNull final SpecialCardType type,
		final int id,
		@NotNull final SpecialCardIcon icon,
		@NotNull final String imageURL
	) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.id = id;
		this.icon = icon;
		this.imageURL = imageURL;
	}

	@Override
	public final @NotNull String getName() {
		return name;
	}

	@Override
	public final @NotNull String getDescription() {
		return description;
	}

	@Override
	public final int getId() {
		return id;
	}

	@Override
	public final @NotNull String getType() {
		return type.getTranslation();
	}

	@NotNull
	@Override
	public final String getImageURL() {
		return imageURL;
	}

	/**
	 * Getter de l'icône de la carte.
	 *
	 * @return L'icône de la carte.
	 */
	public final @NotNull SpecialCardIcon getIcon() {
		return icon;
	}
}
