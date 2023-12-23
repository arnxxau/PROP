package edu.upc.prop.cluster125.domain;

import edu.upc.prop.cluster125.exceptions.*;
import edu.upc.prop.cluster125.persistance.CtrlPersistencia;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Aquesta classe representa el controlador del domini del sistema.
 */
public class CtrlDomini {

    HashMap<String, Alphabet> AP = new HashMap<>();
    HashMap<String, Frequency> FQ = new HashMap<>();
    HashMap<String, Keyboard> KB = new HashMap<>();
    HashMap<Integer, Grid> GD = new HashMap<>();
    final CtrlPersistencia persistencia = new CtrlPersistencia();

    Instant lastSaved = null;

    /**
     * Constructor per a la classe CtrlDomini.
     * Crea una nova instància de CtrlDomini.
     */
    public CtrlDomini(){}

    /**
     * Afegeix un teclat al sistema amb les dades especificades.
     *
     * @param nomT Nom del teclat a afegir.
     * @param nomA Nom de l'alfabet associat al teclat.
     * @param nomF Nom de la freqüència associada al teclat.
     * @param idG  Identificador de la graella associada al teclat.
     * @param mode Mode del teclat.
     * @throws ExisteixID_Exception            Si ja existeix un teclat amb el mateix nom.
     * @throws gridAndAlphabetNotSameSize_Exception Si la mida de la graella i l'alfabet no coincideixen.
     */
    public void Afegir_Teclat(String nomT, String nomA, String nomF, int idG, int mode) throws ExisteixID_Exception, gridAndAlphabetNotSameSize_Exception {
        if (KB.containsKey(nomT)) throw new ExisteixID_Exception();

        Alphabet a = AP.get(nomA);
        Frequency f = a.getFrequencies().get(nomF);
        Grid g = GD.get(idG);

        if (g.getSize() != a.size()) throw new gridAndAlphabetNotSameSize_Exception();

        Keyboard k = new Keyboard(nomT,a,f,g,mode);
        KB.put(nomT,k);
    }

    /**
     * Fusiona les freqüències especificades i retorna el nom de la freqüència fusionada.
     *
     * @param arrayF Un ArrayList de noms de freqüències a fusionar.
     * @return El nom de la freqüència fusionada.
     * @throws alphNotCompatible_Exception Si les freqüències no són compatibles per a la fusió.
     */
    public String fusionarFreqs(ArrayList<String> arrayF) throws alphNotCompatible_Exception {
        if (arrayF.isEmpty()) return null;
        Frequency f = FQ.get(arrayF.get(0));

        f = new Frequency(f);

        if (arrayF.size() > 1) {
            int i = 0;
            String name = "fusion (";
            for (String s : arrayF) {
                if (i > 0) {
                        f.fusion(FQ.get(s));
                        name += " + " + s;
                } else {
                    name += s;
                }
                ++i;
            }
            name += ")";
            f.setName(name);
            FQ.put(f.getName(), f);
            (f.getAlphabet()).addFrequency(f);
        }
        return f.getName();
    }


    /**
     * Esborra un teclat del sistema.
     *
     * @param nomT Nom del teclat a esborrar.
     */
    public void Esborrar_Teclat(String nomT) {
        KB.remove(nomT);
    }


    /**
     * Canvia el nom d'un teclat existent.
     *
     * @param nomT    Nom actual del teclat.
     * @param newNom  Nou nom per al teclat.
     * @throws ExisteixID_Exception Si ja existeix un teclat amb el nou nom.
     */
    public void CanviarNom_Teclat(String nomT, String newNom)throws ExisteixID_Exception {
        if (KB.containsKey(newNom)){
            throw new ExisteixID_Exception();
        }
        Keyboard k = KB.get(nomT);
        k.setNom(newNom);
        KB.remove(nomT);
        KB.put(newNom,k);
    }

    /**
     * Actualitza les dades d'un teclat específic en el sistema.
     *
     * @param nomT Nom del teclat que es vol actualitzar.
     */
    public void Actualitzar_Teclat(String nomT) {
        Keyboard k = KB.get(nomT);
        k.update();
    }

    /**
     * Retorna un vector que conté els noms de tots els teclats disponibles al sistema.
     *
     * @return Un vector amb els noms dels teclats.
     */
    public Vector<String> Noms_Teclats(){
       return new Vector<>(KB.keySet());
    }

    /**
     * Afegeix un alfabet amb el nom i els caràcters especificats.
     *
     * @param s Nom de l'alfabet a afegir.
     * @param h Conjunt de caràcters que formen l'alfabet.
     * @throws ExisteixID_Exception Si ja existeix un alfabet amb el mateix nom.
     */
    public void Afegir_Alfabet(String s, HashSet<Character> h)throws ExisteixID_Exception{
        //h.add(' ');
        if(AP.containsKey(s))throw new ExisteixID_Exception();
        Alphabet a = new Alphabet(s,h);
        AP.put(s,a);
    }


    /**
     * Afegeix una freqüència al sistema a partir del contingut d'un fitxer indicat pel camí especificat.
     *
     * @param nomF Nom de la freqüència a afegir.
     * @param path Camí del fitxer del qual llegir les dades per a la freqüència.
     * @param nomA Nom de l'alfabet associat a la freqüència.
     * @param mode Mode de la freqüència.
     * @throws CaractersfromFreq_notInAlph_Exception Si els caràcters de la freqüència no es troben a l'alfabet associat.
     * @throws IOException Si hi ha un error en llegir el fitxer.
     * @throws ExisteixID_Exception Si ja existeix una freqüència amb el mateix nom.
     * @throws badExtraction_Exception Si hi ha un error en extreure les dades del fitxer.
     */
    public void Afegir_Freq_FromPath(String nomF, String path, String nomA, int mode) throws CaractersfromFreq_notInAlph_Exception, IOException, ExisteixID_Exception, badExtraction_Exception { //PARA PASAR DE PATH DE FICHERO A STRING[] PARA LA CONSTRUCTORA DE FREQ PARA CREARLA

            if(FQ.containsKey(nomF)) throw new ExisteixID_Exception();//return 1;ja existeix la freq

           // if(!AP.containsKey(nomA))return 2; //l'alfabet no existeix*/

            Alphabet a = AP.get(nomA);

            String[] text = llegir_archiu_path(path);//pasa del texto del fichero path a string[]

            /*if (text==null)return 4; // no existeix o no es troba el fitxer*/

            Frequency f;
            f = new Frequency(nomF, text, mode, a);


            f.setAlphabet(a); //si tots els caracters de la freq hi son també al alfabet, li asignem l'alfabet
            a.addFrequency(f);  //a l'alfabet li afegim la freq.
            FQ.put(nomF,f); //afegim la frequencia
    }

    /**
     * Llegeix el contingut d'un fitxer indicat pel camí especificat i retorna les dades com un array de cadenes de text.
     *
     * @param path Camí del fitxer que es vol llegir.
     * @return Un array de cadenes de text amb el contingut del fitxer.
     * @throws IOException Si hi ha un error en llegir el fitxer.
     */

    private String[] llegir_archiu_path(String path) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        return lines.toArray(new String[0]);
    }

    /**
     * Afegeix una freqüència al sistema amb les dades especificades manualment.
     *
     * @param nomA Nom de l'alfabet associat a la freqüència.
     * @param nomF Nom de la freqüència a afegir.
     * @param vs Vector amb les dades de la freqüència.
     * @param mode Mode de la freqüència.
     * @throws CaractersfromFreq_notInAlph_Exception Si els caràcters de la freqüència no es troben a l'alfabet associat.
     * @throws ExisteixID_Exception Si ja existeix una freqüència amb el mateix nom.
     * @throws badExtraction_Exception Si hi ha un error en extreure les dades de la freqüència.
     */
    public void Afegir_FreqMa(String nomA, String nomF, Vector<String> vs, int mode) throws CaractersfromFreq_notInAlph_Exception, ExisteixID_Exception, badExtraction_Exception {
        if(FQ.containsKey(nomF)) throw new ExisteixID_Exception();//return 1; la freq ja existeix

        String[] text = new String[vs.size()];
        for(int i=0; i < text.length; i++){
            text[i]=vs.get(i);
        }

        Alphabet a = AP.get(nomA);

        Frequency f;
        f = new Frequency(nomF,text,mode,a);

        FQ.put(nomF,f);
        a.addFrequency(f);//a l'alfabet se li afegeix la freq
    }

    /**
     * Esborra un alfabet del sistema i les freqüències associades a ell si n'hi ha.
     *
     * @param s Nom de l'alfabet a esborrar.
     */
    public void Esborrar_Alfabet(String s){
        if (AP.get(s).getFrequencies() != null) { // safe delete
            for(String nomf : AP.get(s).getFrequencies().keySet()) FQ.remove(nomf);
        }
        AP.remove(s);
    }


    /**
     * Canvia el nom d'un alfabet existent pel nou nom especificat.
     *
     * @param s   Nom de l'alfabet actual.
     * @param s2  Nou nom per a l'alfabet.
     * @throws ExisteixID_Exception Si ja existeix un altre alfabet amb el nou nom.
     */
    public void CanviarNom_Alfabet(String s,String s2)throws ExisteixID_Exception{
        if(AP.containsKey(s2)){
            throw new ExisteixID_Exception();
        }
        Alphabet a = AP.get(s);
        a.setName(s2);
        AP.remove(s);
        AP.put(s2,a);
    }

    /**
     * Consulta tots els alfabets disponibles al sistema i retorna una matriu de vectors amb la seva informació.
     *
     * @return Una matriu de vectors que conté la informació dels alfabets.
     */
    public Vector<Vector<String>> Consultar_Alfabets(){
        Vector<Vector<String>> vvs = new Vector<>();
        for (Alphabet valor : AP.values()) {
            Vector<String> vs = new Vector<>();
            vs.add(valor.getName());
            StringBuilder stringBuilder = new StringBuilder();
            for (Character c : valor.getCharacters()) {
                stringBuilder.append(c);
            }
            String resultat = stringBuilder.toString();
            vs.add(resultat);
            vvs.add(vs);
        }
        return vvs;
    }

    /**
     * Consulta les dades d'un alfabet pel seu nom i retorna un array de cadenes amb la seva informació.
     *
     * @param nomA Nom de l'alfabet que es vol consultar.
     * @return Un array de cadenes amb la informació de l'alfabet.
     */
    public String[] Consultar_Alfabet(String nomA){

        Alphabet a = AP.get(nomA);

        String [] s = new String[5];

        s[0] = a.getName();
        s[1] = "";
        if (a.getFrequencies() != null) {
            for (String f :  a.getFrequencies().keySet()) {
                s[1] += f + " ";
            }
        }

        s[2] = "";
        for (Character f :  a.getCharacters()) {
            s[2] += f + " ";
        }


        s[3] = a.getLastMod();
        s[4] = a.getCrDate();

        return s;
    }

    /**
     * Consulta les dades d'una freqüència pel seu nom i retorna un array de cadenes amb la seva informació.
     *
     * @param nomF Nom de la freqüència que es vol consultar.
     * @return Un array de cadenes amb la informació de la freqüència.
     */
    public String[] Consultar_Freq(String nomF){

        Frequency f = FQ.get(nomF);

        String [] s = new String[5];

        s[0] = f.getName();
        s[1] = f.getAlphabet().getName();

        int mode = f.getMode();
        if (mode == 0) s[2] = "raw";
        else if (mode == 1) s[2] = "text";


        s[3] = f.getLastModifiedTime();
        s[4] = f.getCreationDate();

        return s;
    }

    /**
     * Consulta les dades d'un teclat pel seu nom i retorna un array de cadenes amb la seva informació.
     *
     * @param nomT Nom del teclat que es vol consultar.
     * @return Un array de cadenes amb la informació del teclat.
     */
    public String[] Consultar_Teclat(String nomT){

        Keyboard k = KB.get(nomT);

        String [] s = new String[5];

        s[0] = k.getName();
        s[1] = k.getAlphabet().getName();

        s[2] = k.getFrequency().getName();


        s[3] = k.getLastMod();
        s[4] = k.getCrDate();
        return s;
    }

    /**
     * Consulta les dades d'una graella pel seu identificador i retorna un array de cadenes amb la seva informació.
     *
     * @param ID Identificador de la graella que es vol consultar.
     * @return Un array de cadenes amb la informació de la graella.
     */
    public String[] Consultar_Grid(Integer ID){

        Grid f = GD.get(ID);

        String [] s = new String[5];

        s[0] = String.valueOf(f.getID());
        s[1] = String.valueOf(f.getPositions().size());

        Pair p = f.getMaxSize();

        s[2] = p.getX() + " " + p.getY();

        s[3] = "";
        s[4] = f.getCreationDate();

        return s;
    }



    /**
     * Retorna un vector amb els noms de tots els alfabets disponibles al sistema.
     *
     * @return Un vector amb els noms dels alfabets.
     */
    public Vector<String> Noms_Alfabets(){
        Vector<String> vs = new Vector<>();
        for (String valor : AP.keySet()) {
            vs.add(valor);
        }
        return vs;
    }

    /**
     * Retorna un vector amb els noms de totes les graelles disponibles al sistema.
     *
     * @return Un vector amb els noms de les graelles.
     */
    public Vector<String> Noms_Grids(){
        Vector<String> vs = new Vector<>();
        for (Integer valor : GD.keySet()) {
            vs.add(valor.toString());
        }
        return vs;
    }


    /**
     * Retorna un vector amb els noms de totes les freqüències associades a un alfabet específic.
     *
     * @param nomA Nom de l'alfabet pel qual es volen consultar les freqüències.
     * @return Un vector amb els noms de les freqüències associades a l'alfabet.
     */
    public Vector<String> NomsFreqs_Alfabet(String nomA){
        Vector<String> vs = new Vector<>();
        for(String s : AP.get(nomA).getFrequencies().keySet()){
            vs.add(s);
        }
        return vs;
    }

    /**
     * Retorna un vector amb els noms de totes les freqüències disponibles al sistema.
     *
     * @return Un vector amb els noms de les freqüències.
     */
    public Vector<String> Noms_Freq(){
        Vector<String> vs = new Vector<>();
        for(String s : FQ.keySet()){
            vs.add(s);
        }
        return vs;
    }

    /**
     * Esborra una freqüència i les seves dades associades del sistema.
     *
     * @param nomF Nom de la freqüència que es vol esborrar.
     */
    public void Esborrar_Frequencia (String nomF) {
        Frequency f = FQ.get(nomF);
        f.getAlphabet().deleteFrequency(f);//quito del HashMap del alfabeto la freq.
        FQ.remove(nomF);
    }


    /**
     * Modifica una freqüència existent amb les dades contingudes en un fitxer especificat pel seu camí.
     *
     * @param nomF  Nom de la freqüència a modificar.
     * @param path  Camí del fitxer amb les dades per a la freqüència.
     * @param mode  Mode de la freqüència (0 per raw, 1 per text).
     * @throws CaractersfromFreq_notInAlph_Exception Si els caràcters de la freqüència no estan presents a l'alfabet associat.
     * @throws IOException                         Si hi ha problemes en llegir el fitxer.
     * @throws badExtraction_Exception              Si hi ha problemes en extreure les dades del fitxer.
     */
    public void Modificar_Freq_Path(String nomF, String path, int mode) throws CaractersfromFreq_notInAlph_Exception, IOException, badExtraction_Exception {

        Frequency f = FQ.get(nomF); //no fa falta anar al HashMapde l'alfabet a modificar la freq perquè en teoria es el mateix punter

        String[] text = llegir_archiu_path(path);

        f.modifyFrequency(text,mode);

    }

    /**
     * Modifica una freqüència existent amb les dades contingudes en un vector de cadenes.
     *
     * @param nomF Nom de la freqüència a modificar.
     * @param vs   Vector de cadenes amb les dades per a la freqüència.
     * @param mode Mode de la freqüència (0 per raw, 1 per text).
     * @throws CaractersfromFreq_notInAlph_Exception Si els caràcters de la freqüència no estan presents a l'alfabet associat.
     * @throws badExtraction_Exception              Si hi ha problemes en extreure les dades del vector.
     */
    public void Modificar_FreqMa(String nomF, Vector<String> vs, int mode) throws CaractersfromFreq_notInAlph_Exception, badExtraction_Exception {

        String[] text = new String[vs.size()];

        for(int i=0; i < text.length; i++){
            text[i]=vs.get(i);
        }

        Frequency f = FQ.get(nomF);
        f.modifyFrequency(text,mode);
    }

    /**
     * Afegeix una nova graella amb l'identificador especificat i la matriu de posicions especificada.
     *
     * @param x   Identificador de la graella.
     * @param pos Matriu de posicions de la graella.
     * @throws ExisteixID_Exception Si ja existeix una graella amb el mateix identificador.
     */
    public void Afegir_Grid (int x, boolean[][] pos)throws ExisteixID_Exception{
        if (GD.containsKey(x))throw new ExisteixID_Exception(); //return -1;
        Grid g = new Grid(x,pos);
        GD.put(x,g);
    }

    /**
     * Esborra una graella del sistema amb l'identificador especificat.
     *
     * @param idG Identificador de la graella a esborrar.
     */
    public void Esborrar_Grid (Integer idG) {
        GD.remove(idG);
    }

    /**
     * Obté la representació de les posicions de la graella identificada per l'ID.
     *
     * @param ID Identificador de la graella.
     * @return Llista d'objectes Pair que representa les posicions de la graella.
     */
    public ArrayList<Pair> Obtenir_Reprentacio_Grid(int ID) {
        return GD.get(ID).getPositions();
    }

    /**
     * Obté la mida màxima de la graella identificada per l'ID.
     *
     * @param ID Identificador de la graella.
     * @return Objecte Pair que conté la mida màxima de la graella (x, y).
     */
    public Pair Max_Grid(int ID) {
        return GD.get(ID).getMaxSize();
    }


    /**
     * Guarda les dades actuals del sistema, incloent alfabets, freqüències, graelles i teclats.
     * Actualitza el temps de l'últim guardat.
     */
    public void Guardar_Dades() {
        persistencia.saveAlphabets(AP);
        persistencia.saveFrequencies(FQ);
        persistencia.saveGrids(GD);
        persistencia.saveKeyboards(KB);

        lastSaved = Instant.now();
    }

    /**
     * Carrega les dades desades del sistema, incloent alfabets, freqüències, graelles i teclats.
     * Realitza correccions relacionades amb alfabets, freqüències i teclats carregats.
     */
    public void Carregar_Dades() {
        AP = persistencia.getAlphabets();
        FQ = persistencia.getFrequencies();



        GD = persistencia.getGrids();

        KB = persistencia.getKeyboards();


        // alphabet and frequency fix
        for (Frequency f : FQ.values()) {
            Alphabet a = AP.get(f.getAlphabet().getName());
            if (null != a) {a.addFrequency((f)); f.setAlphabet(a);}
        }


        // keyboard fix
        for (Keyboard k : KB.values()) {
            String nomA  = k.getAlphabet().getName();
            String nomF = k.getFrequency().getName();
            int nomG = k.getGrid().getID();

            k.setAlph(AP.get(nomA));
            k.setFreq(FQ.get(nomF));
            k.setGrid(GD.get(nomG));
        }


        // frequency fix
        for (Frequency f : FQ.values()) {
            Alphabet a = AP.get(f.getAlphabet().getName());
            f.setAlphabet(a);
            if (null != a) a.addFrequency((f));
        }

    }

    /**
     * Obté informació general sobre l'estat actual del sistema, incloent el nombre d'alfabets,
     * freqüències, teclats i graelles emmagatzemades, així com la data i hora de l'últim guardat.
     *
     * @return Un vector de cadenes de caràcters que conté les següents dades:
     *     [0] - Nombre d'alfabets emmagatzemats.
     *     [1] - Nombre de freqüències emmagatzemades.
     *     [2] - Nombre de teclats emmagatzemats.
     *     [3] - Nombre de graelles emmagatzemades.
     *     [4] - Data i hora de l'últim guardat (en el format "yyyy-MM-dd HH:mm:ss").
     */
    public String[] Obtenir_Informacio() {
        String [] as = new String[5];
        as[0] = String.valueOf(AP.size());
        as[1] = String.valueOf(FQ.size());
        as[2] = String.valueOf(KB.size());
        as[3] = String.valueOf(GD.size());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        if (lastSaved == null) as[4] = "never";
        else as[4] = formatter.format(lastSaved);

        return as;
    }

    /**
     * Obté la distribució de tecles del teclat especificat pel seu nom.
     *
     * @param nomT Nom del teclat del qual es vol obtenir la distribució de tecles.
     * @return Un vector de caràcters que representa la distribució de tecles del teclat.
     */
    public char[] Obtenir_Distribucio_Teclat(String nomT) {
        Keyboard k = KB.get(nomT);
        return k.getDistribucio();
    }

    /**
     * Obté l'identificador de la graella associada al teclat especificat pel seu nom.
     *
     * @param nomT Nom del teclat pel qual es vol obtenir l'identificador de la graella associada.
     * @return L'identificador de la graella associada al teclat.
     */
    public int Obtenir_Nom_Grid_Teclat(String nomT) {
        Keyboard k = KB.get(nomT);
        return k.getGrid().getID();
    }

}
