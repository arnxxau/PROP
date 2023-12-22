package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;
import edu.upc.prop.cluster125.exceptions.CaractersfromFreq_notInAlph_Exception;
import edu.upc.prop.cluster125.exceptions.ExisteixID_Exception;
import edu.upc.prop.cluster125.exceptions.badExtraction_Exception;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

/**
 * La classe FrequencyCreatorDialog és una finestra de diàleg que permet als usuaris crear una freqüència especificant el nom, l'alfabet associat i el mode d'extracció de dades.
 */
public class FrequencyCreatorDialog extends JDialog {

    private String content;

    /**
     * Crea una nova finestra de diàleg FrequencyCreatorDialog.
     *
     * @param parent El marc pare en el qual es mostra el diàleg.
     */
    public FrequencyCreatorDialog(JFrame parent) {
        super(parent, "Frequency creator", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(330, 280);
        setLocationRelativeTo(parent);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JPanel radioBtnPanel = new JPanel(new GridLayout(1, 3));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 30));
        formPanel.add(new JLabel("Nom: "));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Alfabet: "));
        Vector<String> alphabetElements = CtrlPresentacio.getAlphabets();
        JComboBox<String> alphabetComboBox = new JComboBox<>(alphabetElements);
        formPanel.add(alphabetComboBox);

        // Radio Buttons
        JRadioButton liveTextRadioButton = new JRadioButton("Text en directe");
        JRadioButton liveFreqRadioButton = new JRadioButton("Freqüència en directe");
        JRadioButton fileTextRadioButton = new JRadioButton("Text de fitxer");
        JRadioButton fileFreqRadioButton = new JRadioButton("Freqüència de fitxer");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(liveTextRadioButton);
        radioButtonGroup.add(liveFreqRadioButton);
        radioButtonGroup.add(fileFreqRadioButton);
        radioButtonGroup.add(fileTextRadioButton);

        radioBtnPanel.add(liveTextRadioButton);
        radioBtnPanel.add(liveFreqRadioButton);
        radioBtnPanel.add(fileTextRadioButton);
        radioBtnPanel.add(fileFreqRadioButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(radioBtnPanel, BorderLayout.CENTER);

        // Button Panel
        JButton createButton = loadSaveButton("Crear");
        createButton.addActionListener(e -> {
            try {
                if (nameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(parent, "Escriu un nom!");
                } else if (liveTextRadioButton.isSelected() || liveFreqRadioButton.isSelected()) {
                    LiveEditorDialog led = new LiveEditorDialog(parent, content);
                    content = led.showDialogAndGetContent();
                    if (liveTextRadioButton.isSelected())
                        CtrlPresentacio.AfegirTextFreqMa(alphabetComboBox.getSelectedItem().toString(), nameField.getText(), content);
                    else
                        CtrlPresentacio.AfegirListFreqMa(alphabetComboBox.getSelectedItem().toString(), nameField.getText(), content);
                    dispose();
                } else if (fileTextRadioButton.isSelected() || fileFreqRadioButton.isSelected()) {
                    FileSelectorDialog ds = new FileSelectorDialog();
                    String url = ds.selectDirectory();
                    if (fileTextRadioButton.isSelected())
                        CtrlPresentacio.AfegirTextFreqFromPath(nameField.getText(), alphabetComboBox.getSelectedItem().toString(), url);
                    else
                        CtrlPresentacio.AfegirListFreqFromPath(nameField.getText(), alphabetComboBox.getSelectedItem().toString(), url);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un mode d'extracció!");
                }
            } catch (CaractersfromFreq_notInAlph_Exception ex) {
                JOptionPane.showMessageDialog(parent, "La freqüència conté caràcters incompatibles amb l'alfabet seleccionat", "Error de caràcters", JOptionPane.ERROR_MESSAGE);
            } catch (ExisteixID_Exception ex) {
                JOptionPane.showMessageDialog(parent, "El nom ja existeix!", "Error de nom", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "S'ha produït un error en extreure el fitxer", "Error de fitxer", JOptionPane.ERROR_MESSAGE);
            } catch (badExtraction_Exception ex) {
                JOptionPane.showMessageDialog(parent, "S'ha produït un error en intentar extreure la freqüència", "Extracció incorrecta", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(content);
        });

        buttonPanel.add(createButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the dialog
        add(mainPanel);

        // Make the dialog visible
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private JButton loadSaveButton(String text) {
        return new JButton(text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            new FrequencyCreatorDialog(frame);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
