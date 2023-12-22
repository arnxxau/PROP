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
        super(parent, "Informació de l'alfabet", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = UIManager.getColor("textText");

        JLabel nameLabel = new JLabel("Nom: " + properties[0]);
        JLabel alphabetLabel = new JLabel("Alfabet: " + properties[1]);
        JLabel modeLabel = new JLabel("Freqüència: " + properties[2]);
        JLabel lastModificationLabel = new JLabel("Última modificació: " + properties[3]);
        JLabel creationLabel = new JLabel("Creació: " + properties[4]);

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

    /**
     * El mètode principal per provar KeyboardPropertiesDialog.
     *
     * @param args Els arguments de la línia de comandes.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestor de Freqüència");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton showInfoButton = new JButton("Mostrar informació de freqüència");
        showInfoButton.addActionListener(e -> {
            String[] s = {"test", "test", "test", "test", "test"};
            KeyboardPropertiesDialog dialog = new KeyboardPropertiesDialog(frame, s);
            dialog.setVisible(true);
        });

        frame.add(showInfoButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
