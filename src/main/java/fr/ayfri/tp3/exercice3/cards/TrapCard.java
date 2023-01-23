package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.types.SpecialCardType;
import fr.ayfri.tp3.exercice3.types.TrapCardIcon;
import org.jetbrains.annotations.NotNull;

public final class TrapCard extends APiegeEtMagie {
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
