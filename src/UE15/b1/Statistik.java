package UE15.b1;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Statistic class
 *
 * @author fabioanzola
 */
public class Statistik {

    /**
     * Stores first-names
     */
    Set<String> names;

    /**
     * Stores all birthdays
     */
    List<String> birthday;

    /**
     * Stores shortened birthdays
     */
    Set<String> shortDates;

    /**
     * The constructor
     *
     * @param firstn Path to name File
     * @param birthd Path to birthday File
     * @throws Exception If an error occurs
     */
    public Statistik(Path firstn, Path birthd) throws Exception {
        names = readFirstNames(firstn);
        birthday = readFirstDatesList(birthd);
        shortDates = readFirstDates(birthd);

    }

    /**
     * Checks if list contains certain name
     *
     * @param firstName String for name if exists
     * @return If name exists
     */
    boolean hasFirstName(String firstName) {
        return names.contains(firstName);
    }

    /**
     * Returns how many pupils have the same birthdays
     *
     * @return Int How many pupils have the same birthdays
     */
    int countPupilsWithDoubleBirthdays() {
        Set<String> trySet = new HashSet<>();
        Set<String> doubleBirthdays = new HashSet<>();
        for (String birthday : this.birthday) {
            String s = birthday.substring(0, 5);
            if (!(trySet.add(s))) {
                doubleBirthdays.add(s);
            }
        }
        int lenList = birthday.size(); //all
        int lenSet = shortDates.size(); //shortened
        int lenDouble = doubleBirthdays.size(); //doubles
        return (lenList - lenSet) + lenDouble;
    }

    /**
     * Reads all lines of file
     *
     * @param file Path of the input file
     * @return The String List
     * @throws Exception If an error occurs
     */
    List<String> readLines(Path file) throws Exception {
        List<String> result;
        try (Stream<String> lines = Files.lines(file)) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }

    /**
     * Reads the files to Map
     *
     * @param n Path of the input file (names)
     * @param b Path of the input file (birthday)
     * @return Returns the Map
     * @throws Exception If an error occurs
     */
    Map<String, String> readToMap(Path n, Path b) throws Exception {
        //name;birthday
        Map<String, String> m = new HashMap<String, String>();
        try (
                BufferedReader inN = Files.newBufferedReader(n, StandardCharsets.UTF_8);
                BufferedReader inB = Files.newBufferedReader(b, StandardCharsets.UTF_8)

        ) {
            String lineN;
            String lineB;

            while ((lineN = inN.readLine()) != null) {
                lineB = inB.readLine();
                m.put(lineN.split(" ")[0], lineB);
            }
        }
        return m;
    }

    /**
     * Reads the first name of the line (file)
     *
     * @param file Oath to the input file
     * @return The read set
     * @throws Exception If an error occurs
     */
    Set<String> readFirstNames(Path file) throws Exception {
        Set<String> result = new HashSet<>();
        try (
                BufferedReader in = Files.newBufferedReader(file, StandardCharsets.UTF_8)

        ) {
            String line;

            while ((line = in.readLine()) != null) {
                result.add(line.split(" ")[0]);
            }
        }
        return result;
    }

    /**
     * Reads the first date of the line (file)
     *
     * @param file Oath to the input file
     * @return The read set
     * @throws Exception If an error occurs
     */
    Set<String> readFirstDates(Path file) throws Exception {
        Set<String> result = new HashSet<>();
        try (
                BufferedReader in = Files.newBufferedReader(file, StandardCharsets.UTF_8)

        ) {
            String line;

            while ((line = in.readLine()) != null) {
                result.add(line.split("[.]")[0] + line.split("[.]")[1]);
            }
        }
        return result;
    }

    /**
     * Reads the first name of the line (file)
     *
     * @param file Oath to the input file
     * @return The read list
     * @throws Exception If an error occurs
     */
    List<String> readFirstDatesList(Path file) throws Exception {
        List<String> result = new LinkedList<String>();
        try (
                BufferedReader in = Files.newBufferedReader(file, StandardCharsets.UTF_8)

        ) {
            String line;

            while ((line = in.readLine()) != null) {
                result.add(line.split("[.]")[0] + '.' + line.split("[.]")[1]);
            }
        }
        return result;
    }

}
