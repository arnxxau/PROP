package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        // Estableix l'aparença del sistema
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception e) {
            System.out.println(e);
            // Si l'aparença del sistema no està disponible, utilitza l'aparença per defecte.
        }

        // Inicialitza les pestanyes de la vista
        mf = new FrequencyManagerPanel(this);
        ma = new AlphabetManagerPanel(this);
        mk = new KeyboardManagerPanel(this);
        mg = new GridManagerPanel(this);
        mm = new InformationPanel();

        // Icona de l'aplicació
        ImageIcon imgicon = new ImageIcon("/home/akira/IdeaProjects/subgrup-prop12.5/src/main/java/edu/upc/prop/clusterxx/views/logo.png");
        setIconImage(imgicon.getImage());
        setName("Distributor");

        // Utilitza fonts modernes
        setFont(new Font("Arial", Font.PLAIN, 14));

        // Configura el marc principal
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // Crea panells per a una millor organització
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = createBottomPanel();

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("teclats", null, mk, "gestiona els teclats aquí");
        tp.addTab("grafs", null, mg, "gestiona els grafs aquí");
        tp.addTab("alfabets", null, ma, "gestiona els alfabets aquí");
        tp.addTab("freqüències", null, mf, "gestiona les freqüències aquí");
        tp.addTab("informació", null, mm, "carrega i desa el programa i obté informació");
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

        // Afegeix els panells al panell principal
        mainPanel.add(tp, BorderLayout.CENTER);

        // Afegeix el panell principal al marc
        add(mainPanel);

        // Fes visible el marc
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
            case 0: // teclats
                mk.updateTab();
                break;
            case 1: // grafs
                mg.updateTab();
                break;
            case 2: // alfabets
                ma.updateTab();
                break;
            case 3: // freqüències
                mf.updateTab();
                break;
            case 4:
                mm.refreshLabels();
                break;
            default:
                break;
        }
    }

    /**
     * Crea un panell inferior amb etiquetes i botons.
     *
     * @return El panell inferior creat.
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        // Panell d'etiquetes
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(new JLabel("Objectes actuals carregats: "));
        bottomPanel.add(labelPanel, BorderLayout.WEST);
        bottomPanel.add(labelPanel, BorderLayout.WEST);

        // Panell de botons de desar i carregar
        JButton saveButton = createLoadSaveButton("Desar");
        JButton loadButton = createLoadSaveButton("Carregar");

        saveButton.addActionListener(new SaveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());

        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveLoadPanel.add(saveButton);
        saveLoadPanel.add(loadButton);
        bottomPanel.add(saveLoadPanel, BorderLayout.EAST);

        return bottomPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }

    private JButton createLoadSaveButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(70, 30));
        return button;
    }

    // Listeners

    private class ManageFrequencyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Botó Gestionar Frequència Clicat");
            //ManageFrequency mf = new ManageFrequency();
            //setVisible(false);
        }
    }

    private class ManageAlphabetsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Botó Gestionar Alfabets Clicat");
            ManageAlphabets ma = new ManageAlphabets();
            setVisible(false);

            // Afegiu accions específiques per al botó Gestionar Alfabets
        }
    }

    private class ManageKeyboardsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Botó Gestionar Teclats Clicat");
            // Afegiu accions específiques per al botó Gestionar Teclats
        }
    }

    private class ManageGridsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Botó Gestionar Grafs Clicat");
            // Afegiu accions específiques per al botó Gestionar Grafs
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Botó Desar Clicat");
            // Afegiu accions específiques per al botó Desar
        }
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Botó Carregar Clicat");
            FileSelectorDialog ds = new FileSelectorDialog();
            ds.selectDirectory();
        }
    }
}
