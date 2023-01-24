package fr.ayfri;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.Set;

public final class Main {
	private static final @NotNull Set<String> SPECIAL_CARDS = Set.of(
			"Invocateur Dragon Bleu",
			"Sortil\u00E8ge de l'ombre",
			"Typhon d'Espace Mystique"
	);

	private Main() {}

	public static void main(final String @NotNull [] args) {
		final var url = "https://db.ygoprodeck.com/api/v7/cardinfo.php?language=fr";
		try {
			final var content = readUrl(url);
			final var gson = new Gson();
			final var json = gson.fromJson(content, JsonObject.class);
			final var cards = json.get("data").getAsJsonArray();
			shuffleJsonArray(cards);
			final var finalCards = new JsonArray(500);
			final var specialCards = new JsonArray(3);

			for (final var card : cards) {
				if (finalCards.size() == 500 && finalCards.size() == 3) break;

				final var jsonObject = card.getAsJsonObject();
				final var cardName = jsonObject.get("name").getAsString();

				if (specialCards.size() < 3 && SPECIAL_CARDS.contains(cardName)) {
					specialCards.add(card);
				} else if (finalCards.size() < 500) {
					finalCards.add(card);
				}
			}

			final var finalJson = new JsonArray();
			finalJson.addAll(specialCards);
			finalJson.addAll(finalCards);

			writeToFile(finalJson, "../src/main/resources/cards.json");
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String readUrl(final @NotNull String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			final var url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			final var buffer = new StringBuffer();
			int read;
			final var chars = new char[1024];
			while ((read = reader.read(chars)) != -1) buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null) reader.close();
		}
	}

	public static void shuffleJsonArray(final JsonArray array) {
		final var rnd = new Random();
		for (var i = array.size() - 1; i >= 0; i--) {
			final var j = rnd.nextInt(i + 1);
			final var object = array.get(j);
			array.set(j, array.get(i));
			array.set(i, object);
		}
	}

	private static void writeToFile(final @NotNull JsonArray array, final @NotNull String path) throws IOException {
		final var file = new File(path);
		final var output = new BufferedWriter(new FileWriter(file));
		output.write(array.toString());
		output.close();
	}
}
