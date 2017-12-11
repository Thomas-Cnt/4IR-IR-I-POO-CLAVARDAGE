package com.thomascantie.insa.view;

import com.thomascantie.insa.controler.ListenerEnteredPseudo;
import com.thomascantie.insa.controler.ListenerPseudoValid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputPseudo extends JPanel {

    private JTextField field;

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

    private void switchContentPane() {
        new ListenerPseudoValid(this).process();
    }

    public String getPseudo() {
        return this.field.getText().trim();
    }

}
