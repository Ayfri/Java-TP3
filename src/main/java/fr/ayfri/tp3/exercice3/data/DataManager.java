package fr.ayfri.tp3.exercice3.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class DataManager {
	private static final @NotNull Gson GSON;
	private static final @NotNull String DATA_FILE = "src/main/resources/cards.json";

	static {
		final var gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ICarteYuGiOh.class, new CardDeserielizer());
		GSON = gsonBuilder.create();
	}

	private DataManager() {
		final var cardsTypeToken = new TypeToken<List<ICarteYuGiOh>>() {};

//		final var typeFactory = ReflectiveTypeAdapterFactory.Adapter()
	}

	public static @NotNull List<ICarteYuGiOh> getCards() {
		final var file = new File(DATA_FILE);
		if (!file.exists()) {
			System.out.println("Veuillez lancer le module yugiohapi pour récupérer les cartes d'abord.");
			return Collections.emptyList();
		}

		try {
			final var cardsTypeToken = new TypeToken<List<ICarteYuGiOh>>() {};
			final var cards = GSON.fromJson(new FileReader(file), cardsTypeToken);
			cards.removeIf(Objects::isNull);
			return cards;
		} catch (final Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
