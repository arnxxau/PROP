package edu.upc.prop.clusterxx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import static edu.upc.prop.clusterxx.Keyboard.*;

public class Driver_FP {
    InOutPlus ioPlus = new InOutPlus();

    CtrlDomini cd = new CtrlDomini();
    static final int MENUGENERAL=1;
    static final int MENUTECLAT=2;
    static final int MENUFREQUENCIA=3;
    static final int MENUALFABET=4;
    static final int MENUGRID=5;

    static final int MENUAFFRQ=0;

    public Driver_FP(){}

    public static void main(String[] args) throws Exception {
        Driver_FP df = new Driver_FP();
        df.Next_Step_from_Menu_General();
    }


    private void Vista_af_freq(String afegirOModificar){
        try{
            ioPlus.print("Com vols "+afegirOModificar+" la frequencia");
            ioPlus.print("0 - Sortir");
            ioPlus.print("1 - Fitxer Text a través d'un Path");
            ioPlus.print("2 - Fitxer Llista a través d'un Path");
            ioPlus.print("3 - Text a mà");
            ioPlus.print("4 - Llista a mà");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void Vista_General(){
        try{
            ioPlus.print("Menu General: ");
            ioPlus.print("0 - Sortir ");
            ioPlus.print("1 - Manteniment Teclats ");
            ioPlus.print("2 - Manteniment Frequencies ");
            ioPlus.print("3 - Manteniment Alfabets ");
            ioPlus.print("4 - Manteniment Grids ");
            ioPlus.print("5 - guardar");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void Vista_Teclats(){
        try{
            ioPlus.print("Menu Teclats: ");
            ioPlus.print("0 - Sortir ");
            ioPlus.print("1 - Afegir Teclat ");
            ioPlus.print("2 - Esborrar Teclat ");
            ioPlus.print("3 - Canviar Nom Teclat ");
            ioPlus.print("4 - Actualitzar Teclat ");
            ioPlus.print("5 - Llistar Teclats ");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void Vista_Frequencies() {
        try{
            ioPlus.print("Menu Freqüències: ");
            ioPlus.print("0 - Sortir ");
            ioPlus.print("1 - Afegir Freqüència a Alfabet");
            ioPlus.print("2 - Esborrar Freqüència ");
            ioPlus.print("3 - Modificar Freqüència ");
            ioPlus.print("4 - Llistar Freqüències d'un alfabet ");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void Vista_Alfabets() {
        try{
            ioPlus.print("Menu Alfabets: ");
            ioPlus.print("0 - Sortir ");
            ioPlus.print("1 - Afegir Alfabet ");
            ioPlus.print("2 - Esborrar Alfabet");
            ioPlus.print("3 - Canviar Nom Alfabet ");
            ioPlus.print("4 - Llistar Alfabets ");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void Vista_Grids() {
        try{
            ioPlus.print("Menu Grids: ");
            ioPlus.print("0 - Sortir ");
            ioPlus.print("1 - Afegir Grid ");
            ioPlus.print("2 - Esborrar Grid ");
            ioPlus.print("3 - Llistar Grids ");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private int Obtenir_Opcions(int nopcions,int viewtype) throws IOException {
        int opt = -1;
        while(opt<0 || opt>nopcions) {
            switch (viewtype){
                case 0:
                    break;
                case 1:
                    Vista_General();
                    break;
                case 2:
                    Vista_Teclats();
                    break;
                case 3:
                    Vista_Frequencies();
                    break;
                case 4:
                    Vista_Alfabets();
                    break;
                case 5:
                    Vista_Grids();
                    break;
            }

            opt = ioPlus.readInt();
        }
        return opt;
    }
    private int Obtenir_Opcions_funcions(int nopcions, int viewtype,String afegirOModificar) { //viewtype afegirfreq per exemple.
        int opt = -1;
        while(opt<0 || opt>nopcions) {
            switch (viewtype){
                case 0:
                    Vista_af_freq(afegirOModificar);
                    break;
            }
            opt = ioPlus.readInt();
        }
        return opt;
    }

    //pre : nomclase ha de ser el nom d'una classe de model que existeixi
    //se puede hacer mejor esta funcion.(se repiten acciones entre clases. con nomclase y tal)
    private void Opcions_de_Clase(String nomclase,int option){
        try {
            if (option != 0) {
                int ret;
                String path;
                String nomT;
                String newNomT;
                String nomA;
                String nomnewA;
                String nomF;
                int idG;
                switch (nomclase) {
                    case "Teclat":
                        switch (option) {
                            case 1:
                                ioPlus.print("Afegint Teclat");
                                nomT = Demanar_Nom("Teclat");
                                llistar_alfabets(cd.Consultar_Alfabets());
                                nomA = Demanar_Nom("Alfabet");
                                llistar_frequencies(cd.Consultar_Freqs(nomA));
                                ArrayList<String> arrayF = Demanar_Freqs();
                                nomF = cd.fusionarFreqs(arrayF);
                                if (nomF == null) ioPlus.print("has d'indicar com a mínim una freqüència");
                                llistar_grids(cd.Consultar_Grids());
                                idG = Demanar_ID("Graella");
                                ret = cd.Afegir_Teclat(nomT, nomA, nomF, idG, LocalSearchAlgorithm);
                                switch (ret) {
                                    case -1:
                                        ioPlus.print("El teclat " + nomT + " ja existeix");
                                        break;
                                    case -2:
                                        ioPlus.print("L'alfabet " + nomA + " no existeix");
                                        break;
                                    case -3:
                                        ioPlus.print("La freqüència " + nomF + " no existeix o no pertany a l'alfabet " + nomA);
                                        break;
                                    case -4:
                                        ioPlus.print("La Grid número " + idG + " no eisteix");
                                        break;
                                    case -5:
                                        ioPlus.print("El grid número " + idG + " no té el mateix nombre de posicions disponibles que caràcters l'alfabet");
                                }
                                ioPlus.print("Teclat afegit");
                                break;
                            case 2:
                                ioPlus.print("Esborrant Teclat");
                                nomT = Demanar_Nom("Teclat");
                                ret = cd.Esborrar_Teclat(nomT);
                                if (ret == -1) ioPlus.print("El teclat " + nomT + " NO existeix");
                                else ioPlus.print("Teclat Esborrat");
                                break;
                            case 3:
                                ioPlus.print("Canviant nom Teclat");
                                nomT = Demanar_Nom("Teclat");
                                ioPlus.print("Necessito el nou nom de teclat");
                                newNomT = ioPlus.readWord();
                                ret = cd.CanviarNom_Teclat(nomT, newNomT);
                                if (ret == -1) ioPlus.print("El Teclat " + nomT + " NO existeix");
                                else if (ret == -2) ioPlus.print("El Teclat " + newNomT + " JA existeix");
                                else ioPlus.print("Teclat " + nomT + " ha canviat de nom a " + newNomT);
                                break;
                            case 4:
                                ioPlus.print("Actualitzant teclat");
                                nomT = Demanar_Nom("Teclat");
                                ret = cd.Actualitzar_Teclat(nomT);
                                if (ret == -1) ioPlus.print("El Teclat " + nomT + " NO existeix");
                                else ioPlus.print("Teclat actualitzat");
                            case 5:
                                ioPlus.print("Llistant Teclats: ");
                                llistar_Teclats(cd.Consultar_Teclats());
                                break;
                        }
                        break;
                    case "Frequencia":
                        switch (option) {
                            case 1:
                                ioPlus.print("Afegint Frequencia a un Alfabet");
                                int opt2=-1;

                                nomA = Demanar_Nom("Alfabet");
                                if(!cd.ExisteixAlf(nomA)){
                                    System.out.println("No existeix l'Alfabet " + nomA);
                                    llistar_alfabets(cd.Consultar_Alfabets());
                                    break;
                                }

                                nomF = Demanar_Nom("nova Freqüència");
                                if(cd.ExisteixFreq(nomF)){
                                    System.out.println("Ja existeix la Freqüència " + nomF);
                                    llistar_frequencies(cd.Consultar_Freqs(nomA));
                                    break;
                                }
                                while(opt2!=0){
                                    opt2 = Obtenir_Opcions_funcions(4,MENUAFFRQ,"afegir");
                                        switch (opt2){
                                            case 0:
                                                break;
                                            case 1: //opcio text d'un fitxer
                                                path = Demanar_Fitxer();
                                                if(!cd.ExisteixFitxer(path)){
                                                    System.out.println("No existeix el fitxer que m'has passat amb path " + path);
                                                    break;
                                                }
                                                ret = cd.Afegir_Freq_FromPath(nomF,path,nomA,1);

                                                if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de l'Alfabet " + nomA);

                                                else ioPlus.print("Freqüencia " + nomF + " afegida amb el Alfabet " + nomA);
                                                break;

                                            case 2:
                                                //opcio llista d'un fitxer

                                                path = Demanar_Fitxer(); //path
                                                if(!cd.ExisteixFitxer(path)){
                                                    System.out.println("No existeix el fitxer que m'has passat amb path " + path);
                                                    break;
                                                }

                                                ret = cd.Afegir_Freq_FromPath(nomF,path,nomA,0);
                                                if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de l'Alfabet " + nomA);

                                                else ioPlus.print("Freqüencia " + nomF + " afegida amb el Alfabet " + nomA);
                                                break;

                                            case 3:
                                                //opcio text a mà

                                                Vector<String> text = Demanar_text(false);//demanar text en forma normal
                                                ret = cd.Afegir_FreqMa(nomA,nomF,text,1);

                                                if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de l'Alfabet " + nomA);

                                                else ioPlus.print("Freqüencia " + nomF + " afegida amb el Alfabet " + nomA);
                                                break;

                                            case 4:
                                                //opcio llista a mà
                                                Vector<String> llista = Demanar_text(true);//text en forma de llista.
                                                ret = cd.Afegir_FreqMa(nomA,nomF,llista,0);

                                                if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de l'Alfabet " + nomA);

                                                else ioPlus.print("Freqüencia " + nomF + " afegida amb el Alfabet " + nomA);
                                                break;
                                        }
                                }
                                break;
                            case 2:
                                ioPlus.print("Esborrant Freqüència");
                                nomF = Demanar_Nom("Freqüència");
                                ret = cd.Esborrar_Frequencia(nomF);
                                if (ret == -1) ioPlus.print("La Freqüència " + nomF + " no existeix");
                                else ioPlus.print("Freqüència esborrada");
                                break;

                            case 3:
                                ioPlus.print("Modificant Frequencia");

                                nomF = Demanar_Nom("Freqüència");
                                if(!cd.ExisteixFreq(nomF)){
                                    System.out.println("No existeix la Freqüència " + nomF);
                                    break;
                                }
                                int opt3=-1;
                                while(opt3!=0){

                                    opt3 = Obtenir_Opcions_funcions(4,MENUAFFRQ,"modificar");
                                    switch (opt3){
                                        case 1: //opcio text d'un fitxer

                                            path = Demanar_Fitxer();

                                            if(!cd.ExisteixFitxer(path)){
                                                System.out.println("No existeix el fitxer que m'has passat amb path " + path);
                                                break;
                                            }

                                            ret = cd.Modificar_Freq_Path(nomF,path,1);

                                            if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son del seu Alfabet ");

                                            else ioPlus.print("Freqüencia " + nomF + " modificada");
                                            break;

                                        case 2:
                                            //opcio llista d'un fitxer

                                            path = Demanar_Fitxer(); //path
                                            if(!cd.ExisteixFitxer(path)){
                                                System.out.println("No existeix el fitxer que m'has passat amb path " + path);
                                                break;
                                            }

                                            ret = cd.Modificar_Freq_Path(nomF,path,0);

                                            if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son del seu Alfabet ");

                                            else ioPlus.print("Freqüencia " + nomF + " modificada");
                                            break;

                                        case 3:
                                            //opcio text a mà

                                            Vector<String> text = Demanar_text(false);//demanar text en forma normal

                                            ret = cd.Modificar_FreqMa(nomF,text,1);

                                            if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son de Alfabet ");

                                            else ioPlus.print("Freqüencia " + nomF + " modificada");
                                            break;

                                        case 4:
                                            //opcio llista a mà
                                            Vector<String> llista = Demanar_text(true);//text en forma de llista.

                                            ret = cd.Modificar_FreqMa(nomF,llista,0);

                                            if(ret==3)System.out.println("La nova Freqüència tindria caracters que no son del seu Alfabet ");

                                            else ioPlus.print("Freqüencia " + nomF + " modificada");
                                            break;
                                    }
                                }
                                break;
                            case 4:
                                ioPlus.print("Llistant Freqüències d'un Alfabet");
                                nomA = Demanar_Nom("Alfabet");
                                llistar_frequencies(cd.Consultar_Freqs(nomA));
                                break;
                        }
                        break;
                    case "Alfabet":
                        switch (option) {
                            case 1:
                                ioPlus.print("Afegint Alfabet");
                                nomA = Demanar_Nom("Alfabet");
                                if(cd.ExisteixAlf(nomA)){
                                    System.out.println("Ja existeix l'Alfabet " + nomA);
                                    llistar_alfabets(cd.Consultar_Alfabets());
                                    break;
                                }
                                HashSet<Character> h = Demanar_chars_Alfabet();
                                cd.Afegir_Alfabet(nomA, h);

                                ioPlus.print("Alfabet Afegit");
                                break;
                            case 2:
                                ioPlus.print("Esborrant Alfabet");
                                nomA  = Demanar_Nom("Alfabet");

                                if(!cd.ExisteixAlf(nomA)){
                                    System.out.println("No existeix l'Alfabet " + nomA);
                                    llistar_alfabets(cd.Consultar_Alfabets());
                                    break;
                                }

                                cd.Esborrar_Alfabet(nomA);

                                ioPlus.print("Alfabet Esborrat");
                                break;
                            case 3:
                                ioPlus.print("Canviant nom Alfabet");
                                nomA = Demanar_Nom("Alfabet");
                                ioPlus.print("Necessito el nou nom de l'Alfabet");
                                nomnewA = ioPlus.readWord();
                                ret = cd.CanviarNom_Alfabet(nomA, nomnewA);
                                if (ret == 1) ioPlus.print("L'Alfabet el nom " + nomA + " NO existeix");
                                else if (ret == 2) ioPlus.print("L'Alfabet el nom " + nomnewA + " JA existeix");
                                else ioPlus.print("Alfabet " + nomA + " ha canviat de nom a " + nomnewA);

                                break;
                            case 4:
                                ioPlus.print("Llistant Alfabets: ");
                                llistar_alfabets(cd.Consultar_Alfabets());
                                break;
                        }
                        break;
                    case "Grid":
                        switch (option) {
                            case 1:
                                ioPlus.print("Afegint Grid");
                                Integer x = Demanar_ID("Grid");
                                while(x<0){
                                    ioPlus.print("Ha de ser un enter positiu");
                                    x = Demanar_ID("Grid");
                                }
                                boolean[][] b = Demanar_mat_Grid();
                                if (b == null) {
                                    ioPlus.print("Format incorrecte");
                                    break;
                                }
                                ret = cd.Afegir_Grid(x,b);
                                if (ret == -1) ioPlus.print("El Grid " + x + " ja existeix");
                                else ioPlus.print("Grid afegit");
                                break;
                            case 2:
                                ioPlus.print("Esborrant Grid");
                                Integer x2 = Demanar_ID("Grid");
                                while(x2<0){
                                    ioPlus.print("Ha de ser un enter positiu");
                                    x2 = Demanar_ID("Grid");
                                }
                                ret = cd.Esborrar_Grid(x2);
                                if (ret == -1) ioPlus.print("El Grid número " + x2.toString() + " no existeix");
                                else ioPlus.print("Grid esborrat");
                                break;
                            case 3:
                                ioPlus.print("llistant grids");
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
        if(vvs.isEmpty()) ioPlus.print("no hi ha teclats per mostrar");
        for(Vector<String> vs : vvs){
            try {
                ioPlus.print("NOM: " + vs.get(0));
                ioPlus.print("ALFABET: " + vs.get(1));
                ioPlus.print("FREQÜÈNCIA: " + vs.get(2));
                ioPlus.print("GRID: " + vs.get(3));
                ioPlus.print("LAYOUT: ");
                ioPlus.print(vs.get(4));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void llistar_alfabets(Vector<Vector<String>> vvs){
        if(vvs.isEmpty()) ioPlus.print("no hi ha alfabets per mostrar");
        for(Vector<String> vs : vvs){
            try {
                ioPlus.print("NOM: " + vs.get(0));
                ioPlus.print("CARÀCTERS: " + vs.get(1));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void llistar_frequencies(Vector<Vector<String>> vvs){
        if(vvs.isEmpty()) ioPlus.print("no hi ha freqüències d'aquest alfabet per mostrar");
        for(Vector<String> vs : vvs){
            try {
                ioPlus.print("NOM: " + vs.get(0));
                ioPlus.print("Data de Creació: " + vs.get(1).substring(0,19));
                ioPlus.print("Data última modificació: " + vs.get(2).substring(0,19));
                ioPlus.print("Frequency weight: " + vs.get(3));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void llistar_grids(Vector<Vector<String>> vvs){
        if(vvs.isEmpty()) ioPlus.print("no hi ha grids per mostrar");
        for(Vector<String> vs : vvs){
            try {
                ioPlus.print("ID: " + vs.get(0));
                ioPlus.print("POSICIONS VÀLIDES:\n" + vs.get(1));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private Integer Demanar_ID(String nomclase) {
        int x = -1;
        try{
            ioPlus.print("Necessito la ID del " + nomclase);
            x = ioPlus.readInt();//nombre del alfabeto
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return x;
    }

    private String Demanar_Nom(String nomclase) throws Exception {
        String s1="";
        try{
            ioPlus.print("Necessito el nom del " + nomclase);
            s1 = ioPlus.readWord();//nombre del alfabeto

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return s1;
    }
    public Vector<String> Demanar_text(boolean mode) throws Exception {
        Vector<String> vs = new Vector<>();
        String s;
        try{
            if(mode) ioPlus.print("Escriu una llista de freq de la forma\n " +
                    " 600\n" +
                    "    a c 11\n" +
                    "    a v 15\n" +
                    "    a x 99\n " + " on 600 és ns la verda i la resta es per cada parell p.e " +
                    "a c quantes vegades apereix junta, en aquest exemple 11, acabat amb un espai i un '.' del que agafarem les frequencies");

            else ioPlus.print("Escriu el text acabat amb un espai i un '.' del que agafarem les frequencies");
            while(!(s = ioPlus.readWord()).equals(".")){
                vs.add(s);
            }

        }
        catch (Exception e){
           System.out.println(e.getMessage());
        }
        return vs;
    }
    //suposem que el fitxer té escrites bé les dades
    private String Demanar_Fitxer() {
        String s1="";
        try{
            ioPlus.print("Necessito el Path a un fitxer de text del que extraure les Freqüències , el fitxer ha de ser un text amb només paraules, " +
                    "amb cap caracter que no sigui del propi alfabet");
            s1 = ioPlus.readWord();//nombre del Path
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return s1;
    }

    //per a futur presentacio

    private String[][] Demanar_full_Alfabet() {
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

    private String[][] Demanar_full_Freq(){
        return cd.Consultar_Freq();
    }

    private HashSet<Character> Demanar_chars_Alfabet() {
        String s2="";
        HashSet<Character> car = new HashSet<>();
        try{
            ioPlus.print("Necesito un conjunt de caracters per l'alfabet");
            s2 = ioPlus.readWord();//caracteres del alfabeto
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        for(int i=0;i<s2.length();i++){
            car.add(s2.charAt(i));
        }
        return car;
    }
    private boolean[][] Demanar_mat_Grid()  {
        boolean[][] res = null;
        try {
            ioPlus.print("Indica l'alçada màxima del Grid:");
            int x = ioPlus.readInt();
            while(x<0){
                ioPlus.print("Ha de ser un enter positiu");
                x = ioPlus.readInt();
            }
            ioPlus.print("Indica l'amplada màxima del Grid:");
            int y = ioPlus.readInt();
            while(y<0){
                ioPlus.print("Ha de ser un enter positiu");
                y = ioPlus.readInt();
            }
            res = new boolean[x][y];
            ioPlus.print("Indica les posicions vàlides (1 = vàlid; 0 = no vàlid; separats per espais)");
            for (int i = 0; i < x; ++i) {
                for (int j = 0; j < y; ++j) {
                    int c = ioPlus.readInt();
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
    private ArrayList<String> Demanar_Freqs() {
        ioPlus.print("Indica les freqüències separades per espais, acabant amb un espai i un .");
        ArrayList<String> arrayF = new ArrayList<>();
        String nomF = ioPlus.readWord();
        while (!nomF.equals(".")) {
            arrayF.add(nomF);
            nomF = ioPlus.readWord();
        }
        return arrayF;
    }

    public void Next_Step_from_Menu_General() throws IOException {
        cd.Carregar_Dades();
        int opt=-1;
        while(opt!=0){
            int option=-1;
            opt = Obtenir_Opcions(5,MENUGENERAL);
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
                case 5:
                    cd.Guardar_Dades();
                    break;
            }
        }
    }

}
