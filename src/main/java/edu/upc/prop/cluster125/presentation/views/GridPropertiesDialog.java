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
     * @param propietats Un array de propietats per mostrar.
     */
    public GridPropertiesDialog(JFrame parent, String[] propietats) {
        super(parent, "Grid Information", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel panellInformacio = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font fontEtiqueta = new Font("Arial", Font.PLAIN, 14);
        Color colorEtiqueta = UIManager.getColor("textText");

        JLabel etiquetaNom = new JLabel("Name: " + propietats[0]);
        JLabel etiquetaSlots = new JLabel("Number of slots: " + propietats[1]);
        JLabel etiquetaMida = new JLabel("Size: " + propietats[2]);
        JLabel etiquetaCreacio = new JLabel("Creation: " + propietats[4]);

        etiquetaNom.setFont(fontEtiqueta);
        etiquetaNom.setForeground(colorEtiqueta);
        etiquetaSlots.setFont(fontEtiqueta);
        etiquetaSlots.setForeground(colorEtiqueta);
        etiquetaMida.setFont(fontEtiqueta);
        etiquetaMida.setForeground(colorEtiqueta);
        etiquetaCreacio.setFont(fontEtiqueta);
        etiquetaCreacio.setForeground(colorEtiqueta);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panellInformacio.add(etiquetaNom, gbc);

        gbc.gridy = 1;
        panellInformacio.add(etiquetaSlots, gbc);

        gbc.gridy = 2;
        panellInformacio.add(etiquetaMida, gbc);
        gbc.gridy = 3;
        panellInformacio.add(etiquetaCreacio, gbc);

        add(panellInformacio);
    }
}
