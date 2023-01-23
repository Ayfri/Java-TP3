package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.Attribute;
import fr.ayfri.tp3.exercice3.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class AMonstre implements ICarteYuGiOh {
	protected final @NotNull String name;
	protected final @NotNull String description;
	protected final int attack;
	protected final int defense;
	protected final int id;
	protected final int level;
	protected final @NotNull Attribute attribute;
	protected final Type @NotNull [] types;
	protected final @NotNull String imageURL;

	protected AMonstre(
			@NotNull final String name,
			@NotNull final String description,
			final int attack,
			final int defense,
			final int id,
			final int level,
			@NotNull final Attribute attribute,
			final Type @NotNull [] types,
			final @NotNull String imageURL
	) {
		this.name = name;
		this.description = description;
		this.attack = attack;
		this.defense = defense;
		this.id = id;
		this.level = level;
		this.attribute = attribute;
		this.types = types;
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
		return Arrays.stream(types).map(Enum::name).collect(Collectors.joining(","));
	}

	@Override
	public final @NotNull String getImageURL() {
		return imageURL;
	}

	public final int getAttack() {
		return attack;
	}

	public final int getDefense() {
		return defense;
	}

	public final int getLevel() {
		return level;
	}

	public final @NotNull Attribute getAttribute() {
		return attribute;
	}

	public final Type @NotNull [] getTypes() {
		return types;
	}
}
