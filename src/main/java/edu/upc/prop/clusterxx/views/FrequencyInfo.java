package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.awt.*;

public class FrequencyInfo extends JDialog {
    public FrequencyInfo(JFrame parent) {
        super(parent, "Frequency Information", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = Color.DARK_GRAY;

        JLabel nameLabel = new JLabel("Name: ");
        JLabel alphabetLabel = new JLabel("Alphabet: ");
        JLabel modeLabel = new JLabel("Mode: ");
        JLabel lastModificationLabel = new JLabel("Last Modification: ");
        JLabel creationLabel = new JLabel("Creation: ");

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Frequency Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton showInfoButton = new JButton("Show Frequency Info");
        showInfoButton.addActionListener(e -> {
            FrequencyInfo dialog = new FrequencyInfo(frame);
            dialog.setVisible(true);
        });

        frame.add(showInfoButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
