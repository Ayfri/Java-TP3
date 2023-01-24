package fr.ayfri.tp3.exercice3.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import fr.ayfri.tp3.exercice3.types.MagicCardIcon;
import fr.ayfri.tp3.exercice3.types.SpecialCardIcon;
import fr.ayfri.tp3.exercice3.types.TrapCardIcon;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

public class SpecialCardIconDeserializer implements JsonDeserializer<SpecialCardIcon> {
	@Override
	public @NotNull SpecialCardIcon deserialize(
			final @NotNull JsonElement json,
			final @NotNull Type typeOfT,
			final @NotNull JsonDeserializationContext context
	) throws JsonParseException {
		try {
			return MagicCardIcon.valueOf(json.getAsString());
		} catch (final Exception e) {
			return TrapCardIcon.valueOf(json.getAsString());
		}
	}
}
