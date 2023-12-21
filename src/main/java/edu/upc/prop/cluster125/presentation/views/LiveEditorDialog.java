package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LiveEditorDialog extends JDialog {

    private JEditorPane editorPane;
    private String content;

    public LiveEditorDialog(Frame f, String s) {
        // Set up the dialog
        super(f, "Live editor", true);
        setSize(300, 300);
        setLocationRelativeTo(null);

        editorPane = new JEditorPane(JTextComponent.DEFAULT_KEYMAP, s);
        content = s;

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel tablePanel = createTablePanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add panels to the main panel
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the dialog
        add(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(editorPane);
        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new BorderLayout());

        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton doneButton = createLoadSaveButton("Done");

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace this logic with your desired action
                content = getContent();

                // Close the dialog
                dispose();
            }
        });

        saveLoadPanel.add(doneButton);
        buttonPanel.add(saveLoadPanel, BorderLayout.EAST);

        return buttonPanel;
    }

    public String getContent() {
        return editorPane.getText();
    }

    public String showDialogAndGetContent() {
        // Make the dialog visible
        setVisible(true);

        // Return the content after the dialog is closed
        return content;
    }

    private JButton createLoadSaveButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(80, 30));
        return button;
    }
}
