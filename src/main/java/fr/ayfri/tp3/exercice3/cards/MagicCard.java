package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.types.MagicCardIcon;
import fr.ayfri.tp3.exercice3.types.SpecialCardType;
import org.jetbrains.annotations.NotNull;

public final class MagicCard extends APiegeEtMagie {
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
