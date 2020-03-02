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
public class KillRemarks {

    /**
     * Removes all comments from a java file
     *
     * @param inPath  String
     * @param outPath String
     * @throws IOException if an error occurs
     */
    public static void killRemarks(String inPath, String outPath) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(Paths.get(inPath), StandardCharsets.UTF_8);
                BufferedWriter out = Files.newBufferedWriter(Paths.get(outPath), StandardCharsets.UTF_8)

        ) {
            String text = "";
            String line;

            while ((line = in.readLine()) != null) {
                text += (line + System.lineSeparator());
            }

            //remove line comments
            text = text.replaceAll("([ \t]*//.*)", "");

            //remove block comments
            text = text.replaceAll("(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)", "");

            //remove both line and block comments
            //text = text.replaceAll("(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/|[ \\t]*//.*)", "");

            out.write(text);
        }
    }
}
