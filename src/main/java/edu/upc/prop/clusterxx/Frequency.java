package edu.upc.prop.clusterxx;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

public class Frequency {
    // main data
    private Integer frequencyWeight;
    private HashMap<Character, HashMap<Character, Integer>> freq;

    // read modes
    private int MODE;
    final static int FREQ_MODE = 0;
    final static int TEXT_MODE = 1;
    final static int WORD_MODE = 2;
    // insertion modes
    private final static int REPLACE = 3, ADD = 4;

    // error messages
    private final static String EXTRACTION_ERROR = "An error occurred, the frequency couldn't be extracted";
    private final static String WRONG_FILE_PATH_ERROR = "Wrong file path.";

    private final static String ALPHABET_ERROR = "The alphabet is not compatible with this frequency.";
    private final static String NON_EXISTING_FREQUENCY = "The character combination doesn't exist.";

    // general info
    private String name;
    private Instant creationDate, lastModifiedTime;
    private File f;
    private Alphabet alphabet;


    // constructors
    public Frequency(String name, String[] words, fAlphabet alphabet) {
        this.name = name;
        this.MODE = WORD_MODE;
        freq = new HashMap<>();
        extractFrequenciesFromWords(words);
    }

    public Frequency(String name, String filePath, int fileMode) throws FileNotFoundException {
        this.MODE = fileMode;
        this.name = name;
        try {
            freq = new HashMap<>();
            frequencyWeight = 0;
            if (fileMode == FREQ_MODE) extractRawFrequenciesFromFile();
            else if (fileMode == TEXT_MODE) extractTextFrequenciesFromFile();
            f = new File(filePath);
        } catch (NullPointerException e) {
            System.out.println(WRONG_FILE_PATH_ERROR);
        }

        creationDate = Instant.now();
    }


    // extractors
    private void extractFrequenciesFromWords(String[] words) {
        try {
            for (String word : words) {
                processWord(word);
                frequencyWeight += word.length();
            }
        } catch (Exception e) {
            System.out.println(EXTRACTION_ERROR);
        }

    }

    /*
    file structure ->
    600
    a c 11
    a v 15
    a x 99
     */
    private void extractRawFrequenciesFromFile() {
        try {
            Scanner scanner = new Scanner(f);
            frequencyWeight = Integer.parseInt(scanner.nextLine());
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String[] line = str.split(" ");
                Character first = line[0].charAt(0);
                Character second = line[1].charAt(0);
                checkFreqIntegrity(first);
                checkFreqIntegrity(second);

                Integer d_freq = Integer.parseInt(line[2]);

                insertNewFreq(first, second, d_freq, REPLACE);
            }
        }
        catch (Exception e) {
            System.out.println(EXTRACTION_ERROR);
        }
    }

    private void extractTextFrequenciesFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            String str = scanner.next();
            String[] line = str.split(" ");
            try {
                for (String s : line) {
                    processWord(s);
                    frequencyWeight += s.length();
                }
            } catch (Exception e) {
                System.out.println(EXTRACTION_ERROR);
            }
        }
    }


    // utils
    private void processWord(String w) throws Exception{
        for (int i = 0; i < w.length() - 1; ++i) {
            checkFreqIntegrity(w.charAt(i));
            checkFreqIntegrity(w.charAt(i + 1));
            insertNewFreq(w.charAt(i), w.charAt(i + 1), 1, ADD);
            insertNewFreq(w.charAt(i + 1), w.charAt(i), 1, ADD);
        }
    }

    private void insertNewFreq(Character first, Character second, Integer frequency, int mode) {
        if (freq.containsKey(first)) {
            HashMap<Character, Integer> innerHashMap = freq.get(first);

            if (mode == REPLACE) innerHashMap.put(second, frequency);
            else if (mode == ADD) {
                if (innerHashMap.containsKey(second))
                    innerHashMap.put(second, innerHashMap.get(second) + frequency);
                else innerHashMap.put(second, frequency);
            }
        } else {
            HashMap<Character, Integer> innerHashMap = new HashMap<>();
            innerHashMap.put(second, frequency);
            freq.put(first, innerHashMap);
        }
    }

    public void fusion(Frequency f) {


        try {
            if (f.alphabet != this.alphabet)
                throw new Exception(ALPHABET_ERROR);



            Iterator<Character> it1 = alphabet.getCharacters().iterator();
            Iterator<Character> it2 = alphabet.getCharacters().iterator();
            HashMap<Character, HashMap<Character, Integer>> freqcopy1 = new HashMap<>(freq);
            HashMap<Character, HashMap<Character, Integer>> freqcopy2 = new HashMap<>(freq);
            freq = new HashMap<>();

            while (it1.hasNext()) {
                Character c1 = it1.next();
                while (it2.hasNext()) {
                    Character c2 = it2.next();


                    if (freqcopy1.containsKey(c1))
                }

            }




            for (Map.Entry<Character, HashMap<Character, Integer>> outerEntry : freq.entrySet()) {
                char outerKey = outerEntry.getKey();

                HashMap<Character, Integer> innerMap = outerEntry.getValue();
                for (Map.Entry<Character, Integer> innerEntry : innerMap.entrySet()) {
                    char innerKey = innerEntry.getKey();
                    double innerValue = innerEntry.getValue();
                }

            }



        } catch (Exception e) {
            System.out.println(ALPHABET_ERROR);
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


    // integrity checkers
    private void checkFreqIntegrity(Character c) throws Exception {
        if (!alphabet.existsCharacter(c))
            throw new Exception(ALPHABET_ERROR);
    }

    // public getters
    public String getName() {
        return name;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getLastModifiedTime() {
        return lastModifiedTime;
    }

    public double getNumberOfAppearances(Character first, Character second) {
        try {
            return (double) freq.get(first).get(second);
        } catch (Exception e) {
            System.out.println(NON_EXISTING_FREQUENCY);
            return -1.0;
        }
    }

    public double getFrequency(Character first, Character second) {
        return getNumberOfAppearances(first, second) / frequencyWeight;
    }

    // public setters
    public void changeName(String name) {
        this.name = name;
    }

}
