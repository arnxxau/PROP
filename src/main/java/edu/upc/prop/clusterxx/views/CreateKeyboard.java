package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CreateKeyboard extends JFrame implements DialogCallback {

    String content;

    public CreateKeyboard() {
        // Set up the main frame
        setTitle("Keyboard creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(330, 280);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JPanel radioBtnPanel = new JPanel(new GridLayout(1, 3));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 30));
        formPanel.add(new JLabel("Name: "));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Alphabet: "));
        Vector<Object> alphabetElements = CtrlPresentacio.getAlphabets();
        JComboBox<Object> alphabetComboBox = new JComboBox<>(alphabetElements);
        formPanel.add(alphabetComboBox);

        // Radio Buttons
        JRadioButton liveTextRadioButton = new JRadioButton("Live text");
        JRadioButton rawFileRadioButton = new JRadioButton("Raw file");
        JRadioButton textFileRadioButton = new JRadioButton("Text file");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(liveTextRadioButton);
        radioButtonGroup.add(rawFileRadioButton);
        radioButtonGroup.add(textFileRadioButton);

        radioBtnPanel.add(liveTextRadioButton);
        radioBtnPanel.add(rawFileRadioButton);
        radioBtnPanel.add(textFileRadioButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(radioBtnPanel, BorderLayout.CENTER);

        // Button Panel
        JButton createButton = loadSaveButton("Create");
        createButton.addActionListener(e -> {
            if (liveTextRadioButton.isSelected()) {
                LiveEditorDialog led = new LiveEditorDialog(this, content);
                System.out.println(led.showDialogAndGetContent());
            } else if (rawFileRadioButton.isSelected() || textFileRadioButton.isSelected()) {
                DirectorySelector ds = new DirectorySelector();
                String url = ds.selectDirectory();
                System.out.println(url);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an extraction mode!");
            }
        });
        buttonPanel.add(createButton);

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
        SwingUtilities.invokeLater(CreateKeyboard::new);
    }

    @Override
    public void onDialogClosed(String content) {
        System.out.println(content);
    }
}
