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

    public Driver_FP(){}

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
            //io.writeln("5 - Gestionar Alfabet ");
             }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void Vista_Frequencies() {
        try{io.writeln("Menu Teclats: ");
            io.writeln("0 - Sortir ");
            io.writeln("1 - Afegir Frequencia ");
            io.writeln("2 - Esborrar Frequencia ");
            io.writeln("3 - Modificar Frequencia ");
            //io.writeln("4 - Actualitzar Frequencia ");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void Vista_Alfabets() {
        try{io.writeln("Menu Teclats: ");
            io.writeln("0 - Sortir ");
            io.writeln("1 - Afegir Alfabet ");
            io.writeln("2 - Esborrar Alfabet");
            io.writeln("3 - Canviar Nom Alfabet ");
            io.writeln("4 - Llistar Alfabets ");
            //io.writeln("4 - Gestionar Frequencies ");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
//hem de mirar si has tret un alfabet per exemple, quan accedim al teclat mirar ,que encara que estigui creat, no tingui un alfabet apuntant que no existeix.
    private void Vista_Grids() {
        try{io.writeln("Menu Teclats: ");
            io.writeln("0 - Sortir ");
            io.writeln("1 - Afegir Grid ");
            io.writeln("2 - Esborrar Grid ");
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
                opt = io.readint();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return opt;
    }

    //pre : nomclase ha de ser el nom d'una classe de model que existeixi
    //se puede hacer mejor esta funcion.(se repiten acciones entre clases. con nomclase y tal)
    private void Opcions_de_Clase(String nomclase,int option){
        if(option!=0){
            switch (nomclase){
                case "Teclat":
                    switch (option){
                        case 1:
                            System.out.println("Afegint Teclat");
                            //cd.Afegir_Teclat()
                            break;
                        case 2:
                            System.out.println("Esborrant Teclat");
                            break;
                        case 3:
                            System.out.println("Canviant nom Teclat");
                            break;
                        case 4:
                            System.out.println("Actualitzant Teclat");
                            break;
                    }
                    break;
                case "Frequencia":
                    switch (option){
                        case 1:
                            System.out.println("Afegint Frequencia");
                            break;
                        case 2:
                            System.out.println("Esborrant Frequencia");
                            break;
                        case 3:
                            System.out.println("Modificar Frequencia");
                            break;

                    }
                    break;
                case "Alfabet":
                    switch (option){
                        case 1:
                            System.out.println("Afegint Alfabet");
                            String s = Demanar_Nom("Alfabet");
                            HashSet<Character> h = Demanar_chars_Alfabet();
                            int ret = cd.Afegir_Alfabet(s,h);
                            if(ret==1)System.out.println("L'Alfabet amb aquest nom ja existeix");
                            else System.out.println("Alfabet Afegit");
                            System.out.println();
                            //llistar_alfabets(cd.Consultar_Alfabets()); aqui aniria mostrar_alfabet
                            break;
                        case 2:
                            System.out.println("Esborrant Alfabet");
                            String s1 = Demanar_Nom("Alfabet");
                            ret = cd.Esborrar_Alfabet(s1);
                            if(ret==1)System.out.println("L'Alfabet amb aquest nom NO existeix");
                            else System.out.println("Alfabet Esborrat");
                            System.out.println();
                            break;
                        case 3:
                            System.out.println("Canviant nom Alfabet");
                            String s2 = Demanar_Nom("Alfabet");
                            String s3 = Demanar_Nom("nou Alfabet");
                            ret = cd.CanviarNom_Alfabet(s2,s3);
                            if(ret==1)System.out.println("L'Alfabet el nom " + s2 + " NO existeix");
                            else if(ret==2)System.out.println("L'Alfabet el nom " + s3 + " JA existeix");
                            else System.out.println("Alfabet " + s2 + " ha canviat de nom a " + s3);
                            System.out.println();
                            break;
                        case 4:
                            System.out.println("Llistant Alfabets: ");
                            System.out.println();
                            llistar_alfabets(cd.Consultar_Alfabets());
                            break;
                    }
                    break;
                case "Grid":
                    switch (option){
                        case 1:
                            System.out.println("Afegint Grid");
                            break;
                        case 2:
                            System.out.println("Esborrant Grid");
                            break;
                    }
                    break;
            }
        }
    }

    private void llistar_alfabets(Vector<Vector<String>> vvs){
        for(Vector<String> vs : vvs){
            System.out.println("NOM: " + vs.get(0));
            System.out.println("CARACTERS: " + vs.get(1));
            System.out.println();
        }
    }

    private String Demanar_Nom(String nomclase){
        String s1="";
        try{
            io.writeln("Necessito el nom del " + nomclase);
            s1 = s.nextLine();//nombre del alfabeto
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
            s2 = s.nextLine();//caracteres del alfabeto
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        for(int i=0;i<s2.length();i++){
            car.add(s2.charAt(i));
        }
        return car;
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
                        option = Obtenir_Opcions(4,MENUTECLAT);
                        Opcions_de_Clase("Teclat",option);
                    }
                    break;
                case 2:
                    while(option!=0){
                        option = Obtenir_Opcions(3,MENUFREQUENCIA);
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
                        option = Obtenir_Opcions(2,MENUGRID);
                        Opcions_de_Clase("Grid",option);
                    }
                    break;
            }
        }
    }

}
