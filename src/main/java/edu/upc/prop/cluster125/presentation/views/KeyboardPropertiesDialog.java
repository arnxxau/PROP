package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta classe representa un diàleg per a les propietats d'un teclat.
 */
public class KeyboardPropertiesDialog extends JDialog {
    /**
     * Crea una instància de KeyboardPropertiesDialog amb un frame pare i les propietats del teclat.
     *
     * @param parent      El frame pare del diàleg de propietats.
     * @param properties  Les propietats del teclat.
     */
    public KeyboardPropertiesDialog(JFrame parent, String[] properties) {
        super(parent, "Alphabet Information", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = UIManager.getColor("textText");

        JLabel nameLabel = new JLabel("Name: " + properties[0]);
        JLabel alphabetLabel = new JLabel("Alphabet: " + properties[1]);
        JLabel modeLabel = new JLabel("Frequency: " + properties[2]);
        JLabel lastModificationLabel = new JLabel("Last Modification: " + properties[3]);
        JLabel creationLabel = new JLabel("Creation: " + properties[4]);

        nameLabel.setFont(labelFont);
        nameLabel.setForeground(labelColor);
        alphabetLabel.setFont(labelFont);
        alphabetLabel.setForeground(labelColor);
        modeLabel.setFont(labelFont);
        modeLabel.setForeground(labelColor);
        lastModificationLabel.setFont(labelFont);
        lastModificationLabel.setForeground(labelColor);
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
        infoPanel.add(lastModificationLabel, gbc);

        gbc.gridy = 4;
        infoPanel.add(creationLabel, gbc);

        add(infoPanel);
    }


}
