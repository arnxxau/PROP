package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.io.File;

public class FileSelectorDialog extends JFrame {

    public FileSelectorDialog() {
        setLocationRelativeTo(null);
    }

    public String selectDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Selected File: " + selectedDirectory.getAbsolutePath());
            return selectedDirectory.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(this, "Selection canceled");
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileSelectorDialog().selectDirectory());
    }
}