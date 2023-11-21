package edu.upc.prop.clusterxx;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CtrlDomini {

    HashMap<String,Alphabet> AP = new HashMap<>();
    HashMap<String,Frequency> FQ = new HashMap<>();
    HashMap<String,Keyboard> KB = new HashMap<>();
    HashMap<Integer,Grid> GD = new HashMap<>();
    public CtrlDomini(){}

    public int Afegir_Teclat(String nomT, String nomA, String nomF, int idG) {
        if (KB.containsKey(nomT)) return -1;
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
        int i = 0;
        String name = "fusió (";
        for (String s : arrayF) {
            if (i > 0) {
                try {
                    f.fusion(FQ.get(s));
                    name += " + " + s;
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                name += s;
            }
            ++i;
        }
        name += ")";
        f.setName(name);
        FQ.put(f.getName(),f);
        (f.getAlphabet()).addFrequency(f);
        return f.getName();
    }
    public int Esborrar_Teclat(String nomT) {
        if (KB.containsKey(nomT)) {
            KB.remove(nomT);
            return 0;
        }
        return -1;
    }
    public int CanviarNom_Teclat(String nomT, String newNom) {
        if (!KB.containsKey(nomT)) return -1;
        if (KB.containsKey(newNom)) return -2;
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
    public void Afegir_Alfabet(String s, HashSet<Character> h){
        //h.add(' ');
        Alphabet a = new Alphabet(s,h);
        AP.put(s,a);
    }

    //pre: existeix l'alfabet, no existeix la freq, existeix el fitxer
    public int Afegir_Freq_FromPath(String nomF, String path, String nomA, int mode){ //PARA PASAR DE PATH DE FICHERO A STRING[] PARA LA CONSTRUCTORA DE FREQ PARA CREARLA
        try{

            /*if(FQ.containsKey(nomF))return 1;//ja existeix la freq

            if(!AP.containsKey(nomA))return 2; //l'alfabet no existeix*/

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

    public int Afegir_FreqMa(String nomA, String nomF, Vector<String> vs, int mode){
       /* if(FQ.containsKey(nomF))return 1; //la freq ja existeix
        if(!AP.containsKey(nomA))return 2; //l'alfabet no existeix*/

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

    // TODO NO SE BORRAN LOS ALFABETOS DE LOS TECLADOS !!!!!!!
    public void Esborrar_Alfabet(String s){
        for(String nomf : AP.get(s).getFrequencies().keySet()) FQ.remove(nomf);
        AP.remove(s);
    }
    public int CanviarNom_Alfabet(String s,String s2){
        if(!AP.containsKey(s)) return 1;
        Alphabet a = AP.get(s);
        a.setName(s2);
        AP.remove(s);
        if(AP.containsKey(s2))return 2;
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

    // TODO NO SE BORRAN FRECUENCIAS DE LOS TECLADOS !!!!!!!!!!!!!!!
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


    public int Modificar_Freq_Path(String nomF, String path, int mode){

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

    public int Modificar_FreqMa(String nomF, Vector<String> vs, int mode){

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
    public int Afegir_Grid (int x, boolean[][] pos) {
        if (GD.containsKey(x)) return -1;
        Grid g = new Grid(x,pos);
        GD.put(x,g);
        return 0;
    }

    // TODO NO SE BORRAN GRIDS DE LOS TECLAODs !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
}
