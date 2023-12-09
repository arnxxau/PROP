package edu.upc.prop.clusterxx;

public class CtrlPresentacio {
    CtrlDomini cd;

    CtrlPresentacio(){
        cd = new CtrlDomini();
    }

    void iniciar_Mantenimiento_Teclados(){
        int option=-1; //opcion afegir teclat o esborrar o , ...
        while(option!=0){
            //llama a la vista para conseguir la opcion afegir, esborrar ...
            switch (option){
                case 0:
                    break;//ir a menu anterior(aka en este caso el menu general)
                case 1:
                    //afegir teclat
                    //pedir nombre
                    //
                    break;
                case 2:
                    //borrar
                    break;
                case 3:
                    //canviar nom teclat
                    break;
                case 4:
                    //actualitzar teclat
                    break;
                case 5:
                    //llistar teclats
                    break;
            }
        }
    }
    void iniciar_Mantenimiento_Frequencies(){
        int option=-1; //opcion afegir alfabet o esborrar o , ...
        while(option!=0){
            //llama a la vista para conseguir la opcion afegir, esborrar ...
            switch (option){
                case 0:
                    break;//ir a menu anterior(aka en este caso el menu general)
                case 1:
                    //afegir alfabet
                    //pedir nombre
                    //
                    break;
                case 2:
                    //borrar freq
                    break;
                case 3:
                    //mod freq
                    break;
                case 4:
                    //llistar freqs d'un alfabet
                    break;
            }
        }
    }
    void iniciar_Mantenimiento_Alfabets(){

        int option=-1; //opcion afegir alfabet o esborrar o , ...
        while(option!=0){
            //llama a la vista para conseguir la opcion afegir, esborrar ...
            switch (option){
                case 0:
                    break;//ir a menu anterior(aka en este caso el menu general)
                case 1:
                    //afegir alfabet
                    //pedir nombre
                    //
                    break;
                case 2:
                    //borrar
                    break;
                case 3:
                    //canviar nom alfabet
                    break;
                case 4:
                    //llistar alfabets
                    break;
            }
        }
    }
    void iniciar_Mantenimiento_Grids(){
        int option=-1; //opcion afegir alfabet o esborrar o , ...
        while(option!=0){
            //llama a la vista para conseguir la opcion afegir, esborrar ...
            switch (option){
                case 0:
                    break;//ir a menu anterior(aka en este caso el menu general)
                case 1:
                    //afegir grid
                    //pedir nombre
                    //
                    break;
                case 2:
                    //borrar
                    break;
                case 3:
                    //llistar grids
                    break;
            }
        }
    }
    void iniciar_mantenimiento(){
        int option = -1; //opcion mantenimiento general coger de la vista
        while(option!=0){
            //llama a la vista para conseguir la opcion afegir, esborrar ...
            switch(option){
                case 0://ir a menu anterior (aka en este caso acabar la ejecucion)
                    break;
                case 1://ir a menu teclados
                    iniciar_Mantenimiento_Teclados();
                    break;
                case 2://ir a menu teclados
                    iniciar_Mantenimiento_Frequencies();
                    break;
                case 3://ir a menu teclados
                    iniciar_Mantenimiento_Alfabets();
                    break;
                case 4://ir a menu teclados
                    iniciar_Mantenimiento_Grids();
                    break;
                case 5://ir a menu teclados
                    cd.Guardar_Dades();
                    break;
            }
        }

    }
}
