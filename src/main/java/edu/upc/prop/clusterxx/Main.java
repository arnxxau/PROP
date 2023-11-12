package edu.upc.prop.clusterxx;

import com.google.gson.Gson;

import java.io.FileNotFoundException;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    //new Gson();

    //Main divisioner = new Main();
    //System.out.println("Dividing 10 by 2 is " + divisioner.division(10,2));

    Frequency freq = new Frequency("test", "/Users/arnxxau/FIB/subgrup-prop12.5/src/main/java/edu/upc/prop/clusterxx/test.txt", 0);
    freq.printFrequencies();
  }

  public float division(int a, int b) throws ArithmeticException {
    return a/b;
  }
}