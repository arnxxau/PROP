package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.io.File;

/**
 * La classe FileSelectorDialog és una finestra que permet a l'usuari seleccionar un fitxer des del sistema de fitxers local.
 */
public class FileSelectorDialog extends JFrame {

    /**
     * Crea una finestra de selecció de fitxers.
     */
    public FileSelectorDialog() {
        setLocationRelativeTo(null);
    }

    /**
     * Mostra un diàleg de selecció de fitxers i permet a l'usuari seleccionar un fitxer.
     *
     * @return La ruta absoluta del fitxer seleccionat o null si l'usuari cancel·la la selecció.
     */
    public String selectDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Fitxer seleccionat: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(this, "Selecció cancel·lada");
            return null;
        }
    }

    /**
     * Mètode principal per provar la finestra de selecció de fitxers.
     *
     * @param args Els arguments de la línia de comandes (no s'utilitzen en aquest cas).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileSelectorDialog().selectDirectory());
    }
}
