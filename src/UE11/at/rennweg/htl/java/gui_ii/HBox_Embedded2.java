package UE11.at.rennweg.htl.java.gui_ii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HBox_Embedded2 extends Application {

    @Override
    public void start(Stage stage) {
        //screen setup
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 600, 150);
        stage.setScene(scene);

        //setup
        stage.setTitle("HBox in BorderPane");

        //Label setup
        Label label = new Label("Dateiname:");
        label.setMinWidth(Control.USE_PREF_SIZE);
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //field setup
        TextField field1 = new TextField("");
        field1.setMinWidth(Control.USE_PREF_SIZE);
        field1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Button
        Button button = new Button("Bearbeiten");
        button.setMinWidth(Control.USE_PREF_SIZE);

        //Stackpane Setup
        StackPane stp = new StackPane();
        stp.setAlignment(Pos.CENTER_RIGHT);
        stp.getChildren().add(button);

        //hbox Setup
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(20));
        hbox.setBackground(
                new Background(
                        new BackgroundFill(Color.rgb(34, 255, 255), new CornerRadii(10), Insets.EMPTY)));
        hbox.getChildren().addAll(label, field1, stp);

        //final setup
        BorderPane.setMargin(hbox, new Insets(10));
        borderPane.setTop(hbox);
        stage.show();
    }
}
