package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.awt.*;

public class ErrorView extends JDialog {

    public ErrorView(String message) {
        setTitle("ERROR");
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.add(new JLabel(message));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> setVisible(false));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);

        mainPanel.add(messagePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setSize(300, 110);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        new ErrorView("Error message goes here");
    }
}
