/**
 * La classe Frequency representa la freqüència de combinacions de caràcters en un context donat.
 * Es pot inicialitzar amb dades de freqüència en brut o amb dades de text, i proporciona mètodes
 * per actualitzar, fusionar amb una altra freqüència i imprimir la informació de freqüència.
 *
 * @author Arnau Granados
 * @version entrega 1.0
 */

package edu.upc.prop.clusterxx;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Frequency {
    // Main data
    private Integer frequencyWeight;
    private HashMap<Character, HashMap<Character, Integer>> freq;

    // Read modes
    private int mode;
    /**
     * Constant que representa el mode d'extracció de freqüències, utilitzat
     * quan es construeix la instància de la classe Frequency amb dades de freqüències en brut.
     */
    final static int FREQ_MODE = 0;
    /**
     * Constant que representa el mode d'extracció de freqüències, utilitzat
     * quan es construeix la instància de la classe Frequency amb dades de freqüències de text.
     */
    final static int TEXT_MODE = 1;

    // Insertion modes
    private final static int REPLACE = 3, ADD = 4;

    // Error messages
    private final static String EXTRACTION_ERROR = "An error occurred, the frequency couldn't be extracted";
    private final static String WRONG_FILE_PATH_ERROR = "Wrong file path.";
    private final static String ALPHABET_ERROR = "The alphabet is not compatible with this frequency.";
    private final static String NON_EXISTING_FREQUENCY = "The character combination doesn't exist.";

    // General info
    private String name;
    private final Instant creationDate;
    private Instant lastModifiedTime;
    private Alphabet alphabet;


    // constructors

    /**
     * Construeix un objecte Frequency basat en els paràmetres proporcionats.
     *
     * @param name     El nom de la freqüència.
     * @param lines    Les línies de dades que contenen informació de freqüència.
     * @param mode     El mode que indica si les dades estan en forma de freqüència o text. (FREQ_MODE o TEXT_MODE)
     * @param alphabet L'alfabet associat amb la freqüència.
     * @throws Exception Si hi ha un error durant la construcció.
     */
    public Frequency(String name, String[] lines, int mode, Alphabet alphabet) throws Exception {
        this.mode = mode;
        this.name = name;
        this.alphabet = alphabet;
        freq = new HashMap<>();
        frequencyWeight = 0;
        if (mode == FREQ_MODE) extractRawFrequencies(lines);
        else if (mode == TEXT_MODE) extractTextFrequencies(lines);

        creationDate = Instant.now();
        lastModifiedTime = Instant.now();
    }


    // extractors

    /**
     * Extreu les freqüències en brut dels continguts de les línies proporcionades.
     *
     * <p>La estructura del fitxer esperada és:</p>
     * <pre>
     *     600
     *     a c 11
     *     a v 15
     *     a x 99
     * </pre>
     *
     * @param lines Les línies que contenen la informació de freqüències.
     * @throws Exception Si el fitxer conté caràcters que no estan presents a l'alfabet de la freqüència.
     */
    private void extractRawFrequencies(String[] lines) throws Exception {
        frequencyWeight = Integer.parseInt(lines[0]);
        for (int i = 1; i < lines.length; ++i) {
            String[] line = lines[i].split(" ");
            Character first = line[0].charAt(0);
            Character second = line[1].charAt(0);
            checkFreqIntegrity(first);
            checkFreqIntegrity(second);

            Integer d_freq = Integer.parseInt(line[2]);

            insertNewFreq(first, second, d_freq, REPLACE);
        }

    }

    /**
     * Extreu les freqüències de text de les línies proporcionades.
     *
     * @param lines Línies de text amb el mateix alfabet.
     * @throws Exception Si el fitxer conté caràcters que no estan presents a l'alfabet de la freqüència.
     */
    private void extractTextFrequencies(String[] lines) throws Exception {
        for (String s1 : lines) {
            String[] line = s1.split(" ");

            for (String s2 : line) {
                processWord(s2);
                frequencyWeight += s2.length();
            }
        }

    }

    // utils

    /**
     * Processa una paraula per actualitzar les freqüències de caràcters adjacents.
     *
     * @param w La paraula a processar.
     * @throws Exception Si la paraula caràcters que no estan presents a l'alfabet de la freqüència.
     */
    private void processWord(String w) throws Exception {
        for (int i = 0; i < w.length() - 1; ++i) {
            checkFreqIntegrity(w.charAt(i));
            checkFreqIntegrity(w.charAt(i + 1));
            insertNewFreq(w.charAt(i), w.charAt(i + 1), 1, ADD);
            insertNewFreq(w.charAt(i + 1), w.charAt(i), 1, ADD);
        }
    }


    /**
     * Insereix o reemplaça una freqüència de caràcters adjacents en el mapa de freqüències.
     *
     * @param first     El primer caràcter de la combinació.
     * @param second    El segon caràcter de la combinació.
     * @param frequency La freqüència a inserir o reemplaçar.
     * @param mode      El mode que indica si és una inserció o un reemplaçament.
     */
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

    /**
     * Actualitza la freqüència basant-se en noves línies de dades.
     *
     * @param lines Les línies de dades que contenen informació de freqüència.
     * @throws Exception Si hi ha un error durant el procés d'actualització.
     */
    public void updateFrequency(String[] lines) throws Exception {
        frequencyWeight = 0;
        freq = new HashMap<>();
        if (mode == FREQ_MODE) extractRawFrequencies(lines);
        else if (mode == TEXT_MODE) extractTextFrequencies(lines);
        lastModifiedTime = Instant.now();
    }
    public void modifyFrequency(String[] lines, int mode) throws Exception {
        frequencyWeight = 0;
        freq = new HashMap<>();
        this.mode = mode;
        if (mode == FREQ_MODE) extractRawFrequencies(lines);
        else if (mode == TEXT_MODE) extractTextFrequencies(lines);
        lastModifiedTime = Instant.now();
    }

    /**
     * Fusiona la freqüència actual amb una altra freqüència, actualitzant la informació de freqüència.
     *
     * @param f L'objecte Frequency amb el qual fusionar.
     * @throws Exception Si els alfabets de les freqüències no coincideixen.
     */
    public void fusion(Frequency f) throws Exception {
        if (f.alphabet != this.alphabet)
            throw new Exception(ALPHABET_ERROR);

        Iterator<Character> it1 = alphabet.getCharacters().iterator();
        Iterator<Character> it2 = alphabet.getCharacters().iterator();
        HashMap<Character, HashMap<Character, Integer>> freqcopy1 = new HashMap<>(freq);
        HashMap<Character, HashMap<Character, Integer>> freqcopy2 = new HashMap<>(f.freq);

        frequencyWeight += f.frequencyWeight;

        freq = new HashMap<>();

        while (it1.hasNext()) {
            Character c1 = it1.next();
            while (it2.hasNext()) {
                Character c2 = it2.next();

                if (freqcopy1.containsKey(c1) && freqcopy1.get(c1).containsKey(c2))
                    insertNewFreq(c1, c2, freqcopy1.get(c1).get(c2), ADD);
                if (freqcopy2.containsKey(c1) && freqcopy2.get(c1).containsKey(c2))
                    insertNewFreq(c1, c2, freqcopy2.get(c1).get(c2), ADD);
            }
            it2 = alphabet.getCharacters().iterator();
        }
    }


    /**
     * Imprimeix la informació de freqüència a la consola.
     */
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

    /**
     * Obté el pes de la freqüència, que representa el nombre total de combinacions de caràcters.
     *
     * @return El pes de la freqüència.
     */
    public Integer getFrequencyWeight() {
        return frequencyWeight;
    }

    /**
     * Obté les dades de freqüència, un mapa de combinacions de caràcters i les seves freqüències respectives.
     *
     * @return Les dades de freqüència.
     */
    public HashMap<Character, HashMap<Character, Integer>> getFreq() {
        return freq;
    }

    /**
     * Obté el mode de la freqüència, indicant si es troba en mode de freqüència o text.
     *
     * @return El mode de la freqüència.
     */
    public int getMode() {
        return mode;
    }


    /**
     * Obté el nom de la freqüència.
     *
     * @return El nom de la freqüència.
     */
    public String getName() {
        return name;
    }

    /**
     * Obté la data de creació de la freqüència.
     *
     * @return La data de creació.
     */
    public Instant getCreationDate() {
        return creationDate;
    }

    /**
     * Obté el temps de l'última modificació de la freqüència.
     *
     * @return El temps de l'última modificació.
     */
    public Instant getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * Obté l'alfabet associat amb la freqüència.
     *
     * @return L'alfabet.
     */
    public Alphabet getAlphabet() {
        return alphabet;
    }

    /**
     * Obté el nombre d'aparicions d'una combinació de caràcters a la freqüència.
     *
     * @param first  El primer caràcter de la combinació.
     * @param second El segon caràcter de la combinació.
     * @return El nombre d'aparicions de la combinació de caràcters.
     */
    public double getNumberOfAppearances(Character first, Character second) {
        if (freq.containsKey(first)) {
            HashMap<Character, Integer> innerHash = freq.get(first);
            if (innerHash.containsKey(second)) {
                return innerHash.get(second);
            }
        }
        return 0;
    }

    /**
     * Obté la freqüència d'una combinació de caràcters a les dades de freqüència.
     *
     * @param first  El primer caràcter de la combinació.
     * @param second El segon caràcter de la combinació.
     * @return La freqüència de la combinació de caràcters.
     */
    public double getFrequency(Character first, Character second) {
        return getNumberOfAppearances(first, second) / frequencyWeight;
    }


    // public setters

    /**
     * Estableix el pes de la freqüència al valor especificat.
     *
     * @param frequencyWeight El nou pes de la freqüència.
     */
    public void setFrequencyWeight(Integer frequencyWeight) {
        this.frequencyWeight = frequencyWeight;
    }

    /**
     * Estableix les dades de freqüència al mapa especificat.
     *
     * @param freq Les noves dades de freqüència.
     */
    public void setFreq(HashMap<Character, HashMap<Character, Integer>> freq) {
        this.freq = freq;
    }

    /**
     * Estableix el mode de la freqüència al valor especificat.
     *
     * @param mode El nou mode de la freqüència.
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * Estableix el nom de la freqüència al valor especificat.
     *
     * @param name El nou nom de la freqüència.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Estableix el temps de l'última modificació a l'Instant especificat.
     *
     * @param lastModifiedTime El nou temps de l'última modificació.
     */
    public void setLastModifiedTime(Instant lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * Estableix l'alfabet associat amb la freqüència a l'alfabet especificat.
     *
     * @param alphabet El nou alfabet.
     */
    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }


}
