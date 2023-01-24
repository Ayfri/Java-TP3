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

/**
 * L'interface ICarteYuGiOh permettant de créer des cartes de Yu-Gi-Oh.
 *
 * @author Ayfri
 */
public sealed interface ICarteYuGiOh permits AMonstre, APiegeEtMagie {
	/**
	 * Permet de charger une carte de Yu-Gi-Oh depuis un fichier JSON.
	 *
	 * @param file Le fichier de la carte.
	 *
	 * @return La carte de Yu-Gi-Oh.
	 * @throws IOException Si une erreur d'entrée/sortie est survenue lors de la lecture du fichier.
	 */
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

	/**
	 * Getter du nom de la carte.
	 *
	 * @return Le nom de la carte.
	 */
	@NotNull String getName();

	/**
	 * Getter de la description de la carte.
	 *
	 * @return La description de la carte.
	 */
	@NotNull String getDescription();

	/**
	 * Getter de l'ID de la carte.
	 *
	 * @return L'ID de la carte.
	 */
	int getId();

	/**
	 * Getter du type de la carte.
	 *
	 * @return Le type de la carte.
	 */
	@NotNull String getType();

	/**
	 * Getter de l'URL de l'image de la carte.
	 *
	 * @return L'URL de l'image de la carte.
	 */
	@NotNull String getImageURL();

	/**
	 * Permet de sauvegarder la carte dans un fichier JSON.
	 *
	 * @param file Le fichier où sauvegarder la carte.
	 *
	 * @throws IOException Si une erreur d'entrée/sortie est survenue lors de l'écriture du fichier.
	 */
	default void saveTo(final @NotNull File file) throws IOException {
		final var gson = new Gson();
		final var json = gson.toJson(this);
		final var fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
		fileWriter.write(json);
		fileWriter.close();
	}
}
