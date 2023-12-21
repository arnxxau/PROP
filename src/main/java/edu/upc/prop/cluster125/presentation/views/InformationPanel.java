
package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    private JButton loadButton;
    private JButton saveButton;
    private JLabel alphabetInfo;
    private JLabel frequencyInfo;
    private JLabel keyboardInfo;
    private JLabel gridInfo;
    private JLabel saveInfo;

    public InformationPanel() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel();
        JPanel saveLoadPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(100, 30);

        loadButton = new JButton("Load");
        saveButton = new JButton("Save");

        loadButton.setMaximumSize(buttonSize);
        loadButton.setPreferredSize(buttonSize);
        saveButton.setMaximumSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = UIManager.getColor("textText");

        String[] data = CtrlPresentacio.Obtenir_Informacio();

        alphabetInfo = createLabel("Number of alphabets loaded: " + data[0], labelFont, labelColor);
        frequencyInfo = createLabel("Number of frequencies loaded: " + data[1], labelFont, labelColor);
        keyboardInfo = createLabel("Number of keyboards loaded: " + data[2], labelFont, labelColor);
        gridInfo = createLabel("Number of grids loaded: " + data[3], labelFont, labelColor);
        saveInfo = createLabel("Last time saved: " + data[4], labelFont, labelColor);

        infoPanel.add(alphabetInfo);
        infoPanel.add(frequencyInfo);
        infoPanel.add(keyboardInfo);
        infoPanel.add(gridInfo);
        infoPanel.add(saveInfo);

        refreshLabels();

        loadButton.addActionListener(e -> {
            int confirmLoad = JOptionPane.showConfirmDialog(null, "Memory data will be overwritten. Are you sure you want to load?", "Load Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmLoad == JOptionPane.YES_OPTION) {
                CtrlPresentacio.Carregar_Dades();
                refreshLabels();
            }
        });

        saveButton.addActionListener(e -> {
            int confirmSave = JOptionPane.showConfirmDialog(null, "Disk data will be overwritten. Are you sure you want to save?", "Save Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmSave == JOptionPane.YES_OPTION) {
                CtrlPresentacio.Guardar_Dades();
                refreshLabels();
            }
        });

        buttonPanel.add(loadButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(saveButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        saveLoadPanel.setLayout(new BoxLayout(saveLoadPanel, BoxLayout.Y_AXIS));
        saveLoadPanel.add(Box.createVerticalGlue());
        saveLoadPanel.add(buttonPanel);
        saveLoadPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

        mainPanel.add(saveLoadPanel, BorderLayout.EAST);
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    public void refreshLabels() {
        String[] data = CtrlPresentacio.Obtenir_Informacio();

        alphabetInfo.setText("Number of alphabets loaded: " + data[0]);
        frequencyInfo.setText("Number of frequencies loaded: " + data[1]);
        keyboardInfo.setText("Number of keyboards loaded: " + data[2]);
        gridInfo.setText("Number of grids loaded: " + data[3]);
        saveInfo.setText("Last time saved: " + data[4]);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Information Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new InformationPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
