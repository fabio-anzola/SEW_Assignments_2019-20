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
public class MorseCode {

    /**
     * Generic Symbols
     */
    static String resNormal = "abcdefghijklmnopqrstuvwxyz1234567890äöü";

    /**
     * Morse Symbols
     */
    static String[] resMorse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "_", "..-", "...-", ".--", "-..-",
            "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", ".-.-",
            "---.", "..--",
            "--..--", "---...", "-.-.-.", "..--..", "-....-", "..--.-", "-.--.", "-.--.-", ".----.",
            ".-..-.", "-...-",
            ".-.-.", "-..-.", ".--.-."};

    /**
     * Converts text to morse
     *
     * @param inFilename  String
     * @param outFilename String
     * @throws IOException if an error occurs
     */
    public static void convertToMorse(String inFilename, String outFilename) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(inFilename), StandardCharsets.UTF_8);
                BufferedWriter out = Files.newBufferedWriter(Paths.get(outFilename), StandardCharsets.UTF_8)

        ) {
            String line;
            char[] arrayForCharacters;
            while ((line = in.readLine()) != null) {
                arrayForCharacters = line.toLowerCase().toCharArray();
                for (char arrayForCharacter : arrayForCharacters) {
                    if (resNormal.contains("" + arrayForCharacter)) {
                        out.write("/" + resMorse[arrayForCharacter - 97]);
                    } else if (arrayForCharacter == ' ') {
                        out.write("/");
                    } else {
                        out.write("//");
                    }
                }
            }
            out.write("/" + System.lineSeparator());
        }
    }

    /**
     * Converts Morse to Text
     *
     * @param inFilename  String
     * @param outFilename String
     * @throws IOException if an error occurs
     */
    public static void convertToText(String inFilename, String outFilename) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(inFilename), StandardCharsets.UTF_8);
                BufferedWriter out = Files.newBufferedWriter(Paths.get(outFilename), StandardCharsets.UTF_8)

        ) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] chars = line.split("/");
                for (int i = 0; i < chars.length; i++) {
                    for (int j = 0; j < resMorse.length; j++) {
                        if (chars[i].equals("")) {
                            if (i != 0) {
                                out.write(' ');
                                break;
                            }
                        } else if (chars[i].equals(resMorse[j])) {
                            out.write(((char) (j + 97)));
                        }
                    }
                }
                out.write(System.lineSeparator());
            }
        }
    }
}
