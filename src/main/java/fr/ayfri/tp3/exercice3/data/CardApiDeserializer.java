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
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * La classe CardApiDeserializer permettant de désérialiser les cartes de l'API.
 *
 * @author Ayfri
 */
public final class CardApiDeserializer implements JsonDeserializer<ICarteYuGiOh> {
	@Override
	public @Nullable ICarteYuGiOh deserialize(
		final @NotNull JsonElement json,
		final @NotNull Type typeOfT,
		final @NotNull JsonDeserializationContext context
	) throws JsonParseException {
		final var jsonObject = json.getAsJsonObject();
		final var type = jsonObject.get("type").getAsString().toLowerCase();

		if ("skill card".equals(type)) return null;

		final var isMonster = type.contains("monster") || type.contains("token");

		final var name = jsonObject.get("name").getAsString();
		final var description = jsonObject.get("desc").getAsString();
		final var id = jsonObject.get("id").getAsInt();
		final var race = jsonObject.get("race").getAsString().replaceAll("[- ]", "_").toUpperCase();

		final var imageURL = jsonObject.get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();

		if (isMonster) {
			final var defense = jsonObject.has("def") ? jsonObject.get("def").getAsInt() : -1;
			final var level = jsonObject.has("level") ? jsonObject.get("level").getAsInt() : -1;
			return new MonsterCard(
				name,
				description,
				jsonObject.get("atk").getAsInt(),
				defense,
				id,
				level,
				Attribute.valueOf(jsonObject.get("attribute").getAsString().toUpperCase()),
				Arrays.stream(race.split(" ")).map(s -> fr.ayfri.tp3.exercice3.types.Type.valueOf(
					s.toUpperCase())).toArray(fr.ayfri.tp3.exercice3.types.Type[]::new),
				imageURL
			);
		}

		final var isMagic = "spell".equals(jsonObject.get("frameType").getAsString());
		final var specialType = SpecialCardType.valueOf(type.split(" ")[0].toUpperCase());

		return isMagic ? new MagicCard(name, description, specialType, id, MagicCardIcon.valueOf(race), imageURL) : new TrapCard(
			name,
			description,
			specialType,
			id,
			TrapCardIcon.valueOf(race),
			imageURL
		);

	}
}
