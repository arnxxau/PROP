package edu.upc.prop.clusterxx;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

public class CtrlDomini {

    TreeMap<String,Alphabet> AP = new TreeMap<>();
    TreeMap<String,Frequency> FQ = new TreeMap<>();
    TreeMap<String,Keyboard> KB = new TreeMap<>();
    TreeMap<Integer,Grid> GD = new TreeMap<>();
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
        return 0;
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
    public int Afegir_Alfabet(String s, HashSet<Character> h){
        Alphabet a = new Alphabet(s,h);
        if(AP.containsKey(s))return 1;
        AP.put(s,a);
        return 0;
    }
    /*public int Afegir_Freqs(String nom, String path, String nomAlfabet){ //PARA PASAR DE PATH DE FICHERO A STRING[] PARA LA CONSTRUCTORA DE FREQ PARA CREARLA
        try{
            //Frequency f = new Frequency(nom, path, Frequency.TEXT_MODE);
            if(FQ.containsKey(nom))return 1;//ja existeix la freq

            if(!AP.containsKey(nomAlfabet))return 2; //l'alfabet no existeix

            Alphabet a = AP.get(nomAlfabet);

            /*for(Character c : f.getFreq().keySet()){//comprobant si existeix un caracter a la freq i no al alfabet a.
                if(!a.existsCharacter(c))return 3;
                for(Character c1 : f.getFreq().get(c).keySet()){
                    if(!a.existsCharacter(c1))return 3;
                }
            }
            f.setAlphabet(a); //si tots els caracters de la freq hi son també al alfabet, li asignem l'alfabet
            a.afegir_freq(f);  //a l'alfabet li afegim la freq.
            FQ.put(nom,f); //afegim la frequencia

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }*/

    public int Afegir_FreqText(String nomA, String nomF, Vector<String> vs){
        if(FQ.containsKey(nomF))return 1; //la freq ja existeix
        if(!AP.containsKey(nomA))return 2; //l'alfabet no existeix

        String[] text = new String[vs.size()];
        for(int i=0; i < text.length; i++){
            text[i]=vs.get(i);
        }

        Alphabet a = AP.get(nomA);

       /*for(String s : text){
            for(int i=0;i<s.length();i++){
                if(!a.existsCharacter(s.charAt(i)))return 3;
            }
        }*/
        Frequency f;
        try{
            f = new Frequency(nomF,text,1,a);
        }
        catch (Exception e){
            return 3;
        }// A l'alfabet no hi ha la lletra.

        FQ.put(nomF,f);
        f.printFrequencies();
        a.afegir_freq(f);//a l'alfabet se li afegeix la freq
        return 0;
    }

    public int Esborrar_Alfabet(String s){
        if(!AP.containsKey(s)) return 1;
        Alphabet a = AP.get(s);
        for (String nomF : a.getFrequencies().keySet()) FQ.remove(nomF);
        AP.remove(s);
        return 0;
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
    public int Esborrar_Frequencia (String nomF) {
        if (!FQ.containsKey(nomF)) return -1;
        FQ.remove(nomF);
        return 0;
    }
    public int Afegir_Grid (int x, boolean[][] pos) {
        if (GD.containsKey(x)) return -1;
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
}
