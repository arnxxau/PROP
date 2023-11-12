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

    private Integer frequencyWeight;

    HashMap<Character, HashMap<Character, Integer>> freq;

    private boolean verifyCharacter(Character c) {
        return true;
    }

    private boolean checkFreqIntegrity() {
        return true;
    }

    private void insertNewFreq(Character first, Character second, Integer frequency, int mode) {
        if (freq.containsKey(first)) {
            HashMap<Character, Integer> innerHashMap = freq.get(first);

            if (mode == REPLACE) {
                innerHashMap.put(second, frequency);
            } else if (mode == ADD){
                if (innerHashMap.containsKey(second)) {
                    innerHashMap.put(second, innerHashMap.get(second) + frequency);
                } else {
                    innerHashMap.put(second, frequency);
                }
            }
        } else {
            HashMap<Character, Integer> innerHashMap = new HashMap<>();
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
        frequencyWeight = 0;

        if (fileMode == FREQ_MODE) extractRawFrequenciesFromFile();
        else if (fileMode == TEXT_MODE) extractTextFrequenciesFromFile();


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
        frequencyWeight = Integer.parseInt(scanner.nextLine());
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            //System.out.println(str);
            String[] line = str.split(" ");
            Character first = line[0].charAt(0);
            Character second = line[1].charAt(0);
            Integer d_freq = Integer.parseInt(line[2]);

            insertNewFreq(first, second, d_freq, REPLACE);
        }
    }

    private void processWord(String w) {
        for (int i = 0; i < w.length() - 1; ++i) {
            insertNewFreq(w.charAt(i), w.charAt(i + 1), 1, ADD);
            insertNewFreq(w.charAt(i + 1), w.charAt(i), 1, ADD);
        }
        return;
    }

    private void extractTextFrequenciesFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            String str = scanner.next();

            String[] line = str.split(" ");

            frequencyWeight += line.length;

            for (int i = 0; i < line.length; ++i) processWord(line[i]);
        }
    }



    public void printFrequencies() {
        System.out.println("Freq weight = " + frequencyWeight);
        for (Map.Entry<Character, HashMap<Character, Integer>> outerEntry : freq.entrySet()) {
            char outerKey = outerEntry.getKey();
            System.out.print(outerKey + ": {");

            HashMap<Character, Integer> innerMap = outerEntry.getValue();
            for (Map.Entry<Character, Integer> innerEntry : innerMap.entrySet()) {
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

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getLastModifiedTime() {
        return lastModifiedTime;
    }



}
