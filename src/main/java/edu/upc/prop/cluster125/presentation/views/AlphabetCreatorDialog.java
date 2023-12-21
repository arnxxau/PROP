package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;
import edu.upc.prop.cluster125.exceptions.ExisteixID_Exception;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class AlphabetCreatorDialog extends JDialog {

    private HashSet<Character> c;
    private String content = "";

    public AlphabetCreatorDialog(Frame parent) {
        super(parent, "Alphabet creator", true);
        setSize(200, 200);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel radioBtnPanel = new JPanel(new GridLayout(1, 3));
        JPanel buttonPanel = new JPanel(new BorderLayout());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 30));
        formPanel.add(new JLabel("Name: "));
        formPanel.add(nameField);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(radioBtnPanel, BorderLayout.CENTER);

        JButton selectGridButton = loadSaveButton("Type the characters");

        selectGridButton.addActionListener(e -> {
            LiveEditorDialog led = new LiveEditorDialog(parent, content);
            content = led.showDialogAndGetContent();

            c = new HashSet<>();

            for (char ch : content.toCharArray()) {
                c.add(ch);
            }

            System.out.println(c);
        });

        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton createButton = loadSaveButton("Create");
        createButton.addActionListener(e -> {

            if (nameField.getText().isEmpty()) {
                showMessage("Name missing.", "Alert", JOptionPane.WARNING_MESSAGE);
            } else if (c.isEmpty()) {
                showMessage("There are no characters! Please type some characters in the live editor.", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    CtrlPresentacio.Afegir_Alfabet(nameField.getText(), c);
                    dispose();
                } catch (ExisteixID_Exception ex) {
                    JOptionPane.showMessageDialog(parent, "The name already exists!","Name error",JOptionPane.ERROR_MESSAGE);
                }
                 // Close the dialog after successful creation
            }
        });

        saveLoadPanel.add(createButton);

        buttonPanel.add(selectGridButton, BorderLayout.NORTH);
        buttonPanel.add(saveLoadPanel, BorderLayout.SOUTH);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setLocationRelativeTo(parent);

        setVisible(true);
    }

    private JButton loadSaveButton(String text) {
        return new JButton(text);
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            new AlphabetCreatorDialog(frame);
        });
    }
}
