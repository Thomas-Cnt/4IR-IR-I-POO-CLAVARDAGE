package com.thomascantie.insa.view;

import javax.swing.*;
import java.awt.*;

public class InputPseudo extends JPanel {

    public InputPseudo() {
        super(new BorderLayout());

        JPanel panel = new JPanel(new SpringLayout());

        JTextField field = new JTextField(30);
        JButton btn = new JButton("Valider");

        panel.add(field);
        panel.add(Box.createVerticalGlue());
        panel.add(btn);

        SpringUtilities.makeCompactGrid(
                panel,      // component
                1, 3,       // rows, cols
                6, 6,       // initX, initY
                6, 6);      // xPad, yPad


        this.add(panel);

    }

}
