package fr.ayfri.tp3;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public abstract class GraphicalExercice extends Exercice {
	public static void setUIFont(@NotNull final FontUIResource resource) {
		for (final var key : UIManager.getDefaults().keySet()) {
			final var value = UIManager.get(key);
			if (value instanceof FontUIResource orig) {
				final var font = new Font(resource.getFontName(), orig.getStyle(), resource.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}

	public abstract void run(final @NotNull JFrame frame);

	@Override
	public final void run() {
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
			UIManager.put("TextComponent.arc", 5);
		} catch (final UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setUIFont(new FontUIResource("Arial", Font.PLAIN, 15));

		final var frame = new JFrame(getTitle());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		run(frame);
		frame.setVisible(true);
	}
}
