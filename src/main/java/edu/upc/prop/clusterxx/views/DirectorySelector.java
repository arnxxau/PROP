package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.io.File;

public class DirectorySelector extends JFrame {

    DirectorySelector() {
        setLocationRelativeTo(null);
    }

    public String selectDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Selected Directory: " + selectedDirectory.getAbsolutePath());
            return selectedDirectory.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(this, "Selection canceled");
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DirectorySelector().selectDirectory());
    }
}