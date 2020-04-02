package UE14.at.rennweg.htl.java.gui_iii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The JavaFX App v1
 *
 * @author fabioanzola
 */
public class Wort_Raten_1 extends Application {

    /**
     * Stores the current word
     */
    public String currentword;

    /**
     * Stores the index of all correct words
     */
    public int index = 0;

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
        String[] words = new String[]{"Schule", "Hallo", "Java"};

        currentword = setWord(words);

        stage.setTitle("Wort Raten 1");

        TextField input = new TextField();
        input.setPromptText("Wort hier eingeben");

        HBox charBox = new HBox();
        createCharacterButtons(charBox, currentword);

        Button submit = new Button("überprüfe das Wort");
        HBox subBox = new HBox(submit);
        subBox.setHgrow(submit, Priority.ALWAYS);
        submit.setMaxWidth(Double.MAX_VALUE);

        VBox vb = new VBox(input, charBox, subBox);
        vb.setSpacing(5);
        vb.setPadding(new Insets(5, 5, 5, 5));

        Scene scene = new Scene(vb, 600, 100);
        stage.setScene(scene);

        stage.show();

        submit.setOnAction(e -> {
            if (input.getText().equals(currentword)) {
                input.setPromptText(String.format("%s ist richtig", input.getText()));
            } else {
                input.setPromptText(String.format("%s ist falsch, des Wort war: %s", input.getText(), currentword));
            }
            input.clear();
            currentword = setWord(words);
            createCharacterButtons(charBox, currentword);
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
     * Gets the next word
     *
     * @param list The list of words
     * @return The word
     */
    public String setWord(String[] list) {
        if (index < list.length) {
            index++;
            return list[index - 1];
        } else {
            System.exit(0);
        }
        return null;
    }
}
