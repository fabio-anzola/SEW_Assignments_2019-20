package UE12.ue12_textio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author fabioanzola
 */
public class StudentStatistic {

    /**
     * Parameter for the file-path
     */
    private String filePath;

    /**
     * Parameter for the contents of the csv
     */
    private String[][] statistic;


    /**
     * The constructor
     *
     * @param file String
     * @throws IOException if an error occurs
     */
    public StudentStatistic(String file) throws IOException {
        filePath = file;

        read();
    }

    /**
     * Reads the file given and stores it into the array
     *
     * @throws IOException if an error occurs
     */
    private void read() throws IOException {
        String[][] file;
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(this.filePath), StandardCharsets.UTF_8)
        ) {
            file = new String[countLines()][5];
            String line;
            int i = 0;
            while ((line = in.readLine()) != null) {
                String[] tmpStore = line.split(";");
                file[i] = tmpStore;
                i++;
            }
            //System.out.println(Arrays.deepToString(file));
        }
        statistic = file;
    }

    /**
     * Saves the given file in the given format
     *
     * @param destFile String
     * @param chSet    String
     * @throws IOException if an error occurs
     */
    public void save(String destFile, String chSet) throws IOException {
        try (
                BufferedWriter out = Files.newBufferedWriter(Paths.get(destFile), Charset.forName(chSet))
        ) {
            for (int i = 0; i < statistic.length; i++) {
                for (int j = 0; j < statistic[0].length; j++) {
                    out.write(statistic[i][j]);
                    if (j != statistic[0].length - 1) {
                        out.write(";");
                    }
                }
                out.write(System.lineSeparator());
            }
        }
    }

    /**
     * Counts how many lines are inside the file
     *
     * @return int
     * @throws IOException if an error occurs
     */
    private int countLines() throws IOException {
        int counter = 0;
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Creates a student/department statistic
     *
     * @return int[]
     */
    public int[] getStudentsInDepartment() {
        int[] sections = new int[2]; // IT, ME
        for (int i = 1; i < statistic.length; i++) {
            switch (statistic[i][4].toUpperCase()) {
                case "IT":
                    sections[0]++;
                    break;
                case "ME":
                    sections[1]++;
                    break;
                default:
                    break;
            }
        }
        return sections;
    }

    /**
     * Counts how many classes there are within the csv
     *
     * @return int
     */
    public int getClassCount() {
        String tmpClass = "";
        for (int i = 1; i < statistic.length; i++) {
            if (!tmpClass.contains(statistic[i][2])) {
                tmpClass += statistic[i][2] + ";";
            }
        }
        return tmpClass.split(";").length;
    }

    /**
     * Calculates the average class size
     *
     * @return double
     */
    public double averageClassSize() {
        double avg = 0;
        avg = (double) (statistic.length - 1) / (double) getClassCount();
        return avg;
    }

    /**
     * Creates a birthday/month statistic
     *
     * @return int[]
     */
    public int[] getBirthdayCount() {
        int[] months = new int[12];
        for (int i = 1; i < statistic.length; i++) {
            months[Integer.parseInt(statistic[i][3].substring(3, 5)) - 1]++;
        }
        return months;
    }

    /**
     * How often students of the same class have their birthday on the same day
     *
     * @return int
     */
    public int getClassesWithSameBirthdayCount() {
        int same = 0;
        ArrayList<String> seen = new ArrayList<>();
        for (int i = 1; i < statistic.length; i++) {
            String tmpClass = statistic[i][2];
            String tmpBirth = statistic[i][3].substring(0, 5);
            for (int j = i + 1; j < statistic.length; j++) {
                if (i != j && seen.indexOf(tmpClass) < 0) {
                    if (tmpClass.equals(statistic[j][2])) {
                        if (tmpBirth.equals(statistic[j][3].substring(0, 5))) {
                            same++;
                            seen.add(tmpClass);
                        }
                    }
                }
            }
        }
        return same;
    }
}