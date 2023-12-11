package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class EditFrequency extends JFrame implements DialogCallback {

    String content;

    public EditFrequency() {
        // Set up the main frame
        setTitle("Frequency creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new BorderLayout());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 30));
        formPanel.add(new JLabel("Name: "));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Alphabet: "));
        Vector<Object> alphabetElements = new Vector<>();
        alphabetElements.add(1);
        alphabetElements.add(2);
        alphabetElements.add("geeks");
        alphabetElements.add("forGeeks");
        alphabetElements.add(3);
        alphabetElements = CtrlPresentacio.getAlphabets();
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

        JPanel radioBtnPanel = new JPanel(new GridLayout(1, 3));
        radioBtnPanel.add(liveTextRadioButton);
        radioBtnPanel.add(rawFileRadioButton);
        radioBtnPanel.add(textFileRadioButton);

        mainPanel.add(formPanel);
        mainPanel.add(radioBtnPanel);

        // Button Panel
        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton createButton = loadSaveButton("Create");
        createButton.addActionListener(e -> {

            if (liveTextRadioButton.isSelected()) {
                //LiveEditor le = new LiveEditor(this);
                LiveEditorDialog led = new LiveEditorDialog(this, content);
                System.out.println(led.showDialogAndGetContent());
            }
            else if (rawFileRadioButton.isSelected()) {
                DirectorySelector ds = new DirectorySelector();
                String url = ds.selectDirectory();
                System.out.println(url);
            }
            else if (textFileRadioButton.isSelected()) {
                DirectorySelector ds = new DirectorySelector();
                String url = ds.selectDirectory();
            }
            else {
                JOptionPane.showMessageDialog(null, "Please select an extraction mode!");
            }
        });
        saveLoadPanel.add(createButton);
        buttonPanel.add(saveLoadPanel, BorderLayout.EAST);

        mainPanel.add(buttonPanel);

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
        SwingUtilities.invokeLater(EditFrequency::new);
    }

    @Override
    public void onDialogClosed(String content) {
        System.out.println(content);
    }
}
