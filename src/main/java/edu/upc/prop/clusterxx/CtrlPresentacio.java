package edu.upc.prop.clusterxx;

import edu.upc.prop.clusterxx.views.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Vector;

public class CtrlPresentacio {
    static CtrlDomini cd;

    static {
        cd = new CtrlDomini();
    }

    // CRIDA A VISTES

    public static void CreateFrequency() {
        CreateFrequency cf = new CreateFrequency();
    }

    public static void DirectorySelector() {
        DirectorySelector ds = new DirectorySelector();
    }

    public static void ErrorView() {
        ErrorView ev = new ErrorView("Error");
    }

    public static void LiveEditor() {
        LiveEditor le = new LiveEditor(new CreateFrequency());
    }

    public static void MainView() {
        MainView mv = new MainView();
    }

    public static void ManageFrequency() {
        ManageFrequency mf = new ManageFrequency();
    }

    // ALFABETS

    public static void Afegir_Alfabet(String nomA, HashSet<Character> h) {
        cd.Afegir_Alfabet(nomA, h);
        // String s = e.Ja_Existeix(nomA);
    }

    public static void Esborrar_Alfabet(String nomA) {
        cd.Esborrar_Alfabet(nomA);
    }

    public static void CanviarNom_Alfabet(String nomA, String nomAnou) {
        try {
            cd.CanviarNom_Alfabet(nomA, nomAnou);
        } catch (ExisteixID_Exception e) {
            String s = e.Ja_Existeix(nomA);
        }
    }

    public static Vector<Vector<String>> llistar_Alfabets() {
        return cd.Consultar_Alfabets();
    }

    public static Vector<String> getAlphabets() {
        return cd.Noms_Alfabets();
    }

    // GRIDS

    public static void Afegir_Grid(int ID, boolean[][] b) {
        cd.Afegir_Grid(ID, b);
        // String s = e.Ja_Existeix(String.valueOf(ID));
        // cridar a la vista d'errors amb el missatge s //en la vista de errores ya
        // el arnau hace lo que sea
    }

    public static void Esborrar_Grid(int ID) {
        cd.Esborrar_Grid(ID);
    }

    public static Vector<Vector<String>> llistar_Grids() {
        return cd.Consultar_Grids();// 1a pos ID 2a pos el grid
    }

    // FREQUENCIES

    public static void AfegirTextFreqFromPath(String nomF, String nomA, String path) {
        try {
            cd.Afegir_Freq_FromPath(nomF, path, nomA, 1);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s;
            s = c1.getMessage() + nomA;
            // llamar a errorview
        } catch (IOException io) {
            String s = io.getMessage() + "no existeix el path" + path;
        }
    }

    public static void AfegirListFreqFromPath(String nomF, String nomA, String path) {
        try {
            cd.Afegir_Freq_FromPath(nomF, path, nomA, 0);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s;
            s = c1.getMessage() + nomA;
        } catch (IOException io) {
            String s = io.getMessage() + "no existeix el path" + path;
        }
    }

    public static void AfegirTextFreqMa(String nomA, String nomF, Vector<String> text) {
        try {
            cd.Afegir_FreqMa(nomA, nomF, text, 1);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s = c1.getMessage() + nomA;
        }
    }

    public static void AfegirListFreqMa(String nomA, String nomF, Vector<String> text) {
        try {
            cd.Afegir_FreqMa(nomA, nomF, text, 0);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s = c1.getMessage() + nomA;
        }
    }

    public static void Esborrar_Freq(String nomf) {
        cd.Esborrar_Frequencia(nomf);
    }

    public static void ModificarTextFreqFromPath(String nomF, String nomA, String path) {
        try {
            cd.Modificar_Freq_Path(nomF, path, 1);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s;
            s = c1.getMessage() + nomA;
        } catch (IOException io) {
            String s = io.getMessage() + "no existeix el path" + path;
        }
    }

    public static void ModificarListFreqFromPath(String nomF, String path) {
        try {
            cd.Modificar_Freq_Path(nomF, path, 0);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s;
            s = c1.getMessage();
        } catch (IOException io) {
            String s = io.getMessage() + "no existeix el path" + path;
        }
    }

    public static void ModificarTextFreqMa(String nomF, Vector<String> text) {
        try {
            cd.Modificar_FreqMa(nomF, text, 1);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s;
            s = c1.getMessage();
        }
    }

    public static void ModificarListFreqMa(String nomF, Vector<String> text) {
        try {
            cd.Modificar_FreqMa(nomF, text, 0);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s;
            s = c1.getMessage();
        }
    }

    public static Vector<Vector<String>> Llistar_Freqs_from_Alph(String nomA) {
        return cd.Consultar_Freqs(nomA);// ademas de los nombres de las freqs(1a pos de cada vector dentro del vector)te
        // dice los pesos de freqs de los pares de letras creo.
    }

    public static Vector<String> NomsFreqs_alfabet(String nomA) {
        return cd.NomsFreqs_Alfabet(nomA);
    }

    // TECLATS

    public static void Afegir_Teclat(String nomT, String nomA, String nomF, int idG) {
        cd.Afegir_Teclat(nomT, nomA, nomF, idG); // hay que asegurarse que la freq es del alfabeto porque no se comprueba
    }

    public static void Esborrar_Teclat(String nomT) {
        cd.Esborrar_Teclat(nomT); // hay que asegurarse que la freq es del alfabeto porque no se comprueba
    }

    public static void Canviar_Nom_Teclat(String nomT, String nomTnou) {
        try {
            cd.CanviarNom_Teclat(nomT, nomTnou); // hace falta una excepcion para cuando cambias el nombre que no sea uno
            // que ya existe de otro teclado.
        } catch (ExisteixID_Exception e) {
            String s = e.Ja_Existeix(nomTnou);
        }
    }

    public static void Actualitzar_Teclat(String nomT) {
        cd.Actualitzar_Teclat(nomT);
    }

    public static void Llistar_Teclats() {
        cd.Consultar_Teclats();
    }

    public static Vector<String> Noms_Teclats() {
        return cd.Noms_Teclats();
    }
}
