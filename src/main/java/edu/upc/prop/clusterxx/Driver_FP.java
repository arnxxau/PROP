package edu.upc.prop.clusterxx;

public class Driver_FP {
    inout io = new inout();

    static final int MENUGENERAL=0;
    static final int MENUTECLAT=1;
    static final int MENUFREQUENCIA=2;
    static final int MENUALFABET=3;
    static final int MENUGRID=4;

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
    private void Opcions_de_Clase(String nomclase,int option){
        if(option!=0){
            switch (nomclase){
                case "Teclat":
                    switch (option){
                        case 1:
                            System.out.println("Afegint Teclat");
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
                            break;
                        case 2:
                            System.out.println("Esborrant Alfabet");
                            break;
                        case 3:
                            System.out.println("Canviant nom Alfabet");
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

    public void Next_Step_from_Menu_General(){
        int opt=-1;
        int option=-1;
        while(opt!=0){
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
                        option = Obtenir_Opcions(3,MENUALFABET);
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
