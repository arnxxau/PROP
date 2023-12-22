package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;
import edu.upc.prop.cluster125.exceptions.ExisteixID_Exception;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * La classe AlphabetCreatorDialog és una finestra de diàleg que permet als usuaris crear un alfabet especificant el nom i els caràcters associats.
 */
public class AlphabetCreatorDialog extends JDialog {

    private HashSet<Character> c;
    private String content = "";

    /**
     * Crea una nova finestra de diàleg AlphabetCreatorDialog.
     *
     * @param parent El marc pare en el qual es mostra el diàleg.
     */
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

        /**
         * Crea un botó per obrir l'editor de caràcters en viu.
         */
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

        /**
         * Crea un botó per crear l'alfabet.
         */
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
                // Tanca el diàleg després de la creació reeixida
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

    /**
     * Crea i retorna un botó amb el text especificat.
     *
     * @param text Text del botó.
     * @return Un JButton amb el text especificat.
     */
    private JButton loadSaveButton(String text) {
        return new JButton(text);
    }

    /**
     * Mostra un missatge d'alerta.
     *
     * @param message      El missatge a mostrar.
     * @param title        El títol del missatge.
     * @param messageType  El tipus de missatge (e.g., JOptionPane.WARNING_MESSAGE).
     */
    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

}
