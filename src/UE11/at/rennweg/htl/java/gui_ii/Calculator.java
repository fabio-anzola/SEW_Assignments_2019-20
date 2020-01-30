package UE11.at.rennweg.htl.java.gui_ii;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {

    @Override
    public void start(Stage stage) {
        // init
        HBox hBox = new HBox();
        VBox vBox = new VBox();

        //screen setup
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5, 50, 5, 50));

        //setup
        Scene scene = new Scene(vBox, 500, 150);
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.setMinWidth(500);
        stage.setMaxWidth(500);
        stage.setMinHeight(150);
        stage.setMinHeight(150);

        //input fields setup
        TextField numberOne = new TextField();
        TextField numberTwo = new TextField();

        //input fields conf
        numberOne.setPrefColumnCount(10);
        numberTwo.setPrefColumnCount(10);

        //output label setup
        Label output = new Label("---");

        //add Event
        EventHandler<ActionEvent> add = event -> {
            try {
                output.setText("" + (Double.parseDouble(numberOne.getText()) + Double.parseDouble(numberTwo.getText())));
            } catch (NumberFormatException e) {
                output.setText("Ein Fehler ist Aufgetreten");
            }
        };

        //sub Event
        EventHandler<ActionEvent> sub = event -> {
            try {
                output.setText("" + (Double.parseDouble(numberOne.getText()) - Double.parseDouble(numberTwo.getText())));
            } catch (NumberFormatException e) {
                output.setText("Ein Fehler ist Aufgetreten");
            }
        };

        //mult Event
        EventHandler<ActionEvent> mult = event -> {
            try {
                output.setText("" + (Double.parseDouble(numberOne.getText()) * Double.parseDouble(numberTwo.getText())));
            } catch (NumberFormatException e) {
                output.setText("Ein Fehler ist Aufgetreten");
            }
        };

        //div Event
        EventHandler<ActionEvent> div = event -> {
            try {
                output.setText("" + (Double.parseDouble(numberOne.getText()) / Double.parseDouble(numberTwo.getText())));
            } catch (NumberFormatException e) {
                output.setText("Ein Fehler ist Aufgetreten");
            }
        };

        //buttons setup
        Button addButton = new Button("Add");
        Button subButton = new Button("Sub");
        Button multButton = new Button("Mult");
        Button divButton = new Button("Div");

        //button config
        addButton.setOnAction(add);
        subButton.setOnAction(sub);
        multButton.setOnAction(mult);
        divButton.setOnAction(div);

        //final setup
        vBox.getChildren().addAll(numberOne, numberTwo, hBox, output);
        hBox.getChildren().addAll(addButton, subButton, multButton, divButton);
        stage.show();
    }
}
