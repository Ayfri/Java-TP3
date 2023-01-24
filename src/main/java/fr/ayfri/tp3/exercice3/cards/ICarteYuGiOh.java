package fr.ayfri.tp3.exercice3.cards;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.ayfri.tp3.exercice3.data.CardDeserializer;
import fr.ayfri.tp3.exercice3.types.SpecialCardType;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public interface ICarteYuGiOh {
	static @NotNull ICarteYuGiOh loadFrom(final @NotNull File file) throws IOException {
		final var gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
		gsonBuilder.registerTypeAdapter(ICarteYuGiOh.class, new CardDeserializer());
		final var gson = gsonBuilder.create();
		final var json = new FileReader(file);
		final var jsonObject = gson.fromJson(json, JsonObject.class);

		final var isMonster = jsonObject.has("attack");
		final var type = jsonObject.has("type") ? jsonObject.get("type").getAsString() : null;
		final var isSpell = Objects.equals(type, SpecialCardType.SPELL.name());

		return isMonster ? gson.fromJson(json, MonsterCard.class) : isSpell ? gson.fromJson(json, MagicCard.class) : gson.fromJson(
				json,
				APiegeEtMagie.class
		);
	}

	@NotNull String getName();

	@NotNull String getDescription();

	int getId();

	@NotNull String getType();

	@NotNull String getImageURL();

	default void saveTo(final @NotNull File file) throws IOException {
		final var gson = new Gson();
		final var json = gson.toJson(this);
		final var fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
		fileWriter.write(json);
		fileWriter.close();
	}
}
