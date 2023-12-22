package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Aquesta classe representa la vista principal de l'aplicació que conté les pestanyes per gestionar teclats, grafs, alfabets, freqüències i informació.
 */
public class MainView extends JFrame {

    FrequencyManagerPanel mf;
    AlphabetManagerPanel ma;
    KeyboardManagerPanel mk;
    GridManagerPanel mg;
    InformationPanel mm;

    /**
     * Crea una nova instància de la vista principal.
     */
    public MainView() {
        // Set to the system look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception e) {
            System.out.println(e);
            // If system look and feel is not available, fall back to the default look and feel.
        }

        mf = new FrequencyManagerPanel(this);
        ma = new AlphabetManagerPanel(this);
        mk = new KeyboardManagerPanel(this);
        mg = new GridManagerPanel(this);
        mm = new InformationPanel();



        ImageIcon imgicon = new ImageIcon("./src/main/java/edu/upc/prop/cluster125/presentation/views/logo.png");
        setIconImage(imgicon.getImage());
        setName("Layout manager");






        // Use modern fonts
        setFont(new Font("Arial", Font.PLAIN, 14));


        // Set up the main frame
        setTitle("Layout manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("keyboards", null, mk, "manage your keyboards here");
        tp.addTab("grids", null, mg, "manage your grids here");
        tp.addTab("alphabets", null, ma,  "manage your alphabets here");
        tp.addTab("frequencies", null, mf,  "manage your frequencies here");
        tp.addTab("information", null, mm,  "load and save your program and get info");
        topPanel.add(tp);

        tp.setSelectedIndex(4);

        tp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                updateTab(selectedIndex);
            }
        });

        // Add panels to the main panel
        mainPanel.add(tp, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);

        setLocationRelativeTo(null);
    }

    /**
     * Mètode per actualitzar la pestanya seleccionada.
     *
     * @param tabIndex L'índex de la pestanya seleccionada.
     */
    private void updateTab(int tabIndex) {
        switch (tabIndex) {
            case 0: // keyboard
                mk.updateTab();
                break;
            case 1: // grids
                mg.updateTab();
                break;
            case 2: // alphabet
                ma.updateTab();
                break;
            case 3: // frequency
                mf.updateTab();
                break;
            case 4:
                mm.refreshLabels();
                break;
            default:
                break;
        }
    }

}
