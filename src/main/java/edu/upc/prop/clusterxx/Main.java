package edu.upc.prop.clusterxx;

import com.google.gson.Gson;

import java.io.FileNotFoundException;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    CtrlPresentacio cp = new CtrlPresentacio();
    cp.iniciarmanteniment();
    //System.out.println("Hello world!");
    //new Gson();

    //Main divisioner = new Main();
    //System.out.println("Dividing 10 by 2 is " + divisioner.division(10,2));

    //Frequency freq = new Frequency("test", "/home/akira/IdeaProjects/subgrup-prop12.5/src/main/java/edu/upc/prop/clusterxx/raw.freq", 0);
    //freq.printFrequencies();


    /*Frequency freq2 = new Frequency("test", "/Users/arnxxau/FIB/subgrup-prop12.5/src/main/java/edu/upc/prop/clusterxx/text.freq", Frequency.TEXT_MODE);
    freq2.printFrequencies();
    System.out.println(freq2.getFrequency('a', 'c'));*/
  }

  public float division(int a, int b) throws ArithmeticException {
    return a/b;
  }
}