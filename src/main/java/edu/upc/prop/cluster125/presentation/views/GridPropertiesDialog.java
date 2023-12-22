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
        super(parent, "Informació de la Graella", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel panellInformacio = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font fontEtiqueta = new Font("Arial", Font.PLAIN, 14);
        Color colorEtiqueta = UIManager.getColor("textText");

        JLabel etiquetaNom = new JLabel("Nom: " + propietats[0]);
        JLabel etiquetaSlots = new JLabel("Nombre de slots: " + propietats[1]);
        JLabel etiquetaMida = new JLabel("Mida: " + propietats[2]);
        JLabel etiquetaDarreraModificacio = new JLabel("Darrera modificació: " + propietats[3]);
        JLabel etiquetaCreacio = new JLabel("Creació: " + propietats[4]);

        etiquetaNom.setFont(fontEtiqueta);
        etiquetaNom.setForeground(colorEtiqueta);
        etiquetaSlots.setFont(fontEtiqueta);
        etiquetaSlots.setForeground(colorEtiqueta);
        etiquetaMida.setFont(fontEtiqueta);
        etiquetaMida.setForeground(colorEtiqueta);
        etiquetaDarreraModificacio.setFont(fontEtiqueta);
        etiquetaDarreraModificacio.setForeground(colorEtiqueta);
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
        panellInformacio.add(etiquetaDarreraModificacio, gbc);

        gbc.gridy = 4;
        panellInformacio.add(etiquetaCreacio, gbc);

        add(panellInformacio);
    }

    /**
     * El mètode principal per provar el GridPropertiesDialog.
     *
     * @param args Els arguments de línia de comandes.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestor de Graella");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton mostraInformacioButton = new JButton("Mostra Informació de la Graella");
        mostraInformacioButton.addActionListener(e -> {
            String[] s = {"test", "test", "test", "test", "test"};
            GridPropertiesDialog dialeg = new GridPropertiesDialog(frame, s);
            dialeg.setVisible(true);
        });

        frame.add(mostraInformacioButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
