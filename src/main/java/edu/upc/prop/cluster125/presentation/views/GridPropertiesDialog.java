package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta classe representa un diàleg per mostrar les propietats d'una graella.
 */
public class GridPropertiesDialog extends JDialog {
    /**
     * Crea un nou GridPropertiesDialog.
     *
     * @param parent     El JFrame pare.
     * @param properties Un array de propietats per mostrar.
     */
    public GridPropertiesDialog(JFrame parent, String [] properties) {
        super(parent, "Grid Information", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = UIManager.getColor("textText");

        JLabel nameLabel = new JLabel("Name: " + properties[0]);
        JLabel alphabetLabel = new JLabel("Number of slots: " + properties[1]);
        JLabel modeLabel = new JLabel("Size: " + properties[2]);
        JLabel creationLabel = new JLabel("Creation: " + properties[4]);

        nameLabel.setFont(labelFont);
        nameLabel.setForeground(labelColor);
        alphabetLabel.setFont(labelFont);
        alphabetLabel.setForeground(labelColor);
        modeLabel.setFont(labelFont);
        modeLabel.setForeground(labelColor);
        creationLabel.setFont(labelFont);
        creationLabel.setForeground(labelColor);

        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        infoPanel.add(alphabetLabel, gbc);

        gbc.gridy = 2;
        infoPanel.add(modeLabel, gbc);

        gbc.gridy = 3;
        infoPanel.add(creationLabel, gbc);

        add(infoPanel);
    }

}
