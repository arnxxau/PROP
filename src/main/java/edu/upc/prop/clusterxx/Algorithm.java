package edu.upc.prop.clusterxx;

import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Algorithm{

    public Algorithm(){}

    //Pre: El grid i l'alphabet han de tenir el mateix numero de simbols/posicons
    public char[] Gilmore(Alphabet alphabet, Frequency frequency, Grid grid){

        ArrayList<pair<char,int>> Solucio = new ArrayList<pair<char,int>>(); //Es guardaran els simbols amb posicio ja assignada (simbols,identificador de posicio)

        HashSet<char> Simbols = alphabet.getSimbols();

        HashSet<int> Posicions = grid.getPosicions();

        while(!Simbols.isEmpty()){

            int size = Simbols.size();
            double[][] Mat = double[size][size];
            int cont=0;
            char sim_act;
            int pos_act;

            Iterator<char> iterator_simb = Simbols.iteratior();
            for(int i=0; i<size; i++){
                sim_act = iterator_simb.next();

                Iterator<int> iterator_pos = Posicions.iteratior();
                for(int k=0; k<size; k++){
                    pos_act=iterator_pos.next();


                    double total=0;
                    //calculo C1
                    for (pair<char,int> element : Solucio) {
                        total += (frequency.getFrequency(element.getKey(),sim_act) * grid.getDistance(element.getValue(),pos_act));
                    }
                    Mat[i][k]=total;

                    //calculo C2
                    double[] T = double[size-1];
                    double[] D = double[size-1];

                    //calculo vecto T y vector D
                    //T
                    int indx=0;
                    Iterator<char> iter_sim_ext = Simbols.iteratior();
                    char ext;
                    while(iter_sim_ext.hasNext()) {
                        ext=iter_sim_ext.next();
                        if(ext!=sim_act){
                            T[indx]=frequency.getFrequency(ext,sim_act);
                            indx +=1;
                        }
                    }
                    //D
                    indx=0;
                    Iterator<int> iter_pos_ext = Posicions.iteratior();
                    int pos;
                    while(iter_pos_ext.hasNext()){
                        pos=iter_pos_ext.next();
                        if(pos!=pos_act){
                            D[indx]=grid.getDistance(pos,pos_act);
                        }
                    }

                    //Ordenar T Creixent
                    Arrays.sort(T);

                    //Ordenar D Decreixent
                    Arrays.sort(D, Comparator.reverseOrder());


                    //Producte escalar T*D
                    total=0;
                    for(int i=0;i<(size-1);i++){
                        total=T[i]*D[i];
                    }


                    Mat[i][k]+=total;

                }
            }


        }


}