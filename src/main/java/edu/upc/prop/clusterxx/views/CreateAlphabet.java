package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

public class CreateAlphabet extends JFrame {

    HashSet<Character> c;
    String content = "";

    public CreateAlphabet() {
        // Set up the main frame
        setTitle("Alphabet creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 180);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel radioBtnPanel = new JPanel(new GridLayout(1, 3));
        JPanel buttonPanel = new JPanel(new BorderLayout());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 30));
        formPanel.add(new JLabel("Name: "));
        formPanel.add(nameField);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(radioBtnPanel, BorderLayout.CENTER);

        JButton selectGridButton = loadSaveButton("Type the characters");

        selectGridButton.addActionListener(e -> {
            LiveEditorDialog led = new LiveEditorDialog(this, content);
            content = led.showDialogAndGetContent();

            c = new HashSet<>();

            for (char ch : content.toCharArray()) {
                c.add(ch);
            }

            System.out.println(c);
        });

        // Button Panel
        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton createButton = loadSaveButton("Create");
        createButton.addActionListener(e -> {

            if (nameField.getText().isEmpty())
                JOptionPane.showMessageDialog(this, "Name missing.", "Alert", JOptionPane.WARNING_MESSAGE);

            else if (c.isEmpty()) {
                JOptionPane.showMessageDialog(this, "There are no characters! Please type some characters in the live editor.", "Alert", JOptionPane.WARNING_MESSAGE);
            }

            // Your logic for creating the grid goes here
        });
        saveLoadPanel.add(createButton);

        buttonPanel.add(selectGridButton, BorderLayout.NORTH);
        buttonPanel.add(saveLoadPanel, BorderLayout.SOUTH);


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private JButton loadSaveButton(String text) {
        JButton button = new JButton(text);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CreateAlphabet::new);
    }

}
