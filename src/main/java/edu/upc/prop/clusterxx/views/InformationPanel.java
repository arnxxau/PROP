package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationPanel extends JPanel {

    private JTable table;
    private JButton modifyButton;
    private JButton createButton;
    private JButton deleteButton;
    private JButton backButton;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JPanel saveLoadPanel;
    private JScrollPane scrollPane;

    public InformationPanel(JFrame parent) {
        setLayout(new BorderLayout());

        // Create panels for better organization
        mainPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Set BoxLayout for vertical alignment
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding


        buttonPanel = new JPanel(); // Remove BorderLayout here
        saveLoadPanel = new JPanel();

        // Use BoxLayout to stack buttons vertically in the buttonPanel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));


        Dimension buttonSize = new Dimension(100, 30); // Adjust width and height as needed

        modifyButton = new JButton("Load");
        createButton = new JButton("Save");
        deleteButton = new JButton("Update");

        setButtonSize(modifyButton, buttonSize);
        setButtonSize(createButton, buttonSize);
        setButtonSize(deleteButton, buttonSize);

        // Style for labels
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = UIManager.getColor("textText");

        JLabel alphabetInfo = new JLabel("Number of alphabets loaded: ");
        JLabel frequencyInfo = new JLabel("Number of frequencies loaded: ");
        JLabel keyboardInfo = new JLabel("Number of keyboards loaded: ");
        JLabel gridInfo = new JLabel("Number of grids loaded: ");
        JLabel saveInfo = new JLabel("Last time saved: ");
        //JLabel alphabetInfo = new JLabel("alphabet");

        // Set font and color for labels
        setLabelStyle(alphabetInfo, labelFont, labelColor);
        setLabelStyle(frequencyInfo, labelFont, labelColor);
        setLabelStyle(keyboardInfo, labelFont, labelColor);
        setLabelStyle(gridInfo, labelFont, labelColor);
        setLabelStyle(saveInfo, labelFont, labelColor);

        infoPanel.add(alphabetInfo);
        infoPanel.add(frequencyInfo);
        infoPanel.add(keyboardInfo);
        infoPanel.add(gridInfo);
        infoPanel.add(saveInfo);




        // Add ActionListeners to buttons
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace this with the actual logic for the button
                JOptionPane.showMessageDialog(null, "Modify button clicked");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace this with the actual logic for the button
                JOptionPane.showMessageDialog(null, "Delete button clicked");
            }
        });


        // Add buttons to the buttonPanel
        buttonPanel.add(modifyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add separation
        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add separation
        buttonPanel.add(deleteButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add rigid vertical struts before and after buttonPanel
        saveLoadPanel.setLayout(new BoxLayout(saveLoadPanel, BoxLayout.Y_AXIS));
        saveLoadPanel.add(Box.createVerticalGlue());
        saveLoadPanel.add(buttonPanel);
        saveLoadPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));


        mainPanel.add(saveLoadPanel, BorderLayout.EAST); // Add saveLoadPanel directly to the mainPanel
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // Add the main panel to this JPanel (ManageFrequency)
        add(mainPanel);
    }

    public static void main(String[] args) {
        // This should be added to a JFrame in your application's main method
        // For example:
        JFrame frame = new JFrame("Frequency manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new InformationPanel(frame));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setButtonSize(JButton button, Dimension size) {
        //button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }

    private void setLabelStyle(JLabel label, Font font, Color color) {
        label.setFont(font);
        label.setForeground(color);
    }
}
