package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;
import edu.upc.prop.cluster125.exceptions.CaractersfromFreq_notInAlph_Exception;
import edu.upc.prop.cluster125.exceptions.badExtraction_Exception;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FrequencyManagerPanel extends JPanel {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> list = new JList<>(listModel);

    public FrequencyManagerPanel(JFrame parent) {
        setLayout(new BorderLayout());
        updateTab();

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel saveLoadPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        Dimension buttonSize = new Dimension(100, 30);
        JButton createButton = new JButton("Create");
        JButton modifyButton = new JButton("Modify");
        JButton propertiesButton = new JButton("Properties");
        JButton deleteButton = new JButton("Delete");

        setButtonSize(createButton, buttonSize);
        setButtonSize(modifyButton, buttonSize);
        setButtonSize(deleteButton, buttonSize);
        setButtonSize(propertiesButton, buttonSize);

        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(modifyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(propertiesButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        saveLoadPanel.setLayout(new BoxLayout(saveLoadPanel, BoxLayout.Y_AXIS));
        saveLoadPanel.add(Box.createVerticalGlue());
        saveLoadPanel.add(buttonPanel);
        saveLoadPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        mainPanel.add(saveLoadPanel, BorderLayout.EAST);
        add(mainPanel);

        // ActionListeners
        modifyButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select a frequency!");
            } else {
                String[] options = {"Live text", "Live freq", "File text", "File freq"};

                int choice = JOptionPane.showOptionDialog(
                        parent,
                        "Select an extraction type:",
                        "Frequency updater",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);

                try {
                    String selectedFreq = list.getSelectedValue(); // Get the selected frequency name
                    switch (choice) {
                        case 0: // Live text logic
                            LiveEditorDialog ledLiveText = new LiveEditorDialog(parent, "");
                            String contentLiveText = ledLiveText.showDialogAndGetContent();
                            CtrlPresentacio.ModificarTextFreqMa(selectedFreq, contentLiveText);
                            break;

                        case 1: // Live freq logic
                            LiveEditorDialog ledLiveFreq = new LiveEditorDialog(parent, "");
                            String contentLiveFreq = ledLiveFreq.showDialogAndGetContent();
                            CtrlPresentacio.ModificarListFreqMa(selectedFreq, contentLiveFreq);
                            break;

                        case 2: // File text logic
                            FileSelectorDialog dsFileText = new FileSelectorDialog();
                            String urlFileText = dsFileText.selectDirectory();
                            CtrlPresentacio.ModificarTextFreqFromPath(selectedFreq, urlFileText);
                            break;

                        case 3: // File freq logic
                            FileSelectorDialog dsFileFreq = new FileSelectorDialog();
                            String urlFileFreq = dsFileFreq.selectDirectory();
                            CtrlPresentacio.ModificarListFreqFromPath(selectedFreq, urlFileFreq);
                            break;

                        default: // Dialog closed or canceled
                            JOptionPane.showMessageDialog(null, "Dialog closed or canceled");
                            break;
                    }
                } catch (CaractersfromFreq_notInAlph_Exception ex) {
                    JOptionPane.showMessageDialog(parent, "The frequency contains incompatible characters with the selected alphabet", "Character error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(parent, "There was an error while extracting the file", "File error", JOptionPane.ERROR_MESSAGE);
                } catch (badExtraction_Exception ex) {
                    JOptionPane.showMessageDialog(parent, "There was an error while trying to extract the frequency", "Bad extraction", JOptionPane.ERROR_MESSAGE);
                }

                updateTab();
            }
        });

        createButton.addActionListener(e -> {
            new FrequencyCreatorDialog(parent);
            updateTab();
        });

        deleteButton.addActionListener(e -> {

            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select an alphabet!");
            } else {
                int confirmLoad = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this component?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmLoad == JOptionPane.YES_OPTION) {
                    CtrlPresentacio.Esborrar_Freq(list.getSelectedValue());
                    updateTab();
                }
            }

        });        propertiesButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select a frequency!");
            } else {
                FrequencyPropertiesDialog fid = new FrequencyPropertiesDialog(parent, CtrlPresentacio.Consultar_Freq(list.getSelectedValue()));
                fid.setVisible(true);
            }
        });
    }

    private void setButtonSize(JButton button, Dimension size) {
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }

    public void updateTab() {
        listModel.clear();
        for (String freq : CtrlPresentacio.Noms_Freqs()) {
            listModel.addElement(freq);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Frequency Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(new FrequencyManagerPanel(frame));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
