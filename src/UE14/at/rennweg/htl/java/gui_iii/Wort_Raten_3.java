package UE14.at.rennweg.htl.java.gui_iii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The JavaFX App v3
 *
 * @author fabioanzola
 */
public class Wort_Raten_3 extends Application {

    /**
     * Stores the current word
     */
    public String currentWord;

    /**
     * Stores the current index for the words
     */
    public int index = 0;

    /**
     * Stores the index of all correct words
     */
    public int correct = 0;

    /**
     * If last word is reached
     */
    public boolean stop = false;

    /**
     * How many letters uncovered
     */
    public int uncovered = 0;

    /**
     * How many letters should be covered
     */
    public int allCovered = 0;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The JavaFX start function
     *
     * @param stage The JavaFX Stage
     * @throws Exception if an error occurs
     */
    @Override
    public void start(Stage stage) throws Exception {

        ArrayList<String> word = new ArrayList<String>();

        try (
                BufferedReader in = Files.newBufferedReader(Paths.get("resources/words.txt"), StandardCharsets.UTF_8)

        ) {
            String line;

            while ((line = in.readLine()) != null) {
                word.add(line);
            }
        }

        String[] words = word.toArray(String[]::new);

        currentWord = setWord(words, stage);

        stage.setTitle("Wort Raten 2");

        TextField input = new TextField();
        input.setPromptText("Wort hier eingeben");

        HBox charBox = new HBox();
        createCharacterButtons(charBox, currentWord);

        Button submit = new Button("überprüfe das Wort");
        HBox subBox = new HBox(submit);
        subBox.setHgrow(submit, Priority.ALWAYS);
        submit.setMaxWidth(Double.MAX_VALUE);

        Label stats = new Label();
        showStatistic(stats);

        VBox vb = new VBox(input, charBox, subBox, stats);
        vb.setSpacing(5);
        vb.setPadding(new Insets(5, 5, 5, 5));

        Scene scene = new Scene(vb, 600, 120);
        stage.setScene(scene);

        stage.show();

        submit.setOnAction(e -> {
            if (input.getText().equals(currentWord)) {
                input.setPromptText(String.format("%s ist richtig", input.getText()));
                correct++;
            } else {
                input.setPromptText(String.format("%s ist falsch, des Wort war: %s", input.getText(), currentWord));
            }
            getUncovered(charBox);
            showStatistic(stats);
            input.clear();
            currentWord = setWord(words, stage);
            if (!stop) {
                createCharacterButtons(charBox, currentWord);
            }
        });

    }

    /**
     * Sets all CharacterButtons in place
     *
     * @param bx   The HBox for placement
     * @param word The word to be spread on the buttons
     */
    public void createCharacterButtons(HBox bx, String word) {
        bx.getChildren().clear();
        for (int i = 0; i < word.length(); i++) {
            CharacterButton button = new CharacterButton(word.charAt(i));
            bx.getChildren().add(button);
            bx.setHgrow(button, Priority.ALWAYS);
            button.setMaxWidth(Double.MAX_VALUE);
        }
    }

    /**
     * Gets the next word or displays last Dialog
     *
     * @param list  The list of words
     * @param stage The JavaFX Stage
     * @return The word
     */
    public String setWord(String[] list, Stage stage) {
        if (index < list.length) {
            index++;
            return list[index - 1];
        } else {
            stop = true;
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);
            Text errorDisplay = new Text("No more words!");
            Text stat = new Text(String.format("Erratene wörter: %.2f%% \n bei %.2f%% angezeigten Buchstaben", (correct * 1F / index) * 100F, (uncovered * 1F / allCovered) * 100F));
            Button exit = new Button("Ok");
            errorDisplay.setTextAlignment(TextAlignment.CENTER);
            stat.setTextAlignment(TextAlignment.CENTER);
            VBox screen = new VBox(errorDisplay, stat, exit);
            screen.setSpacing(10);
            screen.setAlignment(Pos.CENTER);
            Scene dialogScene = new Scene(screen, 250, 100);
            dialog.setScene(dialogScene);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.show();

            exit.setOnAction(exitEvent -> {
                stage.close();
                dialog.close();
            });
        }
        return null;
    }

    /**
     * Gets how many buttons have gotten uncovered
     *
     * @param bx The HBox of CharacterButtons
     */
    public void getUncovered(HBox bx) {
        Object[] btn = bx.getChildren().toArray();
        allCovered += currentWord.length();
        for (int q = 0; q < btn.length; q++) {
            CharacterButton chB = (CharacterButton) btn[q];
            if (!chB.getText().equals("?")) {
                uncovered++;
            }
        }
    }

    /**
     * Places the stats in the Label
     *
     * @param lb The Label for stats
     */
    public void showStatistic(Label lb) {
        float x = (uncovered * 1F / allCovered) * 100F;
        if (uncovered == 0 && allCovered == 0) {
            x = 0;
        }
        String format = String.format("erratene wörter: %.2f%%, angezeigte Buchstaben: %.2f%%", (correct * 1F / index) * 100F, x);
        lb.setText(format);
    }
}
