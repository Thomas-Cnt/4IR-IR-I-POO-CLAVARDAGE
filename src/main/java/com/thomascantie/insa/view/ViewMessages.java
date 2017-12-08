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

public class ViewMessages extends JTextPane {

	private StyledDocument document;
	private Style styleDefaut;
	private Style styleDate;
	private Style stylePseudo;

	public ViewMessages() {
		super();

		this.setEditable(false);

		this.document = (StyledDocument) this.getDocument();

		styleDefaut = this.getStyle("default");

		styleDate = this.addStyle("styleDate", styleDefaut);
		StyleConstants.setFontFamily(styleDate, Font.SANS_SERIF);
		StyleConstants.setForeground(styleDate, Color.MAGENTA);

		stylePseudo = this.addStyle("stylePseudo", styleDate);
		StyleConstants.setForeground(stylePseudo, Color.BLUE);

	}

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

	private void appendDate(Date date) throws BadLocationException {
		DateFormat df = new SimpleDateFormat("dd-MM-YY HH:mm");//dd-MM-YY HH:mm:ss
		this.append(df.format(date), this.styleDate);
	}

	private void appendPseudo(String txt) throws BadLocationException {
		this.append(txt, this.stylePseudo);
	}

	private void appendContent(String  txt) throws BadLocationException {
		this.append(txt, this.styleDefaut);
	}

	private void append(String txt, Style style) throws BadLocationException {
		this.document.insertString(this.document.getLength(), txt, style);
	}

}
