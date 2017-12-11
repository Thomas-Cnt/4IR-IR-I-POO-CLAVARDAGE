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

		this.field = new JTextField(30);
		JButton btn = new JButton("Valider");
		btn.addActionListener(e -> switchContentPane());

		panel.add(this.field);
		this.field.addKeyListener(new ListenerEnteredPseudo(this));
		panel.add(Box.createVerticalGlue());
		panel.add(btn);

		SpringUtilities.makeCompactGrid(
				panel,      // component
				1, 3,       // rows, cols
				6, 6,       // initX, initY
				6, 6);      // xPad, yPad

		this.add(panel);

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
