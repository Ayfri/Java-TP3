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

/**
 * La classe Utils permettant de gérer les méthodes utilitaires pour l'interface graphique.
 *
 * @author Ayfri
 */
public final class Utils {
	/**
	 * La liste des images mises en cache.
	 */
	private static final String @NotNull [] SAVED_IMAGES = loadSavedImages();

	/**
	 * Constructeur privé de la classe Utils car c'est une classe utilitaire.
	 */
	private Utils() {}

	/**
	 * Récupère un JLabel contenant l'image de la carte, tourne l'image si nécessaire.
	 *
	 * @param card La carte dont on veut l'image.
	 * @param tilted Si l'image doit être tournée de 90°.
	 *
	 * @return L'image de la carte.
	 */
	public static @NotNull JLabel getImage(final @NotNull ICarteYuGiOh card, final boolean tilted) {
		var image = loadImage(card.getImageURL());
		final Image resizedImage;
		if (tilted) {
			image = Utils.rotate(image, 90);
			resizedImage = image.getScaledInstance(PlayerGUI.CARD_SIZE.height, PlayerGUI.CARD_SIZE.width, Image.SCALE_SMOOTH);
		} else {
			resizedImage = image.getScaledInstance(PlayerGUI.CARD_SIZE.width, PlayerGUI.CARD_SIZE.height, Image.SCALE_SMOOTH);
		}

		final var label = new JLabel(new ImageIcon(resizedImage));
		if (tilted) {
			label.setSize(PlayerGUI.CARD_SIZE.height, PlayerGUI.CARD_SIZE.width);
		} else {
			label.setSize(PlayerGUI.CARD_SIZE.width, PlayerGUI.CARD_SIZE.height);
		}

		return label;
	}

	/**
	 * Charge une image depuis son nom, depuis le cache ou non selon si elle est dans la liste des images mises en cache.
	 *
	 * @param path Le chemin de l'image.
	 *
	 * @return L'image.
	 */
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

	/**
	 * Méthode utilitaire pour tourner une image de Java AWT.
	 *
	 * @param img L'image à tourner.
	 * @param angle L'angle de rotation en degrés.
	 *
	 * @return L'image tournée.
	 */
	public static @NotNull BufferedImage rotate(final @NotNull BufferedImage img, final double angle) {
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

	/**
	 * Récupère un JLabel contenant l'image de la carte.
	 *
	 * @param card La carte dont on veut l'image.
	 *
	 * @return L'image de la carte.
	 */
	public static @NotNull JLabel getImage(final @NotNull ICarteYuGiOh card) {
		return getImage(card, PlayerGUI.CARD_SIZE);
	}

	/**
	 * Récupère un JLabel contenant l'image de la carte, redimensionnée.
	 *
	 * @param card La carte dont on veut l'image.
	 * @param cardSize La taille de l'image.
	 *
	 * @return L'image de la carte redimensionnée.
	 */
	public static @NotNull JLabel getImage(final @NotNull ICarteYuGiOh card, final @NotNull Dimension cardSize) {
		final var image = loadImage(card.getImageURL());
		final var resizedImage = image.getScaledInstance(cardSize.width, cardSize.height, Image.SCALE_SMOOTH);

		final var label = new JLabel(new ImageIcon(resizedImage));
		label.setSize(cardSize.width, cardSize.height);

		return label;
	}

	/**
	 * Charge les images mises en cache.
	 *
	 * @return La liste des images mises en cache.
	 */
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
