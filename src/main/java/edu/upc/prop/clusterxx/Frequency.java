package edu.upc.prop.clusterxx;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.time.Instant;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
public class Frequency {

    final static int FREQ_MODE = 0;
    final static int TEXT_MODE = 1;

    final static int REPLACE = 2;
    final static int ADD = 3;

    private String name;
    private String [] words;
    private Instant creationDate;
    private Instant lastModifiedTime;
    private File f;

    HashMap<Character, HashMap<Character, Double>> freq;

    private boolean verifyCharacter(Character c) {
        return true;
    }

    private boolean checkFreqIntegrity() {
        return true;
    }

    private void insertNewFreq(Character first, Character second, Double frequency, int mode) {
        if (freq.containsKey(first)) {
            HashMap<Character, Double> innerHashMap = freq.get(first);

            if (mode == REPLACE) {
                innerHashMap.put(second, frequency);
            } else if (mode == ADD){
                if (innerHashMap.containsKey(second)) {
                    innerHashMap.put(second, innerHashMap.get(second) + frequency);
                }
            }
        } else {
            HashMap<Character, Double> innerHashMap = new HashMap<>();
            innerHashMap.put(second, frequency);
            freq.put(first, innerHashMap);
        }
    }


    public Frequency(String name, String [] words) {

    }

    public Frequency(String name, String filePath, int fileMode) throws FileNotFoundException {
        this.name = name;
        f = new File(filePath);
        freq = new HashMap<>();
        extractRawFrequenciesFromFile();

        creationDate = Instant.now();
    }

    /*
    file structure ->
    a c 0.2
    a v 0.1
    a x 0.6
     */
    private void extractRawFrequenciesFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            //System.out.println(str);
            String[] line = str.split(" ");
            Character first = line[0].charAt(0);
            Character second = line[1].charAt(0);
            Double d_freq = Double.parseDouble(line[2]);

            insertNewFreq(first, second, d_freq, REPLACE);
        }
    }

    private void processWord(String w) {
        return;
    }

    private void extractTextFrequenciesFromFile() throws FileNotFoundException {
        /*
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            String str = scanner.nex();
            //System.out.println(str);
            String[] line = str.split(" ");
            Character first = line[0].charAt(0);
            Character second = line[1].charAt(0);
            Double d_freq = Double.parseDouble(line[2]);

            insertNewFreq(first, second, d_freq);
        }*/
    }



    public void printFrequencies() {
        for (Map.Entry<Character, HashMap<Character, Double>> outerEntry : freq.entrySet()) {
            char outerKey = outerEntry.getKey();
            System.out.print(outerKey + ": {");

            HashMap<Character, Double> innerMap = outerEntry.getValue();
            for (Map.Entry<Character, Double> innerEntry : innerMap.entrySet()) {
                char innerKey = innerEntry.getKey();
                double innerValue = innerEntry.getValue();
                System.out.print(innerKey + "=" + innerValue + ", ");
            }

            if (!innerMap.isEmpty()) {
                System.out.print("\b\b");
            }

            System.out.println("}");
        }
    }
    public String getName() {
        return name;
    }

    public String [] words() {
        return words;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }



}
