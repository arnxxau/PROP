package edu.upc.prop.clusterxx;

import edu.upc.prop.clusterxx.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CtrlDomini {

    HashMap<String,Alphabet> AP = new HashMap<>();
    HashMap<String,Frequency> FQ = new HashMap<>();
    HashMap<String,Keyboard> KB = new HashMap<>();
    HashMap<Integer,Grid> GD = new HashMap<>();
    CtrlPersistencia persistencia = new CtrlPersistencia();

    Instant lastSaved = null;
    public CtrlDomini(){}

    public int Afegir_Teclat(String nomT, String nomA, String nomF, int idG) throws ExisteixID_Exception{
        if (KB.containsKey(nomT)){
            throw new ExisteixID_Exception();
            //return -1;
        }
        Alphabet a = AP.get(nomA);
        if (a == null) return -2;
        Frequency f = a.getFrequencies().get(nomF);
        if (f == null) return -3;
        Grid g = GD.get(idG);
        if (g == null) return -4;
        if (g.getSize() != a.size()) return -5;
        Keyboard k = new Keyboard(nomT,a,f,g);
        KB.put(nomT,k);
        return 0;
    }
    public String fusionarFreqs(ArrayList<String> arrayF) {
        if (arrayF.isEmpty()) return null;
        Frequency f = FQ.get(arrayF.get(0));
        if (arrayF.size() > 1) {
            int i = 0;
            String name = "fusió (";
            for (String s : arrayF) {
                if (i > 0) {
                    try {
                        f.fusion(FQ.get(s));
                        name += " + " + s;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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
    public int Esborrar_Teclat(String nomT) {
        if (KB.containsKey(nomT)) {
            KB.remove(nomT);
            return 0;
        }
        return -1;
    }
    public int CanviarNom_Teclat(String nomT, String newNom)throws ExisteixID_Exception {
        if (!KB.containsKey(nomT)) return -1;
        if (KB.containsKey(newNom)){
            throw new ExisteixID_Exception();
            //return -2;
        }
        Keyboard k = KB.get(nomT);
        k.setNom(newNom);
        KB.remove(nomT);
        KB.put(newNom,k);
        return 0;
    }
    public int Actualitzar_Teclat(String nomT) {
        if (!KB.containsKey(nomT)) return -1;
        Keyboard k = KB.get(nomT);
        k.update();
        return 0;
    }
    public Vector<Vector<String>> Consultar_Teclats(){
        Vector<Vector<String>> vvs = new Vector<>();
        for (Keyboard valor : KB.values()) {
            Vector<String> vs = new Vector<>();
            vs.add(valor.getName());
            vs.add(valor.getAlphabet().getName());
            vs.add(valor.getFrequency().getName());
            vs.add(((Integer)valor.getGrid().getID()).toString());
            vs.add(valor.getLayout());
            vvs.add(vs);
        }
        return vvs;
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
    public int Afegir_Freq_FromPath(String nomF, String path, String nomA, int mode)throws CaractersfromFreq_notInAlph_Exception, IOException,ExisteixID_Exception { //PARA PASAR DE PATH DE FICHERO A STRING[] PARA LA CONSTRUCTORA DE FREQ PARA CREARLA
        try{

            if(FQ.containsKey(nomF)) throw new ExisteixID_Exception();//return 1;ja existeix la freq

           // if(!AP.containsKey(nomA))return 2; //l'alfabet no existeix*/

            Alphabet a = AP.get(nomA);

            String[] text = llegir_archiu_path(path);//pasa del texto del fichero path a string[]

            /*if (text==null)return 4; // no existeix o no es troba el fitxer*/

            Frequency f;
            try{
                f = new Frequency(nomF, text, mode, a);
            }
            catch (Exception e){
                return 3; // NO EXISTEIX LA LLETRA A L'ALFABET

            };

            f.setAlphabet(a); //si tots els caracters de la freq hi son també al alfabet, li asignem l'alfabet
            a.addFrequency(f);  //a l'alfabet li afegim la freq.
            FQ.put(nomF,f); //afegim la frequencia

        }catch (Exception e){
            System.out.println(e.getMessage()); // EXCEPCIÓ NO EXISTEIX EL PATH
        }

        return 0;
    }
    public String[][] datosFreqs(){
        String[][] ss = new String[FQ.size()][3];
        int i=0;
        for(Frequency f : FQ.values()){
            ss[i][0] = f.getName();
            ss[i][1] = f.getAlphabet().getName();
            if(f.getMode()==1)ss[i][2] = "text mode";//escrito a man
            else if(f.getMode() == 0) ss[i][2]= "raw mode";
            i++;
        }
        return ss;
    }

    public String[][] Consultar_Freq(){
        String[][] res = new String[FQ.size()][3];
        int idx=0;
        for (Map.Entry<String, Frequency> entry : FQ.entrySet()) {
            String clave = entry.getKey();
            Frequency valor = entry.getValue();
            String[] a = new String[3];
            a[0]=clave;
            a[1]=valor.getAlphabet().getName();
            a[2]= String.valueOf(valor.getMode());

            res[idx] = a;
            idx += 1;
        }
        return res;
    }

    private String[] llegir_archiu_path(String path){

        List<String> lines = new ArrayList<>();

        /*File f =  new File(path);
        if(!f.exists())return null;*/

        try {
            lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        }catch (Exception e){
            System.out.println(e.getMessage()); //inout exception
        }

        return lines.toArray(new String[0]);
    }

    public int Afegir_FreqMa(String nomA, String nomF, Vector<String> vs, int mode)throws CaractersfromFreq_notInAlph_Exception,ExisteixID_Exception{
        if(FQ.containsKey(nomF)) throw new ExisteixID_Exception();//return 1; la freq ja existeix
       // if(!AP.containsKey(nomA))return 2; l'alfabet no existeix

        String[] text = new String[vs.size()];
        for(int i=0; i < text.length; i++){
            text[i]=vs.get(i);
        }

        Alphabet a = AP.get(nomA);

        Frequency f;
        try{
            f = new Frequency(nomF,text,mode,a);
        }
        catch (Exception e){
            return 3;
        }// A l'alfabet no hi ha la lletra.

        FQ.put(nomF,f);
        //f.printFrequencies();
        a.addFrequency(f);//a l'alfabet se li afegeix la freq
        return 0;
    }

    public void Esborrar_Alfabet(String s){
        if (AP.get(s).getFrequencies() != null) { // safe delete
            for(String nomf : AP.get(s).getFrequencies().keySet()) FQ.remove(nomf);
        }

        AP.remove(s);
    }
    public int CanviarNom_Alfabet(String s,String s2)throws ExisteixID_Exception{
        if(!AP.containsKey(s)) return 1;
        if(AP.containsKey(s2)){
            throw new ExisteixID_Exception();
            // return 2;
        }
        Alphabet a = AP.get(s);
        a.setName(s2);
        AP.remove(s);
        AP.put(s2,a);
        return 0;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());


        s[3] = formatter.format(a.getLastMod());
        s[4] = formatter.format(a.getCrDate());

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


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        s[3] = formatter.format(f.getLastModifiedTime());
        s[4] = formatter.format(f.getCreationDate());

        return s;
    }

    public String[] Consultar_Teclat(String nomT){

        Keyboard k = KB.get(nomT);

        String [] s = new String[5];

        s[0] = k.getName();
        s[1] = k.getAlphabet().getName();

        s[2] = k.getFrequency().getName();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        s[3] = formatter.format(k.getLastMod());
        s[4] = formatter.format(k.getCrDate());
        return s;
    }

    public String[] Consultar_Grid(Integer ID){

        Grid f = GD.get(ID);

        String [] s = new String[5];

        s[0] = String.valueOf(f.getID());
        s[1] = String.valueOf(f.getPositions().size());

        Pair p = f.getMaxSize();

        s[2] = p.getX() + " " + p.getY();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        s[3] = "formatter.format(f.getLastModifiedTime())";
        s[4] = "formatter.format(f.getCreationDate())";

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

    public Vector<Vector<String>> Consultar_Freqs(String nomA){
        Vector<Vector<String>> vvs = new Vector<>();
        for (Frequency valor : FQ.values()) {

            Vector<String> vs = new Vector<>();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.setLength(0);
            if(valor.getAlphabet().getName().equals(nomA)){
                vs.add(valor.getName()); //vs[0] == nom

                stringBuilder.append(valor.getCreationDate());
                vs.add(stringBuilder.toString());//vs[1] == creationdate.
                stringBuilder.setLength(0);

                stringBuilder.append(valor.getLastModifiedTime());//
                vs.add(stringBuilder.toString());//vs[2] == lastmodifieddate
                stringBuilder.setLength(0);

                stringBuilder.append(valor.getFrequencyWeight());
                vs.add(stringBuilder.toString());//vs[3] == frequencyweight
                stringBuilder.setLength(0);

            }

            vvs.add(vs);
        }
        return vvs;
    }



    public Vector<String> NomsFreqs_Alfabet(String nomA){
        Vector<String> vs = new Vector<>();
        String nom;
        for(String s : AP.get(nomA).getFrequencies().keySet()){
            vs.add(s);
        }
        return vs;
    }

    public Vector<String> Noms_Freq(){
        Vector<String> vs = new Vector<>();
        String nom;
        for(String s : FQ.keySet()){
            vs.add(s);
        }
        return vs;
    }

    public int Esborrar_Frequencia (String nomF) {
        if (!FQ.containsKey(nomF)) return -1;
        Frequency f = FQ.get(nomF);
        f.getAlphabet().deleteFrequency(f);//quito del HashMap del alfabeto la freq.
        FQ.remove(nomF);
        return 0;
    }
    public boolean ExisteixFreq(String nomf){
        return FQ.containsKey(nomf);
    }


    public int Modificar_Freq_Path(String nomF, String path, int mode)throws CaractersfromFreq_notInAlph_Exception,IOException{

        Frequency f = FQ.get(nomF); //no fa falta anar al HashMapde l'alfabet a modificar la freq perquè en teoria es el mateix punter

        String[] text = llegir_archiu_path(path);
        try{
            f.modifyFrequency(text,mode);
        }
        catch (Exception e){
            return 3;
        }
        return 0;
    }

    public int Modificar_FreqMa(String nomF, Vector<String> vs, int mode)throws CaractersfromFreq_notInAlph_Exception{

        String[] text = new String[vs.size()];

        for(int i=0; i < text.length; i++){
            text[i]=vs.get(i);
        }

        Frequency f = FQ.get(nomF);
        try{
            f.modifyFrequency(text,mode);
        }
        catch (Exception e){
            return 3;
        }// A l'alfabet no hi ha la lletra.

        return 0;
    }

    public boolean ExisteixAlf(String noma){
        return AP.containsKey(noma);
    }

    public boolean ExisteixFitxer(String path){
        File f = new File(path);
        return f.exists();
    }
    public int Afegir_Grid (int x, boolean[][] pos)throws ExisteixID_Exception{
        if (GD.containsKey(x))throw new ExisteixID_Exception(); //return -1;
        Grid g = new Grid(x,pos);
        GD.put(x,g);
        return 0;
    }

    public int Esborrar_Grid (Integer idG) {
        if (GD.containsKey(idG)) {
            GD.remove(idG);
            return 0;
        }
        return -1;
    }
    public Vector<Vector<String>> Consultar_Grids() {
        Vector<Vector<String>> vvs = new Vector<>();
        for (Grid valor : GD.values()) {
            Vector<String> vs = new Vector<>();
            vs.add(((Integer)valor.getID()).toString());
            vs.add(valor.toString());
            vvs.add(vs);
        }
        return vvs;
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
        for (Frequency f : FQ.values()) AP.get(f.getAlphabet().getName()).addFrequency((f));
        GD = persistencia.getGrids();
        KB = persistencia.getKeyboards();
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
}
