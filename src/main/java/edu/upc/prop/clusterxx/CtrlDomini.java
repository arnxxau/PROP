package edu.upc.prop.clusterxx;

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
    public Vector<Vector<String>> Consultar_Teclats(){
        Vector<Vector<String>> vvs = new Vector<>();
        for (Keyboard valor : KB.values()) {
            Vector<String> vs = new Vector<>();
            vs.add(valor.getName());
            vs.add(valor.getAlphabet().getName());
            vs.add(valor.getFrequency().getName());
            vs.add(valor.getGrid().getID().toString());
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
    public int Esborrar_Alfabet(String s){
        if(!AP.containsKey(s)) return 1;
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
}
