package fr.ayfri.tp3.exercice3.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import fr.ayfri.tp3.exercice3.Attribute;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import fr.ayfri.tp3.exercice3.cards.MagicCard;
import fr.ayfri.tp3.exercice3.cards.MonsterCard;
import fr.ayfri.tp3.exercice3.cards.TrapCard;
import fr.ayfri.tp3.exercice3.types.MagicCardIcon;
import fr.ayfri.tp3.exercice3.types.SpecialCardType;
import fr.ayfri.tp3.exercice3.types.TrapCardIcon;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

/**
 * La classe CardDeserializer permettant de désérialiser les cartes sauvegardées dans le fichier JSON.
 *
 * @author Ayfri
 */
public final class CardDeserializer implements JsonDeserializer<ICarteYuGiOh> {
	@Override
	public @NotNull ICarteYuGiOh deserialize(
		final JsonElement json, final @NotNull Type typeOfT, final @NotNull JsonDeserializationContext context
	) throws JsonParseException {
		System.out.println("ENCULER");

		final var jsonObject = json.getAsJsonObject();
		final var isMonster = jsonObject.has("attack");

		final var name = jsonObject.get("name").getAsString();
		final var description = jsonObject.get("description").getAsString();
		final var id = jsonObject.get("id").getAsInt();
		final var imageURL = jsonObject.get("imageURL").getAsString();

		if (isMonster) {
			final var types = jsonObject.get("types").getAsJsonArray();
			System.out.println(types);
			return new MonsterCard(
					name,
					description,
					jsonObject.get("attack").getAsInt(),
					jsonObject.get("defense").getAsInt(),
					id,
					jsonObject.get("level").getAsInt(),
					Attribute.valueOf(jsonObject.get("attribute").getAsString()),
					types.asList()
					     .stream()
					     .map(s -> fr.ayfri.tp3.exercice3.types.Type.valueOf(s.getAsString()))
					     .toArray(fr.ayfri.tp3.exercice3.types.Type[]::new),
					imageURL
			);
		}

		final var icon = jsonObject.get("icon").getAsString();
		final var isMagic = SpecialCardType.SPELL.name().equals(icon);
		final var specialType = SpecialCardType.valueOf(icon);

		return isMagic ? new MagicCard(name, description, specialType, id, MagicCardIcon.valueOf(icon), imageURL) : new TrapCard(
				name,
				description,
				specialType,
				id,
				TrapCardIcon.valueOf(icon),
				imageURL
		);

	}
}
