package edu.upc.prop.clusterxx;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;

public class Driver_FP {
    inout io = new inout();

    Scanner s = new Scanner(System.in);

    CtrlDomini cd = new CtrlDomini();
    static final int MENUGENERAL=0;
    static final int MENUTECLAT=1;
    static final int MENUFREQUENCIA=2;
    static final int MENUALFABET=3;
    static final int MENUGRID=4;

    static final int MENUAFFRQ=0;

    public Driver_FP(){}

    private void Vista_af_freq(){
        try{io.writeln("Com vols Afegir la frequencia");
            io.writeln("0 - Sortir");
            io.writeln("1 - Fitxer Text a través d'un Path");
            io.writeln("2 - Fitxer Llista a través d'un Path");
            io.writeln("3 - Text a mà");
            io.writeln("4 - Llista a mà");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void Vista_General(){
        try{io.writeln("Menu General: ");
        io.writeln("0 - Sortir ");
        io.writeln("1 - Manteniment Teclats ");
        io.writeln("2 - Manteniment Frequencies ");
        io.writeln("3 - Manteniment Alfabets ");
        io.writeln("4 - Manteniment Grids ");}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void Vista_Teclats(){
        try{io.writeln("Menu Teclats: ");
            io.writeln("0 - Sortir ");
            io.writeln("1 - Afegir Teclat ");
            io.writeln("2 - Esborrar Teclat ");
            io.writeln("3 - Canviar Nom Teclat ");
            io.writeln("4 - Actualitzar Teclat ");
            io.writeln("5 - Llistar Teclats ");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void Vista_Frequencies() {
        try{io.writeln("Menu Freqüències: ");
            io.writeln("0 - Sortir ");
            io.writeln("1 - Afegir Freqüència ");
            io.writeln("2 - Esborrar Freqüència ");
            io.writeln("3 - Modificar Freqüència ");
            io.writeln("4 - Llistar Freqüències ");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void Vista_Alfabets() {
        try{io.writeln("Menu Alfabets: ");
            io.writeln("0 - Sortir ");
            io.writeln("1 - Afegir Alfabet ");
            io.writeln("2 - Esborrar Alfabet");
            io.writeln("3 - Canviar Nom Alfabet ");
            io.writeln("4 - Llistar Alfabets ");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
//hem de mirar si has tret un alfabet per exemple, quan accedim al teclat mirar ,que encara que estigui creat, no tingui un alfabet apuntant que no existeix.
    private void Vista_Grids() {
        try{io.writeln("Menu Grids: ");
            io.writeln("0 - Sortir ");
            io.writeln("1 - Afegir Grid ");
            io.writeln("2 - Esborrar Grid ");
            io.writeln("3 - Llistar Grids ");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private int Obtenir_Opcions(int nopcions,int viewtype){
        int opt = -1;
        while(opt<0 || opt>nopcions) {
            switch (viewtype){
                case 0:
                    Vista_General();
                    break;
                case 1:
                    Vista_Teclats();
                    break;
                case 2:
                    Vista_Frequencies();
                    break;
                case 3:
                    Vista_Alfabets();
                    break;
                case 4:
                    Vista_Grids();
                    break;
            }
            try {
                opt = Integer.parseInt(io.readword());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return opt;
    }
    private int Obtenir_Opcions_funcions(int nopcions, int viewtype) { //viewtype afegirfreq per exemple.
        int opt = -1;
        while(opt<0 || opt>nopcions) {
            switch (viewtype){
                case 0:
                    Vista_af_freq();
                    break;
            }
            try {
                opt = Integer.parseInt(io.readword());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return opt;
    }

    //pre : nomclase ha de ser el nom d'una classe de model que existeixi
    //se puede hacer mejor esta funcion.(se repiten acciones entre clases. con nomclase y tal)
    private void Opcions_de_Clase(String nomclase,int option){
        try {
            if (option != 0) {
                switch (nomclase) {
                    case "Teclat":
                        switch (option) {
                            case 1:
                                io.writeln("Afegint Teclat");
                                String nomT = Demanar_Nom("Teclat");
                                llistar_alfabets(cd.Consultar_Alfabets());
                                String nomA = Demanar_Nom("Alfabet");
                                llistar_frequencies(cd.Consultar_Freqs(nomA));
                                String nomF = Demanar_Nom("Freqüència");
                                llistar_grids(cd.Consultar_Grids());
                                int idG = Demanar_ID("Graella");
                                int ret = cd.Afegir_Teclat(nomT, nomA, nomF, idG);
                                switch (ret) {
                                    case -1:
                                        io.writeln("El teclat " + nomT + " ja existeix");
                                        break;
                                    case -2:
                                        io.writeln("L'alfabet " + nomA + " no existeix");
                                        break;
                                    case -3:
                                        io.writeln("La freqüència " + nomF + " no existeix o no pertany a l'alfabet " + nomA);
                                        break;
                                    case -4:
                                        io.writeln("La Grid número " + idG + " no eisteix");
                                        break;
                                    case -5:
                                        io.writeln("El grid número " + idG + " no té el mateix nombre de posicions disponibles que caràcters l'alfabet");
                                }
                                io.writeln("Teclat afegit");
                                break;
                            case 2:
                                io.writeln("Esborrant Teclat");
                                String nomT1 = Demanar_Nom("Teclat");
                                ret = cd.Esborrar_Teclat(nomT1);
                                if (ret == -1) io.writeln("El teclat " + nomT1 + " NO existeix");
                                else io.writeln("Teclat Esborrat");
                                break;
                            case 3:
                                io.writeln("Canviant nom Teclat");
                                String nomT3 = Demanar_Nom("Teclat");
                                String newNom = Demanar_Nom("nou Teclat");
                                ret = cd.CanviarNom_Teclat(nomT3, newNom);
                                if (ret == -1) io.writeln("El Teclat " + nomT3 + " NO existeix");
                                else if (ret == -2) io.writeln("El Teclat " + newNom + " JA existeix");
                                else io.writeln("Teclat " + nomT3 + " ha canviat de nom a " + newNom);
                                break;
                            case 4:
                                io.writeln("Actualitzant teclat");
                                String nomT4 = Demanar_Nom("Teclat");
                                ret = cd.Actualitzar_Teclat(nomT4);
                                if (ret == -1) io.writeln("El Teclat " + nomT4 + " NO existeix");
                                else io.writeln("Teclat actualitzat");
                            case 5:
                                io.writeln("Llistant Teclats: ");
                                llistar_Teclats(cd.Consultar_Teclats());
                                break;
                        }
                        break;
                    case "Frequencia":
                        switch (option) {
                            case 1:
                                io.writeln("Afegint Frequencia a un Alfabet");
                                int opt2=-1;
                                while(opt2!=0){
                                    opt2 = Obtenir_Opcions_funcions(4,MENUAFFRQ);
                                    if(opt2!=0){
                                        switch (opt2){

                                            case 1: //opcio text d'un fitxer
                                                String s = Demanar_Nom("Alfabet");
                                                if(!cd.ExisteixAlf(s)){
                                                    System.out.println("No existeix l'Alfabet " + s);
                                                    llistar_alfabets(cd.Consultar_Alfabets());
                                                    break;
                                                }
                                                String s2 = Demanar_Nom("nova Freqüència");
                                                if(cd.ExisteixFreq(s2)){
                                                    System.out.println("Ja existeix la Freqüència " + s2);
                                                    llistar_frequencies(cd.Consultar_Freqs(s));
                                                    break;
                                                }
                                                String s3 = Demanar_Fitxer();
                                                if(!cd.ExisteixFitxer(s3)){
                                                    System.out.println("No existeix el fitxer que m'has passat amb path " + s3);
                                                    break;
                                                }
                                                int ret = cd.Afegir_Freq_FromPath(s2,s3,s,1);

                                                if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de l'Alfabet " + s);

                                                else io.writeln("Freqüencia " + s2 + " afegida amb el Alfabet " + s);
                                                break;

                                            case 2:
                                                //opcio llista d'un fitxer
                                                s = Demanar_Nom("Alfabet");
                                                if(!cd.ExisteixAlf(s)){
                                                    System.out.println("No existeix l'Alfabet " + s);
                                                    llistar_alfabets(cd.Consultar_Alfabets());
                                                    break;
                                                }
                                                s2 = Demanar_Nom("nova Freqüència");
                                                if(cd.ExisteixFreq(s2)){
                                                    System.out.println("Ja existeix la Freqüència " + s2);
                                                    llistar_frequencies(cd.Consultar_Freqs(s));
                                                    break;
                                                }
                                                s3 = Demanar_Fitxer(); //path
                                                if(!cd.ExisteixFitxer(s3)){
                                                    System.out.println("No existeix el fitxer que m'has passat amb path " + s3);
                                                    break;
                                                }

                                                ret = cd.Afegir_Freq_FromPath(s2,s3,s,0);
                                                if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de l'Alfabet " + s);

                                                else io.writeln("Freqüencia " + s2 + " afegida amb el Alfabet " + s);
                                                break;

                                            case 3:
                                                //opcio text a mà
                                                s = Demanar_Nom("Alfabet");
                                                if(!cd.ExisteixAlf(s)){
                                                    System.out.println("No existeix l'Alfabet " + s);
                                                    llistar_alfabets(cd.Consultar_Alfabets());
                                                    break;
                                                }
                                                s2 = Demanar_Nom("nova Freqüència");
                                                if(cd.ExisteixFreq(s2)){
                                                    System.out.println("Ja existeix la Freqüència " + s2);
                                                    llistar_frequencies(cd.Consultar_Freqs(s));
                                                    break;
                                                }
                                                Vector<String> text = Demanar_text(false);//demanar text en forma normal
                                                ret = cd.Afegir_FreqMa(s,s2,text,1);

                                                if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de l'Alfabet " + s);

                                                else io.writeln("Freqüencia " + s2 + " afegida amb el Alfabet " + s);
                                                break;

                                            case 4:
                                                //opcio llista a mà
                                                s = Demanar_Nom("Alfabet");
                                                if(!cd.ExisteixAlf(s)){
                                                    System.out.println("No existeix l'Alfabet " + s);
                                                    llistar_alfabets(cd.Consultar_Alfabets());
                                                    break;
                                                }
                                                s2 = Demanar_Nom("nova Freqüència");
                                                if(cd.ExisteixFreq(s2)){
                                                    System.out.println("Ja existeix la Freqüència " + s2);
                                                    llistar_frequencies(cd.Consultar_Freqs(s));
                                                    break;
                                                }
                                                Vector<String> llista = Demanar_text(true);//text en forma de llista.
                                                ret = cd.Afegir_FreqMa(s,s2,llista,0);

                                                if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de l'Alfabet " + s);

                                                else io.writeln("Freqüencia " + s2 + " afegida amb el Alfabet " + s);
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 2:
                                io.writeln("Esborrant Freqüència");
                                String nomF = Demanar_Nom("Freqüència");
                                int ret = cd.Esborrar_Frequencia(nomF);
                                if (ret == -1) io.writeln("La Freqüència " + nomF + " no existeix");
                                else io.writeln("Freqüència esborrada");
                                break;
                            case 3:
                                io.writeln("Modificant Frequencia");
                                io.writeln("funcionalitat en desenvolupament...");
                                break;
                            case 4:
                                io.writeln("Llistant Freqüències d'un Alfabet");
                                String nomA = Demanar_Nom("Alfabet");
                                llistar_frequencies(cd.Consultar_Freqs(nomA));
                                break;
                        }
                        break;
                    case "Alfabet":
                        switch (option) {
                            case 1:
                                io.writeln("Afegint Alfabet");
                                String s = Demanar_Nom("Alfabet");
                                if(cd.ExisteixAlf(s)){
                                    System.out.println("Ja existeix l'Alfabet " + s);
                                    llistar_alfabets(cd.Consultar_Alfabets());
                                    break;
                                }
                                HashSet<Character> h = Demanar_chars_Alfabet();
                                cd.Afegir_Alfabet(s, h);

                                io.writeln("Alfabet Afegit");
                                break;
                            case 2:
                                io.writeln("Esborrant Alfabet");
                                String s1 = Demanar_Nom("Alfabet");

                                if(!cd.ExisteixAlf(s1)){
                                    System.out.println("No existeix l'Alfabet " + s1);
                                    llistar_alfabets(cd.Consultar_Alfabets());
                                    break;
                                }

                                cd.Esborrar_Alfabet(s1);

                                io.writeln("Alfabet Esborrat");
                                break;
                            case 3:
                                io.writeln("Canviant nom Alfabet");
                                String s2 = Demanar_Nom("Alfabet");
                                String s3 = Demanar_Nom("nou Alfabet");
                                int ret = cd.CanviarNom_Alfabet(s2, s3);
                                if (ret == 1) io.writeln("L'Alfabet el nom " + s2 + " NO existeix");
                                else if (ret == 2) io.writeln("L'Alfabet el nom " + s3 + " JA existeix");
                                else io.writeln("Alfabet " + s2 + " ha canviat de nom a " + s3);

                                break;
                            case 4:
                                io.writeln("Llistant Alfabets: ");
                                llistar_alfabets(cd.Consultar_Alfabets());
                                break;
                        }
                        break;
                    case "Grid":
                        switch (option) {
                            case 1:
                                io.writeln("Afegint Grid");
                                Integer x = Demanar_ID("Grid");
                                boolean[][] b = Demanar_mat_Grid();
                                if (b == null) {
                                    io.writeln("Format incorrecte");
                                    break;
                                }
                                int ret = cd.Afegir_Grid(x,b);
                                if (ret == -1) io.writeln("El Grid " + x + " ja existeix");
                                else io.writeln("Grid afegit");
                                break;
                            case 2:
                                io.writeln("Esborrant Grid");
                                x = Demanar_ID("Grid");
                                ret = cd.Esborrar_Grid(x);
                                if (ret == -1) io.writeln("El Grid número " + x.toString() + " no existeix");
                                else io.writeln("Grid esborrat");
                                break;
                            case 3:
                                io.writeln("llistant grids");
                                llistar_grids(cd.Consultar_Grids());
                                break;
                        }
                        break;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void llistar_Teclats(Vector<Vector<String>> vvs) {
        for(Vector<String> vs : vvs){
            try {
                io.writeln("NOM: " + vs.get(0));
                io.writeln("ALFABET: " + vs.get(1));
                io.writeln("FREQÜÈNCIA: " + vs.get(2));
                io.writeln("GRID: " + vs.get(3));
                io.writeln("LAYOUT: ");
                io.writeln(vs.get(4));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void llistar_alfabets(Vector<Vector<String>> vvs){
        for(Vector<String> vs : vvs){
            try {
                io.writeln("NOM: " + vs.get(0));
                io.writeln("CARÀCTERS: " + vs.get(1));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void llistar_frequencies(Vector<Vector<String>> vvs){
        for(Vector<String> vs : vvs){
            try {
                io.writeln("NOM: " + vs.get(0));
                io.writeln("Data de Creació: " + vs.get(1));
                io.writeln("Data última modificació: " + vs.get(2));
                io.writeln("Frequency weight: " + vs.get(3));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void llistar_grids(Vector<Vector<String>> vvs){
        for(Vector<String> vs : vvs){
            try {
                io.writeln("ID: " + vs.get(0));
                io.writeln("POSICIONS VÀLIDES:\n" + vs.get(1));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private Integer Demanar_ID(String nomclase) {
        int x = -1;
        try{
            io.writeln("Necessito la ID del " + nomclase);
            x = Integer.parseInt(io.readword());//nombre del alfabeto
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return x;
    }

    private String Demanar_Nom(String nomclase){
        String s1="";
        try{
            io.writeln("Necessito el nom del " + nomclase);
            s1 = io.readword();//nombre del alfabeto

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return s1;
    }
    public Vector<String> Demanar_text(boolean mode){
        Vector<String> vs = new Vector<>();
        String s;
        try{
            if(mode)io.writeln("Escriu una llista de freq de la forma\n " +
                    " 600\n" +
                    "    a c 11\n" +
                    "    a v 15\n" +
                    "    a x 99\n " + " on 600 és ns la verda i la resta es per cada parell p.e " +
                    "a c quantes vegades apereix junta, en aquest exemple 11, acabat amb un espai i un '.' del que agafarem les frequencies");

            else io.writeln("Escriu el text acabat amb un espai i un '.' del que agafarem les frequencies");
            while(!(s = io.readword()).equals(".")){
                vs.add(s);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return vs;
    }
    //suposem que el fitxer té escrites bé les dades
    private String Demanar_Fitxer(){
        String s1="";
        try{
            io.writeln("Necessito el Path a un fitxer de text del que extraure les Freqüències , el fitxer ha de ser un text amb només paraules, " +
                    "amb cap caracter que no sigui del propi alfabet");
            s1 = io.readword();//nombre del Path
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return s1;
    }

    private HashSet<Character> Demanar_chars_Alfabet(){
        String s2="";
        HashSet<Character> car = new HashSet<>();
        try{
            io.writeln("Necesito un conjunt de caracters per l'alfabet");
            s2 = io.readword();//caracteres del alfabeto
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        for(int i=0;i<s2.length();i++){
            car.add(s2.charAt(i));
        }
        return car;
    }
    private boolean[][] Demanar_mat_Grid() {
        boolean[][] res = null;
        try {
            io.writeln("Indica l'amplada màxima del Grid:");
            int x = Integer.parseInt(io.readword());
            io.writeln("Indica l'alçada màxima del Grid:");
            int y = Integer.parseInt(io.readword());
            res = new boolean[x][y];
            io.writeln("Indica les posicions vàlides (1 = vàlid; 0 = no vàlid; separats per espais)");
            for (int i = 0; i < x; ++i) {
                for (int j = 0; j < y; ++j) {
                    int c = Integer.parseInt(io.readword());
                    if (c == 1) res[i][j] = true;
                    else if (c == 0) res[i][j] = false;
                    else return null;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public void Next_Step_from_Menu_General(){
        int opt=-1;
        while(opt!=0){
            int option=-1;
            opt = Obtenir_Opcions(4,MENUGENERAL);
            switch(opt){
                case 0:
                    break;
                case 1:
                    while(option!=0){
                        option = Obtenir_Opcions(5,MENUTECLAT);
                        Opcions_de_Clase("Teclat",option);
                    }
                    break;
                case 2:
                    while(option!=0){
                        option = Obtenir_Opcions(4,MENUFREQUENCIA);
                        Opcions_de_Clase("Frequencia",option);
                    }
                    break;
                case 3:
                    while(option!=0){
                        option = Obtenir_Opcions(4,MENUALFABET);
                        Opcions_de_Clase("Alfabet",option);
                    }
                    break;
                case 4:
                    while (option != 0) {
                        option = Obtenir_Opcions(3,MENUGRID);
                        Opcions_de_Clase("Grid",option);
                    }
                    break;
            }
        }
    }

}
