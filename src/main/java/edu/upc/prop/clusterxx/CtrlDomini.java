package edu.upc.prop.clusterxx;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

public class CtrlDomini {

    TreeMap<String,Alphabet> AP = new TreeMap<>();
    TreeMap<String,Frequency> FQ = new TreeMap<>();
    TreeMap<String,Keyboard> KD = new TreeMap<>();
    TreeMap<String,Grid> GD = new TreeMap<>();
    public CtrlDomini(){}

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
