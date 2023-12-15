package edu.upc.prop.clusterxx;

import edu.upc.prop.clusterxx.exceptions.CaractersfromFreq_notInAlph_Exception;
import edu.upc.prop.clusterxx.exceptions.ExisteixID_Exception;
import edu.upc.prop.clusterxx.views.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Vector;

public class CtrlPresentacio {
    static CtrlDomini cd;

    static {
        cd = new CtrlDomini();
    }

    // CRIDA A VISTES

    //public static void CreateFrequency() {
    //    CreateFrequency cf = new CreateFrequency();
    //}

    public static void DirectorySelector() {
        DirectorySelectorDialog ds = new DirectorySelectorDialog();
    }

    //public static void LiveEditor() {
    //    LiveEditor le = new LiveEditor(new CreateFrequency());
   // }

    public static void MainView() {
        MainView mv = new MainView();
    }

    //public static void ManageFrequency() {
    //    ManageFrequency mf = new ManageFrequency();
    //}

    // ALFABETS

    public static void Afegir_Alfabet(String nomA, HashSet<Character> h) {
       try{
           cd.Afegir_Alfabet(nomA, h);
       }catch (ExisteixID_Exception e){
           // String s = e.Ja_Existeix(nomA);
       }
    }

    public static ArrayList<Pair> Obtenir_Reprentacio_Grid(int ID) {
        return cd.Obtenir_Reprentacio_Grid(ID);
    }

    public static Pair Max_Grid(int ID) {
        return cd.Max_Grid(ID);
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

    public static Vector<String> getGrid() {
        return cd.Noms_Grids();
    }

    public static String[][] Demanar_full_Alfabet() {
        Vector<Vector<String>> inp = cd.Consultar_Alfabets();
        String[][] res = new String[inp.size()][3];
        for(int i=0; i<inp.size(); i++) {
            String[] a = new String[3];
            a[0] = inp.get(i).get(0);

            StringBuilder resultant = new StringBuilder();

            String entrant = inp.get(i).get(1);

            for (int l = 0; l < entrant.length(); l++) {
                resultant.append(entrant.charAt(l)).append(" ");
            }

            // Eliminar el espacio adicional al final, si es necesario
            if (resultant.length() > 0) {
                resultant.deleteCharAt(resultant.length() - 1);
            }

            a[1] = resultant.toString();
            a[2] = String.valueOf(entrant.length());

            res[i] = a;
        }
        return res;
    }

    public static String[] Consultar_Alfabet(String nomA) {
        return cd.Consultar_Alfabet(nomA);
    }

    public static String[] Consultar_Freq(String nomA) {
        return cd.Consultar_Freq(nomA);
    }

    // GRIDS

    public static void Afegir_Grid(int ID, boolean[][] b) {
        try{
            cd.Afegir_Grid(ID, b);
        }catch (ExisteixID_Exception e){
            String s = e.Ja_Existeix(String.valueOf(ID));
        }
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
        }catch (ExisteixID_Exception e){
            String s = e.Ja_Existeix(nomF);
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
        }catch (ExisteixID_Exception e){
            String s = e.Ja_Existeix(nomF);
        }
    }

    public static void AfegirTextFreqMa(String nomA, String nomF, String liveText) {
        try {
            Vector<String> text = new Vector<>();
            StringTokenizer tokenizer = new StringTokenizer(liveText, " ");

            while (tokenizer.hasMoreTokens()) {
                text.add(tokenizer.nextToken());
            }

            cd.Afegir_FreqMa(nomA, nomF, text, 1);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s = c1.getMessage() + nomA;
        }catch (ExisteixID_Exception e){
            String s = e.Ja_Existeix(nomF);
        }
    }

    public static void AfegirListFreqMa(String nomA, String nomF, Vector<String> text) {
        try {
            cd.Afegir_FreqMa(nomA, nomF, text, 0);
        } catch (CaractersfromFreq_notInAlph_Exception c1) {
            String s = c1.getMessage() + nomA;
        }catch (ExisteixID_Exception e){
            String s = e.Ja_Existeix(nomF);
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

    public static Vector<String> Noms_Freqs() {
        return cd.Noms_Freq();
    }

    public static Vector<String> Noms_Alfabet() {
        return cd.Noms_Alfabets();
    }

    public static Vector<String> Noms_Grid() {
        return cd.Noms_Grids();
    }


    public static String[][] Dades_Freqs(){
        return cd.datosFreqs();
    }

    public static String FusionarFreqa(ArrayList<String> vs) {
        return cd.fusionarFreqs(vs);
    }

    public static String[][] Demanar_full_Freq(){
        return cd.Consultar_Freq();
    }

    // TECLATS

    public static void Afegir_Teclat(String nomT, String nomA, String nomF, int idG) {
        try{
            cd.Afegir_Teclat(nomT, nomA, nomF, idG, 0);
        }catch (ExisteixID_Exception e){
           String s = e.Ja_Existeix(nomT);
        }
         // hay que asegurarse que la freq es del alfabeto porque no se comprueba
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

    public static  String[] Obtenir_Informacio() {
        return cd.Obtenir_Informacio();
    }

    public static  void Guardar_Dades() {
         cd.Guardar_Dades();
    }

    public static  void Carregar_Dades() {
        cd.Carregar_Dades();
    }


    public static String[] Consultar_Grid(Integer ID){
        return cd.Consultar_Grid(ID);
    }

    public static String[] Consultar_Teclat(String nomT){
        return cd.Consultar_Teclat(nomT);
    }
}
