package fr.ayfri.tp3.exercice3.gui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterBordered extends MouseAdapter {

	private final boolean canceled;

	public MouseAdapterBordered() {
		this(false);
	}

	public MouseAdapterBordered(final boolean canceled) {this.canceled = canceled;}

	@Override
	public void mouseEntered(final @NotNull MouseEvent e) {
		final var component = (JComponent) e.getComponent();
		if (canceled) {
			try {
				component.setCursor(Cursor.getSystemCustomCursor("Invalid.32x32"));
			} catch (final AWTException ex) {
				throw new RuntimeException(ex);
			}
			return;
		}

		final var border = new LineBorder(Color.WHITE, 4, true);
		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		component.setBorder(border);
	}

	@Override
	public void mouseExited(final @NotNull MouseEvent e) {
		if (canceled) return;

		final var component = (JComponent) e.getComponent();
		component.setBorder(new EmptyBorder(4, 4, 4, 4));
		component.getRootPane().repaint();
	}
}
