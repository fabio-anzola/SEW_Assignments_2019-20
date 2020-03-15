package UE13.ue13_byte_io_and_files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class TestByteIO {

    /**
     * Inverts a given file
     *
     * @param srcFile  Path
     * @param destFile Path
     * @throws IOException if an error occurs
     */
    public static void invertFile(Path srcFile, Path destFile) throws IOException {
        try (
                InputStream in = Files.newInputStream(srcFile);
                OutputStream out = Files.newOutputStream(destFile)

        ) {
            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) ~buffer[i];
                }
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    /**
     * Encrypts previously encrypted File
     *
     * @param srcFile  Path
     * @param destFile Path
     * @param key      int
     * @throws IOException if an error occurs
     */
    public static void encryptFile(Path srcFile, Path destFile, int key) throws IOException {
        try (
                InputStream in = Files.newInputStream(srcFile);
                OutputStream out = Files.newOutputStream(destFile)

        ) {
            byte[] buffer = new byte[2048];
            int bytesRead;

            Random rnd = new Random(key);
            byte[] rndBytes = new byte[2048];
            rnd.nextBytes(rndBytes);

            while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) (buffer[i] - rndBytes[i]);
                }
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    /**
     * Decrypts previously encrypted File
     *
     * @param srcFile  Path
     * @param destFile Path
     * @param key      int
     * @throws IOException if an error occurs
     */
    public static void decryptFile(Path srcFile, Path destFile, int key) throws IOException {
        try (
                InputStream in = Files.newInputStream(srcFile);
                OutputStream out = Files.newOutputStream(destFile)

        ) {
            byte[] buffer = new byte[2048];
            int bytesRead;

            Random rnd = new Random(key);
            byte[] rndBytes = new byte[2048];
            rnd.nextBytes(rndBytes);

            while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) (buffer[i] + rndBytes[i]);
                }
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    /**
     * Encrypts previously encrypted File
     *
     * @param srcFile  Path
     * @param destFile Path
     * @param key      String
     * @throws IOException if an error occurs
     */
    public static void encryptFile(Path srcFile, Path destFile, String key) throws IOException {
        try (
                InputStream in = Files.newInputStream(srcFile);
                OutputStream out = Files.newOutputStream(destFile)

        ) {
            byte[] buffer = new byte[2048];
            int bytesRead;

            Random rnd = new Random(key.hashCode());
            byte[] rndBytes = new byte[2048];
            rnd.nextBytes(rndBytes);

            while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) (buffer[i] - rndBytes[i]);
                }
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    /**
     * Decrypts previously encrypted File
     *
     * @param srcFile  Path
     * @param destFile Path
     * @param key      String
     * @throws IOException if an error occurs
     */
    public static void decryptFile(Path srcFile, Path destFile, String key) throws IOException {
        try (
                InputStream in = Files.newInputStream(srcFile);
                OutputStream out = Files.newOutputStream(destFile)

        ) {
            byte[] buffer = new byte[2048];
            int bytesRead;

            Random rnd = new Random(key.hashCode());
            byte[] rndBytes = new byte[2048];
            rnd.nextBytes(rndBytes);

            while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) (buffer[i] + rndBytes[i]);
                }
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    /**
     * Splits a file
     *
     * @param file     Path
     * @param maxBytes long
     * @throws IOException if an error occurs
     */
    public static void fileSplit(Path file, long maxBytes) throws IOException {
        try (
                InputStream in = Files.newInputStream(file)

        ) {
            float helper = (float) Files.size(file) / maxBytes;
            byte[] buffer = new byte[(int) maxBytes];
            int bytesRead;
            double filecount = Math.ceil(helper);
            int i = 1;
            while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
                String first = file.toString() + '.' + String.format("%08d", i);
                Files.createFile(Paths.get(first));
                OutputStream out = Files.newOutputStream(Paths.get(first));
                out.write(buffer, 0, bytesRead);
                i++;
            }
        }
    }

    /**
     * Unsplits a file
     *
     * @param file Path
     * @throws IOException if an error occurs
     */
    public static void fileUnsplit(Path file) throws IOException {
        try (
                OutputStream out = Files.newOutputStream(file)

        ) {
            byte[] buffer = new byte[2048];
            int bytesRead;
            for (int i = 1; true; i++) {
                String first = file.toString() + '.' + String.format("%08d", i);
                if (Files.exists(Paths.get(first))) {
                    InputStream in = Files.newInputStream(Paths.get(first));
                    while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
                        out.write(buffer, 0, bytesRead);
                    }
                } else {
                    break;
                }
            }
        }
    }
}
