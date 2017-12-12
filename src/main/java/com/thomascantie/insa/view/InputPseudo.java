package com.thomascantie.insa.view;

import com.thomascantie.insa.controler.ListenerEnteredPseudo;
import com.thomascantie.insa.controler.ListenerPseudoValid;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la vue de saisie du pseudo
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see JPanel
 */
public class InputPseudo extends JPanel {

	/**
	 * Champ de saisie
	 */
	private JTextField field;

	/**
	 * Constructeur
	 */
	public InputPseudo() {
		super(new BorderLayout());

		JPanel panel = new JPanel(new SpringLayout());

		JLabel label = new JLabel("Entrez votre pseudo :", JLabel.CENTER);
		this.field = new JTextField(30);
		this.field.addKeyListener(new ListenerEnteredPseudo(this));
		label.setLabelFor(this.field);

		JButton btn = new JButton("Valider");
		btn.addActionListener(e -> switchContentPane());

		panel.add(label);
		panel.add(Box.createHorizontalGlue());
		panel.add(this.field);
		panel.add(Box.createVerticalGlue());
		panel.add(btn);

		SpringUtilities.makeCompactGrid(
				panel,      // component
				1, 5,       // rows, cols
				6, 6,       // initX, initY
				6, 6);      // xPad, yPad

		this.add(panel, BorderLayout.CENTER);

	}

	/**
	 * Modificateur
	 * Change la vue gérer par l'application
	 */
	private void switchContentPane() {
		if (!this.getPseudo().isEmpty())
			new ListenerPseudoValid(this).process();
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return le pseudo saisi
	 */
	public String getPseudo() {
		return this.field.getText().trim();
	}

}
