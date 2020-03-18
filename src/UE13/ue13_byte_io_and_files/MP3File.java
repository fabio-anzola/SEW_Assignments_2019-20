package UE13.ue13_byte_io_and_files;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author fabioanzola
 */
public class MP3File {
    /**
     * Attribute to store the ID3v1 data-array
     */
    public byte[] id3v1Data;
    /**
     * Attribute for song-title
     */
    private String titleAtt;
    /**
     * Attribute for song-artist
     */
    private String artistAtt;
    /**
     * Attribute for song-album
     */
    private String albumAtt;
    /**
     * Attribute for song-release-year
     */
    private String yearAtt;
    /**
     * Attribute for comment
     */
    private String commentAtt;
    /**
     * Attribute for genre
     */
    private char genreAtt;

    /**
     * A constructor with one path
     *
     * @param file The path to the song
     * @throws IOException If an error occurs
     */
    public MP3File(Path file) throws IOException {
        storeData(file);
        storeToAttributes();
    }

    /**
     * Outputs to String
     *
     * @return The output
     */
    @Override
    public String toString() {
        return (this.titleAtt + " von " + this.artistAtt + " in " + this.albumAtt + " (" + yearAtt + "), " + commentAtt + ", Genre: " + (int) (this.genreAtt));
    }

    /**
     * Fills the attributes
     *
     * @throws IOException If an error occurs
     */
    public void storeToAttributes() throws IOException {
        byte[] titleArr = new byte[30];
        byte[] artistArr = new byte[30];
        byte[] albumArr = new byte[30];
        byte[] yearArr = new byte[4];
        byte[] commentArr = new byte[30];
        byte[] genreArr = new byte[1];
        for (int i = 0; i < this.id3v1Data.length; i++) {
            if (i > 2 && i < 33) {
                titleArr[i - 3] = this.id3v1Data[i];
            }
            if (i > 32 && i < 63) {
                artistArr[i - 33] = this.id3v1Data[i];
            }
            if (i > 62 && i < 93) {
                albumArr[i - 63] = this.id3v1Data[i];
            }
            if (i > 92 && i < 97) {
                yearArr[i - 93] = this.id3v1Data[i];
            }
            if (i > 96 && i < 127) {
                commentArr[i - 97] = this.id3v1Data[i];
            }
            if (i == 127) {
                this.genreAtt = (char) this.id3v1Data[i];
            }
        }
        this.titleAtt = new String(titleArr).trim();
        this.artistAtt = new String(artistArr).trim();
        this.albumAtt = new String(albumArr).trim();
        this.yearAtt = new String(yearArr).trim();
        this.commentAtt = new String(commentArr).trim();
    }

    /**
     * @param file The File as a path
     * @throws IOException If an error occurs
     */
    public void storeData(Path file) throws IOException {
        try (
                InputStream in = Files.newInputStream(file)

        ) {
            byte[] buffer = new byte[128];
            int bytesRead;
            in.skip(Files.size(file) - 128);
            in.read(buffer, 0, buffer.length);
            this.id3v1Data = buffer;
        }
    }
}

