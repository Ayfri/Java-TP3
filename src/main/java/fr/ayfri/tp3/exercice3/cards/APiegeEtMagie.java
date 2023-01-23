package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.types.SpecialCardIcon;
import fr.ayfri.tp3.exercice3.types.SpecialCardType;
import org.jetbrains.annotations.NotNull;

public abstract class APiegeEtMagie implements ICarteYuGiOh {
	protected final @NotNull String name;
	protected final @NotNull String description;
	protected final @NotNull SpecialCardType type;
	protected final int id;
	protected final @NotNull SpecialCardIcon icon;
	protected final @NotNull String imageURL;

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
		return type.name();
	}

	@NotNull
	@Override
	public final String getImageURL() {
		return imageURL;
	}

	public final @NotNull SpecialCardIcon getIcon() {
		return icon;
	}
}
