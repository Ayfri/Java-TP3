package fr.ayfri.tp3.exercice3.cards;

import org.jetbrains.annotations.NotNull;

public interface ICarteYuGiOh {
	@NotNull String getName();

	@NotNull String getDescription();

	int getId();

	@NotNull String getType();

	@NotNull String getImageURL();
}
