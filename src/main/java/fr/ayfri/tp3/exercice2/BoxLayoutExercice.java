package fr.ayfri.tp3.exercice2;

import fr.ayfri.tp3.GraphicalExercice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class BoxLayoutExercice extends GraphicalExercice {
	private @Nullable JTextField name;
	private @Nullable JTextField password;

	public BoxLayoutExercice() {
		title = "BoxLayout";
		description = "Afficher 2 inputs dont 1 de type password ainsi que 2 fen\u00EAtres de dialogues utilisant les valeurs des inputs.";
	}

	public static void main(final String @NotNull [] args) {
		new BoxLayoutExercice().run();
	}

	private void createText(final @NotNull JComponent root, final @NotNull String text, final boolean isPassword) {
		final var panel = new JPanel();
		final var label = new JLabel(text);
		final var textField = isPassword ? new JPasswordField() : new JTextField();
		textField.setPreferredSize(new Dimension(120, 30));

		if (isPassword) name = textField;
		else password = textField;

		panel.add(label);
		panel.add(textField);
		root.add(panel);
	}

	private @NotNull String getName() {
		assert name != null;
		return name.getText();
	}

	private @NotNull String getPassword() {
		assert password != null;
		return password.getText();
	}

	private void createButton(final @NotNull JComponent root, final @NotNull String name) {
		final var button = new JButton(name);
		button.addActionListener(e -> JOptionPane.showMessageDialog(root, """
					Vous avez appuy\u00E9 sur : %s
					nom : %s
					mot de passe : %s
				""".formatted(name, getName(), getPassword()), "Message", JOptionPane.INFORMATION_MESSAGE));
		root.add(button);
	}

	@Override
	public void run(@NotNull final JFrame frame) {
		frame.setSize(650, 250);
		final var contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

		final var textFieldsPanel = new JPanel();
		contentPane.add(textFieldsPanel);
		textFieldsPanel.setLayout(new BoxLayout(textFieldsPanel, BoxLayout.Y_AXIS));

		createText(textFieldsPanel, "Nom: ", false);
		createText(textFieldsPanel, "Mot de passe: ", true);

		final var buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

		createButton(buttonsPanel, "Ok");
		buttonsPanel.add(Box.createHorizontalGlue());
		createButton(buttonsPanel, "Cancel");
	}
}
