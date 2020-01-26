package UE10.at.rennweg.htl.java.gui_i;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Gui_1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //Button button = new Button ("Say Hello");
        Group group = new Group();
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("GUI 1 - empty");
        stage.setMinWidth(200D); // double!
        stage.setMinHeight(200D);
        //stage.centerOnScreen();
        stage.setX(100D);
        stage.setY(200D);

        Label label1 = new Label("Hello"); // label mit Text
        label1.setLayoutX(10); // x position relative to window
        label1.setLayoutY(20);
        Label label2 = new Label(); // empty
        label2.setText("World!");
        label2.setLayoutX(10);
        label2.setLayoutY(40);
        group.getChildren().addAll(label1, label2); // add all labels
        stage.show();
    }
}