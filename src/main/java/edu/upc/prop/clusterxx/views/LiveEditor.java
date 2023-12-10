package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LiveEditor extends JFrame {

    JEditorPane editorPane = new JEditorPane();

    CreateFrequency cf;
    public LiveEditor(CreateFrequency cf) {
        // Set up the main frame
        setTitle("Live editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel tablePanel = createTablePanel();
        JPanel buttonPanel = createButtonPanel();

        this.cf = cf;

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add panels to the main panel
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
        setLocationRelativeTo(null);
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
                String content = getContent();

                cf.doneLiveText(content);

                setVisible(false);

                System.out.println("Content in the editor:\n" + content);
            }
        });

        saveLoadPanel.add(doneButton);
        buttonPanel.add(saveLoadPanel, BorderLayout.EAST);

        return buttonPanel;
    }

    public String getContent() {
        return editorPane.getText();
    }

    private JButton createLoadSaveButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(80, 30));
        return button;
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(LiveEditor::new);
    // }
}
