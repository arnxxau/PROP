package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    FrequencyManagerPanel mf;
    AlphabetManagerPanel ma;
    KeyboardManagerPanel mk;
    GridManagerPanel mg;
    InformationPanel mm;

    public MainView() {
        // Set to the system look and feel
        try {
            //
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
        JPanel bottomPanel = createBottomPanel();

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("keyboards", null, mk, "manage your keyboards here");
        tp.addTab("grids", null, mg, "manage your grids here");
        tp.addTab("alphabets", null, ma, "manage your alphabets here");
        tp.addTab("frequencies", null, mf, "manage your frequencies here");
        tp.addTab("info", null, mm, "load and save your program and get info");
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
        //mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
        //setVisible(true);


        setLocationRelativeTo(null);

    }

    // Method to update the selected tab
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


    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        // Label Panel
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(new JLabel("Current loaded objects: "));
        bottomPanel.add(labelPanel, BorderLayout.WEST);
        bottomPanel.add(labelPanel, BorderLayout.WEST);

        // Save and Load Panel
        JButton saveButton = createLoadSaveButton("Save");
        JButton loadButton = createLoadSaveButton("Load");

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
}
