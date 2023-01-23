package fr.ayfri.tp3.exercice3.gui;

import fr.ayfri.tp3.exercice3.cards.ICarteYuGiOh;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public final class Utils {
	private static final String @NotNull [] SAVED_IMAGES = loadSavedImages();

	private Utils() {}

	public static @NotNull JLabel getImage(final @NotNull ICarteYuGiOh card, final boolean tilted) {
		var image = loadImage(card.getImageURL());
		final Image resizedImage;
		if (tilted) {
			image = Utils.rotate(image, 90);
			resizedImage = image.getScaledInstance(
					PlayerGUI.CARD_SIZE.height,
					PlayerGUI.CARD_SIZE.width,
					Image.SCALE_SMOOTH
			);
		} else {
			resizedImage = image.getScaledInstance(
					PlayerGUI.CARD_SIZE.width,
					PlayerGUI.CARD_SIZE.height,
					Image.SCALE_SMOOTH
			);
		}

		final var label = new JLabel(new ImageIcon(resizedImage));
		if (tilted) {
			label.setSize(PlayerGUI.CARD_SIZE.height, PlayerGUI.CARD_SIZE.width);
		} else {
			label.setSize(PlayerGUI.CARD_SIZE.width, PlayerGUI.CARD_SIZE.height);
		}

		return label;
	}

	private static @NotNull BufferedImage loadImage(@NotNull final String path) {
		final var imageName = path.substring(path.lastIndexOf('/') + 1);
		final var imageFile = new File("src/main/resources/cards/" + imageName);

		@NotNull final BufferedImage result;
		try {
			if (Arrays.asList(SAVED_IMAGES).contains(imageName)) {
				result = ImageIO.read(imageFile);
			} else {
				result = ImageIO.read(new URL(path));
			}

			ImageIO.write(result, path.substring(path.lastIndexOf('.') + 1), imageFile);

			return result;
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static @NotNull BufferedImage rotate(final BufferedImage img, final double angle) {
		final var rads = Math.toRadians(angle);
		final var sin = Math.abs(StrictMath.sin(rads));
		final var cos = Math.abs(StrictMath.cos(rads));
		final var w = img.getWidth();
		final var h = img.getHeight();
		final var newWidth = (int) Math.floor(w * cos + h * sin);
		final var newHeight = (int) Math.floor(h * cos + w * sin);

		final var rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		final var g2d = rotated.createGraphics();
		final var at = new AffineTransform();
		at.translate((newWidth - w) / 2d, (newHeight - h) / 2d);

		final var x = w / 2;
		final var y = h / 2;

		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, 0, 0, null);
		g2d.setColor(Color.RED);
		g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
		g2d.dispose();

		return rotated;
	}

	public static @NotNull JLabel getImage(final @NotNull ICarteYuGiOh card) {
		return getImage(card, PlayerGUI.CARD_SIZE);
	}

	public static @NotNull JLabel getImage(final @NotNull ICarteYuGiOh card, final @NotNull Dimension cardSize) {
		final var image = loadImage(card.getImageURL());
		final var resizedImage = cardSize == PlayerGUI.CARD_SIZE ? image : image.getScaledInstance(
				cardSize.width,
				cardSize.height,
				Image.SCALE_SMOOTH
		);

		final var label = new JLabel(new ImageIcon(resizedImage));
		label.setSize(cardSize.width, cardSize.height);

		return label;
	}

	public static @NotNull ImageIcon getImageIcon(final @NotNull ICarteYuGiOh card) {
		return getImageIcon(card, PlayerGUI.CARD_SIZE);
	}

	public static @NotNull ImageIcon getImageIcon(
			final @NotNull ICarteYuGiOh card,
			final @NotNull Dimension cardSize
	) {
		final var image = loadImage(card.getImageURL());
		return new ImageIcon(image.getScaledInstance(cardSize.width, cardSize.height, Image.SCALE_SMOOTH));
	}

	private static String @NotNull [] loadSavedImages() {
		final var dir = new File("src/main/resources/cards");
		final var files = dir.listFiles();
		dir.mkdir();
		if (files != null) {
			final var savedImages = new String[files.length];
			for (var i = 0; i < files.length; i++) {
				savedImages[i] = files[i].getName();
			}

			return savedImages;
		}

		return new String[]{};
	}
}
