package edu.upc.prop.cluster125.presentation.views;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aquesta classe representa un diàleg d'edició en temps real que permet als usuaris editar contingut de text i guardar els canvis.
 */
public class LiveEditorDialog extends JDialog {

    private JEditorPane editorPane;
    private String content;

    /**
     * Crea una nova instància del diàleg d'edició en temps real.
     *
     * @param f     El marc pare on es mostrarà el diàleg.
     * @param s     El contingut inicial per al JEditorPane.
     */
    public LiveEditorDialog(Frame f, String s) {
        // Configura el diàleg
        super(f, "Live Editor", true);
        setSize(300, 300);
        setLocationRelativeTo(null);

        editorPane = new JEditorPane(JTextComponent.DEFAULT_KEYMAP, s);
        content = s;

        // Crea panells per a una millor organització
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel tablePanel = createTablePanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Afegeix els panells al panell principal
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Afegeix el panell principal al diàleg
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
        JButton doneButton = createLoadSaveButton("Save");

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reemplaça aquesta lògica amb l'acció desitjada
                content = getContent();

                // Tanca el diàleg
                dispose();
            }
        });

        saveLoadPanel.add(doneButton);
        buttonPanel.add(saveLoadPanel, BorderLayout.EAST);

        return buttonPanel;
    }

    /**
     * Obté el contingut actual del JEditorPane.
     *
     * @return El contingut actual del JEditorPane.
     */
    public String getContent() {
        return editorPane.getText();
    }

    /**
     * Mostra el diàleg d'edició en temps real i obté el contingut després que es tanqui el diàleg.
     *
     * @return El contingut del JEditorPane després de tancar el diàleg.
     */
    public String showDialogAndGetContent() {
        // Fes visible el diàleg
        setVisible(true);

        // Retorna el contingut després que es tanqui el diàleg
        return content;
    }

    private JButton createLoadSaveButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(80, 30));
        return button;
    }
}
