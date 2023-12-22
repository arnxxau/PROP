package edu.upc.prop.cluster125.presentation;

import edu.upc.prop.cluster125.domain.Pair;
import edu.upc.prop.cluster125.domain.CtrlDomini;
import edu.upc.prop.cluster125.exceptions.*;
import edu.upc.prop.cluster125.presentation.views.MainView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Controlador de presentació.
 * Gestiona la comunicació entre la capa de domini i la interfície gràfica d'usuari.
 */
public class CtrlPresentacio {
    static CtrlDomini cd;

    static {
        cd = new CtrlDomini();
    }


    /**
     * Punt d'entrada principal de l'aplicació.
     * Inicia la interfície gràfica d'usuari mitjançant la vista principal.
     * @param args arguments d'entrada de l'aplicació (no utilitzats).
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }


    // ALFABETS

    /**
     * Afegeix un nou alfabet al sistema.
     * @param nomA El nom de l'alfabet a afegir.
     * @param h Conjunt de caràcters que formen l'alfabet.
     * @throws ExisteixID_Exception si l'alfabet ja existeix.
     */
    public static void Afegir_Alfabet(String nomA, HashSet<Character> h) throws ExisteixID_Exception {
        cd.Afegir_Alfabet(nomA, h);
    }

    /**
     * Obté una representació en forma de llista de parelles per a una graella específica.
     * @param ID Identificador de la graella.
     * @return ArrayList de parelles representant la graella.
     */
    public static ArrayList<Pair> Obtenir_Reprentacio_Grid(int ID) {
        return cd.Obtenir_Reprentacio_Grid(ID);
    }


    /**
     * Retorna les dimensions màximes de la graella especificada.
     * @param ID Identificador de la graella.
     * @return Parell amb les dimensions màximes de la graella.
     */
    public static Pair Max_Grid(int ID) {
        return cd.Max_Grid(ID);
    }

    /**
     * Esborra un alfabet existent del sistema.
     * @param nomA Nom de l'alfabet a esborrar.
     */
    public static void Esborrar_Alfabet(String nomA) {
        cd.Esborrar_Alfabet(nomA);
    }

    /**
     * Canvia el nom d'un alfabet existent.
     * @param nomA Nom actual de l'alfabet.
     * @param nomAnou Nou nom per a l'alfabet.
     * @throws ExisteixID_Exception si el nou nom d'alfabet ja existeix.
     */
    public static void CanviarNom_Alfabet(String nomA, String nomAnou) throws ExisteixID_Exception {
        cd.CanviarNom_Alfabet(nomA, nomAnou);
    }


    /**
     * Obté els noms de tots els alfabets disponibles.
     * @return Vector amb els noms dels alfabets.
     */
    public static Vector<String> getAlphabets() {
        return cd.Noms_Alfabets();
    }

    /**
     * Demana una representació completa d'un alfabet específic.
     * @return Matriu de Strings amb la informació de l'alfabet.
     */
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

    /**
     * Consulta la informació d'un alfabet específic.
     * @param nomA Nom de l'alfabet a consultar.
     * @return Array de Strings amb la informació de l'alfabet.
     */

    public static String[] Consultar_Alfabet(String nomA) {
        return cd.Consultar_Alfabet(nomA);
    }

    /**
     * Consulta les freqüències associades amb un alfabet específic.
     * @param nomA Nom de l'alfabet del qual es volen consultar les freqüències.
     * @return Array de Strings amb les freqüències de l'alfabet.
     */
    public static String[] Consultar_Freq(String nomA) {
        return cd.Consultar_Freq(nomA);
    }

    // GRIDS

    /**
     * Afegeix una nova graella al sistema.
     * @param ID Identificador de la nova graella.
     * @param b Array bidimensional de booleans representant la graella.
     * @throws ExisteixID_Exception si ja existeix una graella amb el mateix identificador.
     */

    public static void Afegir_Grid(int ID, boolean[][] b) throws ExisteixID_Exception {
        cd.Afegir_Grid(ID, b);
    }

    /**
     * Esborra una graella existent.
     * @param ID Identificador de la graella a esborrar.
     */
    public static void Esborrar_Grid(int ID) {
        cd.Esborrar_Grid(ID);
    }

    // FREQUENCIES

    /**
     * Afegeix una freqüència de text des d'un camí de fitxer especificat.
     * @param nomF Nom de la freqüència a afegir.
     * @param nomA Nom de l'alfabet associat a la freqüència.
     * @param path Camí del fitxer que conté el text.
     * @throws CaractersfromFreq_notInAlph_Exception si els caràcters de la freqüència no estan en l'alfabet.
     * @throws IOException si es produeix un error d'entrada/sortida.
     * @throws ExisteixID_Exception si ja existeix una freqüència amb el mateix nom.
     * @throws badExtraction_Exception si la freqüència no es pot extreure correctament.
     */
    public static void AfegirTextFreqFromPath(String nomF, String nomA, String path)
            throws CaractersfromFreq_notInAlph_Exception, IOException, ExisteixID_Exception, badExtraction_Exception {
        cd.Afegir_Freq_FromPath(nomF, path, nomA, 1);
    }

    /**
     * Afegeix una llista de freqüències des d'un camí de fitxer especificat.
     * @param nomF Nom de la freqüència a afegir.
     * @param nomA Nom de l'alfabet associat a la freqüència.
     * @param path Camí del fitxer que conté la llista.
     * @throws CaractersfromFreq_notInAlph_Exception si els caràcters de la freqüència no estan en l'alfabet.
     * @throws IOException si es produeix un error d'entrada/sortida.
     * @throws ExisteixID_Exception si ja existeix una freqüència amb el mateix nom.
     * @throws badExtraction_Exception si la freqüència no es pot extreure correctament.
     */
    public static void AfegirListFreqFromPath(String nomF, String nomA, String path)
            throws CaractersfromFreq_notInAlph_Exception, IOException, ExisteixID_Exception, badExtraction_Exception {
        cd.Afegir_Freq_FromPath(nomF, path, nomA, 0);
    }

    /**
     * Afegeix una freqüència de text manualment.
     * @param nomA Nom de l'alfabet associat a la freqüència.
     * @param nomF Nom de la freqüència a afegir.
     * @param liveText Text en viu introduït per l'usuari.
     * @throws CaractersfromFreq_notInAlph_Exception si els caràcters del text no estan en l'alfabet.
     * @throws ExisteixID_Exception si ja existeix una freqüència amb el mateix nom.
     * @throws badExtraction_Exception si la freqüència no es pot extreure correctament.
     */
    public static void AfegirTextFreqMa(String nomA, String nomF, String liveText)
            throws CaractersfromFreq_notInAlph_Exception, ExisteixID_Exception, badExtraction_Exception {
        Vector<String> text = new Vector<>();
        StringTokenizer tokenizer = new StringTokenizer(liveText, " ");

        while (tokenizer.hasMoreTokens()) {
            text.add(tokenizer.nextToken());
        }

        cd.Afegir_FreqMa(nomA, nomF, text, 1);
    }

    /**
     * Afegeix una llista de freqüències introduïdes manualment.
     * @param nomA Nom de l'alfabet associat a la llista de freqüències.
     * @param nomF Nom de la llista de freqüències a afegir.
     * @param liveText Text en viu introduït per l'usuari, separats per salt de línia per cada freqüència.
     * @throws CaractersfromFreq_notInAlph_Exception si els caràcters de la llista no estan en l'alfabet.
     * @throws ExisteixID_Exception si ja existeix una llista de freqüències amb el mateix nom.
     * @throws badExtraction_Exception si la llista de freqüències no es pot extreure correctament.
     */
    public static void AfegirListFreqMa(String nomA, String nomF, String liveText)
            throws CaractersfromFreq_notInAlph_Exception, ExisteixID_Exception, badExtraction_Exception {
        Vector<String> text = new Vector<>();
        StringTokenizer tokenizer = new StringTokenizer(liveText, "\n");

        while (tokenizer.hasMoreTokens()) {
            text.add(tokenizer.nextToken());
        }

        cd.Afegir_FreqMa(nomA, nomF, text, 0);
    }

    /**
     * Esborra una freqüència existent.
     * @param nomf Nom de la freqüència a esborrar.
     */
    public static void Esborrar_Freq(String nomf) {
        cd.Esborrar_Frequencia(nomf);
    }


    /**
     * Modifica una freqüència de text existent a partir d'un camí de fitxer.
     * @param nomF Nom de la freqüència a modificar.
     * @param path Camí del fitxer que conté el text nou.
     * @throws CaractersfromFreq_notInAlph_Exception si els caràcters del text no estan en l'alfabet.
     * @throws IOException si es produeix un error d'entrada/sortida.
     * @throws badExtraction_Exception si la freqüència no es pot extreure correctament.
     */
    public static void ModificarTextFreqFromPath(String nomF, String path)
            throws CaractersfromFreq_notInAlph_Exception, IOException, badExtraction_Exception {
        cd.Modificar_Freq_Path(nomF, path, 1);
    }

    /**
     * Modifica una llista de freqüències existent a partir d'un camí de fitxer.
     * @param nomF Nom de la llista de freqüències a modificar.
     * @param path Camí del fitxer que conté la nova llista.
     * @throws CaractersfromFreq_notInAlph_Exception si els caràcters de la llista no estan en l'alfabet.
     * @throws IOException si es produeix un error d'entrada/sortida.
     * @throws badExtraction_Exception si la llista de freqüències no es pot extreure correctament.
     */
    public static void ModificarListFreqFromPath(String nomF, String path)
            throws CaractersfromFreq_notInAlph_Exception, IOException, badExtraction_Exception {

        cd.Modificar_Freq_Path(nomF, path, 0);
    }

    /**
     * Modifica una freqüència de text existent manualment.
     * @param nomF Nom de la freqüència a modificar.
     * @param liveText Nou text en viu per reemplaçar l'anterior.
     * @throws CaractersfromFreq_notInAlph_Exception si els caràcters del text no estan en l'alfabet.
     * @throws badExtraction_Exception si la freqüència no es pot extreure correctament.
     */
    public static void ModificarTextFreqMa(String nomF, String liveText)
            throws CaractersfromFreq_notInAlph_Exception, badExtraction_Exception {
        Vector<String> text = new Vector<>();
        StringTokenizer tokenizer = new StringTokenizer(liveText, " ");

        while (tokenizer.hasMoreTokens()) {
            text.add(tokenizer.nextToken());
        }


        cd.Modificar_FreqMa(nomF, text, 1);
    }

    /**
     * Modifica una llista de freqüències existent manualment.
     * @param nomF Nom de la llista de freqüències a modificar.
     * @param liveText Nou text en viu per reemplaçar l'anterior, separats per salt de línia per cada freqüència.
     * @throws CaractersfromFreq_notInAlph_Exception si els caràcters de la llista no estan en l'alfabet.
     * @throws badExtraction_Exception si la llista de freqüències no es pot extreure correctament.
     */
    public static void ModificarListFreqMa(String nomF, String liveText)
            throws CaractersfromFreq_notInAlph_Exception, badExtraction_Exception {
        Vector<String> text = new Vector<>();
        StringTokenizer tokenizer = new StringTokenizer(liveText, " ");

        while (tokenizer.hasMoreTokens()) {
            text.add(tokenizer.nextToken());
        }


        cd.Modificar_FreqMa(nomF, text, 0);
    }

    /**
     * Obté una llista amb els noms de totes les freqüències associades a un determinat alfabet.
     * @param nomA Nom de l'alfabet.
     * @return Vector amb els noms de les freqüències.
     */
    public static Vector<String> NomsFreqs_alfabet(String nomA) {
        return cd.NomsFreqs_Alfabet(nomA);
    }

    /**
     * Obté una llista amb els noms de totes les freqüències disponibles en el sistema.
     * @return Vector amb els noms de totes les freqüències.
     */
    public static Vector<String> Noms_Freqs() {
        return cd.Noms_Freq();
    }

    /**
     * Obté una llista amb els noms de tots els alfabets disponibles en el sistema.
     * @return Vector amb els noms dels alfabets.
     */
    public static Vector<String> Noms_Alfabet() {
        return cd.Noms_Alfabets();
    }

    /**
     * Obté una llista amb els noms de totes les graelles disponibles en el sistema.
     * @return Vector amb els noms de les graelles.
     */
    public static Vector<String> Noms_Grid() {
        return cd.Noms_Grids();
    }


    /**
     * Fusiona diverses freqüències en una sola freqüència.
     * @param vs Llista dels noms de les freqüències a fusionar.
     * @return Nom de la freqüència resultant de la fusió.
     * @throws alphNotCompatible_Exception si les freqüències no són compatibles entre elles.
     */
    public static String FusionarFreqa(ArrayList<String> vs) throws alphNotCompatible_Exception {
        return cd.fusionarFreqs(vs);
    }

    // TECLATS

    /**
     * Afegeix un nou teclat al sistema basant-se en els paràmetres proporcionats.
     * @param nomT Nom del teclat a afegir.
     * @param nomA Nom de l'alfabet associat.
     * @param nomF Nom de la freqüència associada.
     * @param idG Identificador de la graella associada.
     * @param mode Mode de generació del teclat.
     * @throws ExisteixID_Exception si ja existeix un teclat amb el mateix nom.
     * @throws gridAndAlphabetNotSameSize_Exception si la mida de la graella i l'alfabet no coincideixen.
     */
    public static void Afegir_Teclat(String nomT, String nomA, String nomF, int idG, int mode)
            throws ExisteixID_Exception, gridAndAlphabetNotSameSize_Exception {
        cd.Afegir_Teclat(nomT, nomA, nomF, idG, mode);
    }

    /**
     * Esborra un teclat existent en el sistema.
     * @param nomT Nom del teclat a esborrar.
     */
    public static void Esborrar_Teclat(String nomT) {
        cd.Esborrar_Teclat(nomT);
    }

    /**
     * Canvia el nom d'un teclat existent.
     * @param nomT Nom actual del teclat.
     * @param nomTnou Nou nom per al teclat.
     * @throws ExisteixID_Exception si ja existeix un teclat amb el nou nom.
     */
    public static void Canviar_Nom_Teclat(String nomT, String nomTnou) throws ExisteixID_Exception {
        cd.CanviarNom_Teclat(nomT, nomTnou);
    }

    /**
     * Actualitza el teclat especificat per reflectir qualsevol canvi en les seves dependències.
     * @param nomT Nom del teclat a actualitzar.
     */
    public static void Actualitzar_Teclat(String nomT) {
        cd.Actualitzar_Teclat(nomT);
    }

    /**
     * Obté una llista amb els noms de tots els teclats disponibles en el sistema.
     * @return Vector amb els noms dels teclats.
     */
    public static Vector<String> Noms_Teclats() {
        return cd.Noms_Teclats();
    }

    /**
     * Obté informació general sobre l'estat actual del sistema, com ara el nombre d'elements en cada categoria.
     * @return Array de Strings amb la informació del sistema.
     */
    public static String[] Obtenir_Informacio() {
        return cd.Obtenir_Informacio();
    }

    /**
     * Guarda les dades actuals del sistema a una ubicació persistent.
     */
    public static void Guardar_Dades() {
        cd.Guardar_Dades();
    }

    /**
     * Carrega les dades del sistema des d'una ubicació persistent.
     */
    public static void Carregar_Dades() {
        cd.Carregar_Dades();
    }

    /**
     * Consulta la informació d'una graella específica.
     * @param ID Identificador de la graella a consultar.
     * @return Array de Strings amb la informació de la graella.
     */
    public static String[] Consultar_Grid(Integer ID){
        return cd.Consultar_Grid(ID);
    }

    /**
     * Consulta la informació d'un teclat específic.
     * @param nomT Nom del teclat a consultar.
     * @return Array de Strings amb la informació del teclat.
     */
    public static String[] Consultar_Teclat(String nomT){
        return cd.Consultar_Teclat(nomT);
    }

    /**
     * Obté la distribució de caràcters d'un teclat específic.
     * @param nomT Nom del teclat del qual s'obtindrà la distribució.
     * @return Array de caràcters representant la distribució del teclat.
     */
    public static char[] Obtenir_Distribucio_Teclat(String nomT) {
        return cd.Obtenir_Distribucio_Teclat(nomT);
    }

    /**
     * Obté l'identificador de la graella associada a un teclat específic.
     * @param nomT Nom del teclat.
     * @return Identificador de la graella associada.
     */
    public static int Obtenir_Nom_Grid_Teclat(String nomT) {
        return cd.Obtenir_Nom_Grid_Teclat(nomT);
    }

}
