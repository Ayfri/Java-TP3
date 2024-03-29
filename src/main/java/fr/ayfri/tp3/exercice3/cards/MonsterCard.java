package fr.ayfri.tp3.exercice3.cards;

import fr.ayfri.tp3.exercice3.Attribute;
import fr.ayfri.tp3.exercice3.types.Type;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * La classe MonsterCard permettant de créer des monstres pour les cartes de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public final class MonsterCard extends AMonstre {
	/**
	 * Constructeur de la classe MonsterCard.
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
	public MonsterCard(
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
		super(name, description, attack, defense, id, level, attribute, types, imageURL);
	}

	@Override
	public boolean equals(@Nullable final Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		final var other = (MonsterCard) obj;
		return this.name.equals(other.name) && this.description.equals(other.description) && this.attack == other.attack &&
		       this.defense == other.defense && this.id == other.id && this.level == other.level &&
		       this.attribute.equals(other.attribute) && Arrays.equals(this.types, other.types);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, attack, defense, id, level, attribute, types);
	}

	@Contract(pure = true)
	@Override
	public @NotNull String toString() {
		return "MonsterCard[" + "name=" + name + ", " + "description=" + description + ", " + "attack=" + attack + ", " + "defense=" +
		       defense + ", " + "id=" + id + ", " + "level=" + level + ", " + "attribute=" + attribute + ", " + "types=" +
		       Arrays.toString(types) + ']';
	}
}
