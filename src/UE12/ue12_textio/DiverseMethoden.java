package UE12.ue12_textio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author fabioanzola
 */
public class DiverseMethoden {

    /**
     * Converts text within a file to uppercase
     *
     * @param srcFile  String
     * @param destFile String
     * @throws IOException if an error occurs
     */
    static void convertFileToUpperCase(String srcFile, String destFile) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(srcFile), StandardCharsets.UTF_8);
                BufferedWriter out = Files.newBufferedWriter(Paths.get(destFile), StandardCharsets.UTF_8)

        ) {
            String line;

            while ((line = in.readLine()) != null) {
                out.write(line.toUpperCase() + System.lineSeparator());
            }
        }
    }

    /**
     * Converts text within a file to lowercase
     *
     * @param srcFile  String
     * @param destFile String
     * @throws IOException if an error occurs
     */
    static void convertFileToLowerCase(String srcFile, String destFile) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(srcFile), StandardCharsets.UTF_8);
                BufferedWriter out = Files.newBufferedWriter(Paths.get(destFile), StandardCharsets.UTF_8)

        ) {
            String line;

            while ((line = in.readLine()) != null) {
                out.write(line.toLowerCase() + System.lineSeparator());
            }
        }
    }

    /**
     * Compares the text within two files
     *
     * @param srcFile1 String
     * @param srcFile2 String
     * @return boolean
     * @throws IOException if an error occurs
     */
    static boolean compareFiles(String srcFile1, String srcFile2) throws IOException {
        try (
                BufferedReader inOne = Files.newBufferedReader(Paths.get(srcFile1), StandardCharsets.UTF_8);
                BufferedReader inTwo = Files.newBufferedReader(Paths.get(srcFile2), StandardCharsets.UTF_8)


        ) {
            String line1;
            String line2;
            while (((line1 = inOne.readLine()) != null) && ((line2 = inTwo.readLine()) != null)) {
                if (!line1.equalsIgnoreCase(line2)) {
                    return false;
                }
            }
            if (line1 == null) {
                return (inTwo.readLine() == null);
            }
        }
        return true;
    }

    /**
     * Encrypts a file and then writes it into a different file
     *
     * @param srcFile  String
     * @param destFile String
     * @throws IOException if an error occurs
     */
    static void encryptFile(String srcFile, String destFile) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(srcFile), StandardCharsets.UTF_8);
                BufferedWriter out = Files.newBufferedWriter(Paths.get(destFile), StandardCharsets.UTF_8)


        ) {
            String line;

            while ((line = in.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    out.write((char) (line.charAt(i) + 1));
                }
                out.write(System.lineSeparator());
            }
        }
    }

    /**
     * Decrypts a previously encrypted file and then writes it into a different file
     *
     * @param srcFile  String
     * @param destFile String
     * @throws IOException if an error occurs
     */
    static void decryptFile(String srcFile, String destFile) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(srcFile), StandardCharsets.UTF_8);
                BufferedWriter out = Files.newBufferedWriter(Paths.get(destFile), StandardCharsets.UTF_8)


        ) {
            String line;

            while ((line = in.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    out.write((char) (line.charAt(i) - 1));
                }
                out.write(System.lineSeparator());
            }
        }
    }

    /**
     * Creates a text-statistic
     *
     * @param srcFile         String
     * @param statisticString String
     * @throws IOException if an error occurs
     */
    static void countChar(String srcFile, String statisticString) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(srcFile), StandardCharsets.UTF_8);
                BufferedWriter out = Files.newBufferedWriter(Paths.get(statisticString), StandardCharsets.UTF_8)


        ) {
            String line;
            String[][] stats = new String[26][4];
            for (int i = 0; i < stats.length; i++) {
                stats[i][2] = String.valueOf(0);
            }

            int all = 0;

            while ((line = in.readLine()) != null) {
                line = line.toLowerCase();
                for (int i = 0; i < line.length(); i++) {
                    all++;
                    stats[(int) line.charAt(i) - 97][0] = line.charAt(i) + "";
                    stats[(int) line.charAt(i) - 97][1] = String.valueOf((int) line.charAt(i));
                    stats[(int) line.charAt(i) - 97][2] = String.valueOf(Integer.parseInt(stats[(int) line.charAt(i) - 97][2]) + 1);
                }
            }

            for (int i = 0; i < stats.length; i++) {
                stats[i][3] = String.valueOf(String.format("%.4g", (((float) Integer.parseInt(stats[i][2]) / (float) all))));
            }

            for (int i = 0; i < stats.length - 1; i++) {
                for (int j = i + 1; j < stats.length; j++) {
                    if (stats[i][3].compareTo(stats[j][3]) > 0) {
                        String[] temp = stats[i];
                        stats[i] = stats[j];
                        stats[j] = temp;
                    }
                }
            }

            //System.out.println(Arrays.deepToString(stats).replace("], ", "]\n"));
            for (int i = stats.length - 1; i >= 0; i--) {
                for (int j = 0; j < stats[i].length; j++) {
                    if (stats[i][j] != null) {
                        out.write(stats[i][j]);
                        if (j != stats[i].length - 1) {
                            out.write(';');
                        }
                    } else {
                        out.write(";0;0.0000");
                        break;
                    }
                }
                out.write(System.lineSeparator());
            }
        }
    }
}
