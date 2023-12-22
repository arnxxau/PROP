package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aquesta classe representa la vista per gestionar els alfabets.
 */
public class ManageAlphabets extends JFrame {

    private JTable table;
    private JButton modifyButton;
    private JButton createButton;
    private JButton deleteButton;
    private JButton backButton;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;

    /**
     * Crea una nova instància de la vista de gestió d'alfabets.
     */
    public ManageAlphabets() {
        // Configura el marc principal
        setTitle("Alphabet manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Crea panells per a una millor organització
        mainPanel = new JPanel(new BorderLayout());
        tablePanel = new JPanel(new BorderLayout());

        String[] column = {"NAME", "CHARS", "SIZE"};

        table = new JTable(CtrlPresentacio.Demanar_full_Alfabet(), column);

        scrollPane = new JScrollPane(table);

        tablePanel.add(scrollPane);

        buttonPanel = new JPanel(new BorderLayout());
        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        modifyButton = new JButton("Modify");
        createButton = new JButton("Create");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");

        modifyButton.setPreferredSize(new Dimension(80, 30));
        createButton.setPreferredSize(new Dimension(80, 30));
        deleteButton.setPreferredSize(new Dimension(80, 30));
        backButton.setPreferredSize(new Dimension(80, 30));

        // Afegeix ActionListeners als botons
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitueix això amb la lògica real per al botó
                JOptionPane.showMessageDialog(null,  "Modify button clicked");
            }
        });

        createButton.addActionListener(e -> {
            // Substitueix això amb la lògica real per al botó
            AlphabetCreatorDialog ca = new AlphabetCreatorDialog(this);

            // Actualitza el model de la taula amb les noves dades
            DefaultTableModel model = new DefaultTableModel(CtrlPresentacio.Demanar_full_Alfabet(), column);
            table.setModel(model);

            // Notifica a la taula que les dades han canviat
            model.fireTableDataChanged();
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitueix això amb la lògica real per al botó
                JOptionPane.showMessageDialog(null, "Delete button clicked");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tanca el marc actual
                setVisible(false);
                // Obre la vista principal
                new MainView().setVisible(true);
            }
        });

        // Afegeix els botons al panell
        saveLoadPanel.add(modifyButton);
        saveLoadPanel.add(createButton);
        saveLoadPanel.add(deleteButton);
        saveLoadPanel.add(backButton);

        buttonPanel.add(saveLoadPanel, BorderLayout.EAST);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Afegeix panells al panell principal
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Afegeix el panell principal al marc
        add(mainPanel);

        // Fes visible el marc
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
