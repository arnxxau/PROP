package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * La classe AlphabetPropertiesDialog és una finestra de diàleg que mostra informació detallada sobre un alfabet, incloent-hi el nom, les freqüències associades, els caràcters, la data de l'última modificació i la data de creació.
 */
public class AlphabetPropertiesDialog extends JDialog {
    /**
     * Crea una nova finestra de diàleg AlphabetPropertiesDialog amb la informació de l'alfabet especificada.
     *
     * @param parent      El marc principal en el qual es mostra el diàleg.
     * @param properties  Un array de cadenes que conté les propietats de l'alfabet (nom, freqüències, caràcters, data de l'última modificació i data de creació).
     */
    public AlphabetPropertiesDialog(JFrame parent, String[] properties) {
        super(parent, "Alphabet Information", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        // Panel per mostrar la informació amb un disseny de graella
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = UIManager.getColor("textText");

        // Etiquetes per mostrar les propietats de l'alfabet
        JLabel nameLabel = new JLabel("Name: " + properties[0]);
        JLabel alphabetLabel = new JLabel("Frequencies: " + properties[1]);
        JLabel modeLabel = new JLabel("Characters: "  + properties[2]);
        JLabel lastModificationLabel = new JLabel("Last Modification: "  + properties[3]);
        JLabel creationLabel = new JLabel("Creation: " + properties[4]);

        // Estableix la font i el color de les etiquetes
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

        // Afegeix les etiquetes al panel d'informació
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

        // Afegeix el panel d'informació a la finestra de diàleg
        add(infoPanel);
    }

}
