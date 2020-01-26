package UE10.at.rennweg.htl.java.gui_i;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class BorderLayout extends Application {
    public static void buttons(String text) {
        //setup
        String in = text.toUpperCase();
        Stage stage = new Stage();
        stage.setTitle("BorderPane Sample");
        BorderPane br = new BorderPane();
        //all the same just different alignment
        if (in.indexOf('T') >= 0) {
            Button top = new Button("TOP");
            top.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            br.setTop(top);
            BorderPane.setAlignment(top, Pos.CENTER);
        }
        if (in.indexOf('L') >= 0) {
            Button left = new Button("LEFT");
            left.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            br.setLeft(left);
            BorderPane.setAlignment(left, Pos.CENTER);
        }
        if (in.indexOf('C') >= 0) {
            Button center = new Button("CENTER");
            center.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            br.setCenter(center);
            BorderPane.setAlignment(center, Pos.CENTER);
        }
        if (in.indexOf('R') >= 0) {
            Button right = new Button("RIGHT");
            right.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            br.setRight(right);
            BorderPane.setAlignment(right, Pos.CENTER);
        }
        if (in.indexOf('B') >= 0) {
            Button bottom = new Button("BOTTOM");
            bottom.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            br.setBottom(bottom);
            BorderPane.setAlignment(bottom, Pos.CENTER);
        }
        //final setup
        Scene scene = new Scene(br, 500, 350);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //setup
        stage.setTitle("Eingabe");
        FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL); //horizontal
        Scene scene = new Scene(flowPane, 370, 100);
        stage.setScene(scene);
        flowPane.setPadding(new Insets(0, 0, 0, 37));
        flowPane.setVgap(10);
        flowPane.setHgap(20);
        flowPane.setRowValignment(VPos.BOTTOM);
        flowPane.setColumnHalignment(HPos.RIGHT);
        //label
        Label mainLabel = new Label("Kombination von: TCBLR");
        TextField text = new TextField();
        text.setPrefWidth(300);
        //button1
        Button ok = new Button("OK");
        ok.setPrefWidth(80);
        ok.setTranslateX(35);
        ok.setDefaultButton(true);
        //button2
        Button abbr = new Button("Abbrechen");
        abbr.setPrefWidth(80);
        abbr.setTranslateX(80);
        //button actions
        ok.setOnAction(event -> buttons(text.getText())); //ok button
        abbr.setOnAction(event -> stage.close()); //abbr button
        //final setup
        flowPane.getChildren().addAll(mainLabel, text, ok, abbr);
        stage.show();
    }
}
