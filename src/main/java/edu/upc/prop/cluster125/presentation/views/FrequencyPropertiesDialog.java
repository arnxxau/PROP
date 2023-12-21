package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.awt.*;

public class FrequencyPropertiesDialog extends JDialog {
    public FrequencyPropertiesDialog(JFrame parent, String [] propierties) {
        super(parent, "Alphabet Information", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = UIManager.getColor("textText");

        JLabel nameLabel = new JLabel("Name: " + propierties[0]);
        JLabel alphabetLabel = new JLabel("Alphabet: " + propierties[1]);
        JLabel modeLabel = new JLabel("Mode: " + propierties[2]);
        JLabel lastModificationLabel = new JLabel("Last Modification: " + propierties[3]);
        JLabel creationLabel = new JLabel("Creation: " + propierties[4]);

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
            String [] s = {"test", "test", "test", "test", "test"};
            FrequencyPropertiesDialog dialog = new FrequencyPropertiesDialog(frame, s);
            dialog.setVisible(true);
        });

        frame.add(showInfoButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
