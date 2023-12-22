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

public class CtrlDomini {

    HashMap<String, Alphabet> AP = new HashMap<>();
    HashMap<String, Frequency> FQ = new HashMap<>();
    HashMap<String, Keyboard> KB = new HashMap<>();
    HashMap<Integer, Grid> GD = new HashMap<>();
    CtrlPersistencia persistencia = new CtrlPersistencia();

    Instant lastSaved = null;
    public CtrlDomini(){}

    public void Afegir_Teclat(String nomT, String nomA, String nomF, int idG, int mode) throws ExisteixID_Exception, gridAndAlphabetNotSameSize_Exception {
        if (KB.containsKey(nomT)) throw new ExisteixID_Exception();

        Alphabet a = AP.get(nomA);
        Frequency f = a.getFrequencies().get(nomF);
        Grid g = GD.get(idG);

        if (g.getSize() != a.size()) throw new gridAndAlphabetNotSameSize_Exception();

        Keyboard k = new Keyboard(nomT,a,f,g,mode);
        KB.put(nomT,k);
    }
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
    public void Esborrar_Teclat(String nomT) {
        KB.remove(nomT);
    }
    public void CanviarNom_Teclat(String nomT, String newNom)throws ExisteixID_Exception {
        if (KB.containsKey(newNom)){
            throw new ExisteixID_Exception();
        }
        Keyboard k = KB.get(nomT);
        k.setNom(newNom);
        KB.remove(nomT);
        KB.put(newNom,k);
    }
    public void Actualitzar_Teclat(String nomT) {
        Keyboard k = KB.get(nomT);
        k.update();
    }

    public Vector<String> Noms_Teclats(){
       return new Vector<>(KB.keySet());
    }
    public void Afegir_Alfabet(String s, HashSet<Character> h)throws ExisteixID_Exception{
        //h.add(' ');
        if(AP.containsKey(s))throw new ExisteixID_Exception();
        Alphabet a = new Alphabet(s,h);
        AP.put(s,a);
    }

    //pre: existeix l'alfabet, no existeix la freq, existeix el fitxer
    public void Afegir_Freq_FromPath(String nomF, String path, String nomA, int mode) throws CaractersfromFreq_notInAlph_Exception, IOException, ExisteixID_Exception, badExtraction_Exception { //PARA PASAR DE PATH DE FICHERO A STRING[] PARA LA CONSTRUCTORA DE FREQ PARA CREARLA

            if(FQ.containsKey(nomF)) throw new ExisteixID_Exception();//return 1;ja existeix la freq

           // if(!AP.containsKey(nomA))return 2; //l'alfabet no existeix*/

            Alphabet a = AP.get(nomA);

            System.out.println(a);

            String[] text = llegir_archiu_path(path);//pasa del texto del fichero path a string[]

            /*if (text==null)return 4; // no existeix o no es troba el fitxer*/

            Frequency f;
            f = new Frequency(nomF, text, mode, a);


            f.setAlphabet(a); //si tots els caracters de la freq hi son també al alfabet, li asignem l'alfabet
            a.addFrequency(f);  //a l'alfabet li afegim la freq.
            FQ.put(nomF,f); //afegim la frequencia
    }


    private String[] llegir_archiu_path(String path) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        return lines.toArray(new String[0]);
    }

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

    public void Esborrar_Alfabet(String s){
        if (AP.get(s).getFrequencies() != null) { // safe delete
            for(String nomf : AP.get(s).getFrequencies().keySet()) FQ.remove(nomf);
        }
        AP.remove(s);
    }
    public void CanviarNom_Alfabet(String s,String s2)throws ExisteixID_Exception{
        if(AP.containsKey(s2)){
            throw new ExisteixID_Exception();
        }
        Alphabet a = AP.get(s);
        a.setName(s2);
        AP.remove(s);
        AP.put(s2,a);
    }


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

    public String[] Consultar_Grid(Integer ID){

        Grid f = GD.get(ID);

        String [] s = new String[5];

        s[0] = String.valueOf(f.getID());
        s[1] = String.valueOf(f.getPositions().size());

        Pair p = f.getMaxSize();

        s[2] = p.getX() + " " + p.getY();

        s[3] = f.getLastModifiedTime();
        s[4] = f.getCreationDate();

        return s;
    }


    public Vector<String> Noms_Alfabets(){
        Vector<String> vs = new Vector<>();
        for (String valor : AP.keySet()) {
            vs.add(valor);
        }
        return vs;
    }

    public Vector<String> Noms_Grids(){
        Vector<String> vs = new Vector<>();
        for (Integer valor : GD.keySet()) {
            vs.add(valor.toString());
        }
        return vs;
    }


    public Vector<String> NomsFreqs_Alfabet(String nomA){
        Vector<String> vs = new Vector<>();
        for(String s : AP.get(nomA).getFrequencies().keySet()){
            vs.add(s);
        }
        return vs;
    }

    public Vector<String> Noms_Freq(){
        Vector<String> vs = new Vector<>();
        for(String s : FQ.keySet()){
            vs.add(s);
        }
        return vs;
    }

    public void Esborrar_Frequencia (String nomF) {
        Frequency f = FQ.get(nomF);
        f.getAlphabet().deleteFrequency(f);//quito del HashMap del alfabeto la freq.
        FQ.remove(nomF);
    }


    public void Modificar_Freq_Path(String nomF, String path, int mode) throws CaractersfromFreq_notInAlph_Exception, IOException, badExtraction_Exception {

        Frequency f = FQ.get(nomF); //no fa falta anar al HashMapde l'alfabet a modificar la freq perquè en teoria es el mateix punter

        String[] text = llegir_archiu_path(path);

        f.modifyFrequency(text,mode);

    }

    public void Modificar_FreqMa(String nomF, Vector<String> vs, int mode) throws CaractersfromFreq_notInAlph_Exception, badExtraction_Exception {

        String[] text = new String[vs.size()];

        for(int i=0; i < text.length; i++){
            text[i]=vs.get(i);
        }

        Frequency f = FQ.get(nomF);
        f.modifyFrequency(text,mode);
    }

    public void Afegir_Grid (int x, boolean[][] pos)throws ExisteixID_Exception{
        if (GD.containsKey(x))throw new ExisteixID_Exception(); //return -1;
        Grid g = new Grid(x,pos);
        GD.put(x,g);
    }

    public void Esborrar_Grid (Integer idG) {
        GD.remove(idG);
    }

    public ArrayList<Pair> Obtenir_Reprentacio_Grid(int ID) {
        return GD.get(ID).getPositions();
    }

    public Pair Max_Grid(int ID) {
        return GD.get(ID).getMaxSize();
    }


    public void Guardar_Dades() {
        persistencia.saveAlphabets(AP);
        persistencia.saveFrequencies(FQ);
        persistencia.saveGrids(GD);
        persistencia.saveKeyboards(KB);

        lastSaved = Instant.now();
    }
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

    public char[] Obtenir_Distribucio_Teclat(String nomT) {
        Keyboard k = KB.get(nomT);
        return k.getDistribucio();
    }

    public int Obtenir_Nom_Grid_Teclat(String nomT) {
        Keyboard k = KB.get(nomT);
        return k.getGrid().getID();
    }

}
