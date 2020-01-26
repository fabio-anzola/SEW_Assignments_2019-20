package UE10.at.rennweg.htl.java.gui_i;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowLayout extends Application {
    public static void actions(String text) {
        Button play = new Button("Play");
        Button rewi = new Button("Rewind");
        Button forw = new Button("Forward");
        Button paus = new Button("Pause");
        Button stop = new Button("Stop");
        String in = text.toUpperCase();
        switch (in) {
            case "H":
                Stage stageH = new Stage();
                stageH.setTitle("FlowLayout horizontal");
                FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
                flowPane.setPadding(new Insets(20, 0, 0, 20));
                flowPane.setHgap(10);
                flowPane.getChildren().addAll(play, rewi, forw, paus, stop);
                Scene sceneH = new Scene(flowPane, 340, 70);
                stageH.setScene(sceneH);
                stageH.show();
                break;
            case "V":
                Stage stageV = new Stage();
                stageV.setTitle("FlowLayout vertical");
                FlowPane flowPaneV = new FlowPane(Orientation.VERTICAL);
                flowPaneV.setPadding(new Insets(0, 0, 0, 25));
                flowPaneV.setVgap(30);
                play.setTranslateX(+8);
                rewi.setTranslateX(-10);
                forw.setTranslateX(-15);
                paus.setTranslateX(-4);
                stop.setTranslateX(+4);
                flowPaneV.getChildren().addAll(play, rewi, forw, paus, stop);
                Scene sceneV = new Scene(flowPaneV, 100, 270);
                stageV.setScene(sceneV);
                stageV.show();
                break;
            default:
                Stage stageDef = new Stage();
                stageDef.show();
                stageDef.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
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
        Label mainLabel = new Label("FlowLayout Ausrichtung angeben: H oder V");
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
        ok.setOnAction(event -> actions(text.getText())); //ok button
        abbr.setOnAction(event -> stage.close()); //abbr button
        //final setup
        flowPane.getChildren().addAll(mainLabel, text, ok, abbr);
        stage.show();
    }
}
