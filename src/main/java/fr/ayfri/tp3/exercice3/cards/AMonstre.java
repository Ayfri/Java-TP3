package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.Attribute;
import fr.ayfri.tp3.exercice3.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * La classe AMonstre permettant de créer des monstres pour les cartes de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public abstract sealed class AMonstre implements ICarteYuGiOh permits MonsterCard {
	/**
	 * Le nom du monstre.
	 */
	protected final @NotNull String name;
	/**
	 * La description du monstre.
	 */
	protected final @NotNull String description;
	/**
	 * L'attaque du monstre.
	 */
	protected final int attack;
	/**
	 * La défense du monstre.
	 */
	protected final int defense;
	/**
	 * L'identifiant du monstre.
	 */
	protected final int id;
	/**
	 * Le niveau du monstre.
	 */
	protected final int level;
	/**
	 * L'attribut du monstre.
	 */
	protected final @NotNull Attribute attribute;
	/**
	 * Les types du monstre.
	 */
	protected final Type @NotNull [] types;
	/**
	 * L'URL de l'image du monstre.
	 */
	protected final @NotNull String imageURL;

	/**
	 * Constructeur de la classe AMonstre.
	 *
	 * @param name Le nom du monstre.
	 * @param description La description du monstre.
	 * @param attack L'attaque du monstre.
	 * @param defense La défense du monstre.
	 * @param id L'identifiant du monstre.
	 * @param level Le niveau du monstre.
	 * @param attribute L'attribut du monstre.
	 * @param types Les types du monstre.
	 * @param imageURL L'URL de l'image du monstre.
	 */
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
		return Arrays.stream(types).map(Type::getTranslation).collect(Collectors.joining(","));
	}

	@Override
	public final @NotNull String getImageURL() {
		return imageURL;
	}

	/**
	 * L'attaque du monstre.
	 *
	 * @return L'attaque du monstre.
	 */
	public final int getAttack() {
		return attack;
	}

	/**
	 * La défense du monstre.
	 *
	 * @return La défense du monstre.
	 */
	public final int getDefense() {
		return defense;
	}

	/**
	 * Le niveau du monstre.
	 *
	 * @return Le niveau du monstre.
	 */
	public final int getLevel() {
		return level;
	}

	/**
	 * L'attribut du monstre.
	 *
	 * @return L'attribut du monstre.
	 */
	public final @NotNull Attribute getAttribute() {
		return attribute;
	}

	/**
	 * Les types du monstre.
	 * @return Les types du monstre.
	 */
	public final Type @NotNull [] getTypes() {
		return types;
	}
}
