package edu.upc.prop.clusterxx;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello world!");
    //new Gson();

    //Main divisioner = new Main();
    //System.out.println("Dividing 10 by 2 is " + divisioner.division(10,2));

    //Frequency freq = new Frequency("test", "/home/akira/IdeaProjects/subgrup-prop12.5/src/main/java/edu/upc/prop/clusterxx/raw.freq", 0);
    //freq.printFrequencies();

    List<String> lines = Files.readAllLines(Path.of("/home/akira/IdeaProjects/subgrup-prop12.5/src/main/java/edu/upc/prop/clusterxx/text.freq"), StandardCharsets.UTF_8);
    String[] arr = lines.toArray(new String[0]);
    Alphabet a = null;


    Frequency freq2 = new Frequency("test", arr, Frequency.TEXT_MODE, a);
    freq2.printFrequencies();
    System.out.println(freq2.getFrequency('a', 'p'));
  }

  public float division(int a, int b) throws ArithmeticException {
    return a/b;
  }
}