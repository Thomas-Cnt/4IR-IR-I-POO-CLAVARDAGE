package com.thomascantie.insa.view;

import com.thomascantie.insa.model.core.User;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe représentative de la vue des messages échangés
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see JTextField
 */
public class ViewMessages extends JTextPane {

	/**
	 * Document stylisé
	 */
	private StyledDocument document;
	/**
	 * Style par défaut pour le contenu des messages
	 */
	private Style styleDefaut;
	/**
	 * Style pour les dates
	 */
	private Style styleDate;
	/**
	 * Style pour le pseudo des expéditeurs
	 */
	private Style stylePseudo;

	/**
	 * Constructeur
	 */
	public ViewMessages() {
		super();

		this.setEditable(false);

		this.document = (StyledDocument) this.getDocument();

		this.styleDefaut = this.getStyle("default");

		this.styleDate = this.addStyle("styleDate", this.styleDefaut);
		StyleConstants.setFontFamily(this.styleDate, Font.SANS_SERIF);
		StyleConstants.setForeground(this.styleDate, Color.MAGENTA);

		this.stylePseudo = this.addStyle("stylePseudo", this.styleDate);
		StyleConstants.setForeground(this.stylePseudo, Color.BLUE);

	}

	/**
	 * Modificateur
	 * Ajoute un message
	 *
	 * @param date la date du message
	 * @param user l'expéditeur du message
	 * @param txt  le contenu du message
	 */
	public void writeMessage(Date date, User user, String txt) {
		try {
			this.appendDate(date);
			this.appendContent(" :: ");
			this.appendPseudo(user.getPseudo());
			this.appendContent(" > ");
			this.appendContent(txt);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ajoute la date stylisée
	 *
	 * @param date la date à ajouter
	 * @throws BadLocationException if the location to append is not valid
	 */
	private void appendDate(Date date) throws BadLocationException {
		DateFormat df = new SimpleDateFormat("dd-MM-YY HH:mm");//dd-MM-YY HH:mm:ss
		this.append(df.format(date), this.styleDate);
	}

	/**
	 * Ajoute l'expéditeur stylisé
	 *
	 * @param txt le pseudo de l'expéditeur
	 * @throws BadLocationException if the location to append is not valid
	 */
	private void appendPseudo(String txt) throws BadLocationException {
		this.append(txt, this.stylePseudo);
	}

	/**
	 * Ajoute le contenu stylisé
	 *
	 * @param txt le contenu à ajouter
	 * @throws BadLocationException if the location to append is not valid
	 */
	private void appendContent(String txt) throws BadLocationException {
		this.append(txt, this.styleDefaut);
	}

	/**
	 * Ajoute du texte stylisé
	 *
	 * @param txt   le texte à ajouter
	 * @param style le style à appliquer
	 * @throws BadLocationException if the location to append is not valid
	 */
	private void append(String txt, Style style) throws BadLocationException {
		this.document.insertString(this.document.getLength(), txt, style);
	}

}
