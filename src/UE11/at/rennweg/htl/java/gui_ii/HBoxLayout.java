package UE11.at.rennweg.htl.java.gui_ii;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HBoxLayout extends Application {

    //global var define
    double abstaende;
    Insets outerGap;
    int currentStage = 0;
    Pos posOnScreen;
    VPos vPos = null;

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
        //mainLabel
        Label mainLabel = new Label("Abstand zwischen den GUI-Elementen:");
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
        ok.setOnAction(event -> abstaende = Double.parseDouble(text.toString())); //ok button
        abbr.setOnAction(event -> stage.close()); //abbr button
        // Define the event handler
        EventHandler<ActionEvent> textReader = e -> {
            //abstaende stage
            if (currentStage == 0) {
                mainLabel.setText("Rahmen Abstände: oben, rechts, unten, links");
                abstaende = Double.parseDouble(text.getText());
                //text.deleteText(0, text.getText().length());
                text.setText("");
            }
            //border gaps stage
            if (currentStage == 1) {
                mainLabel.setText("Position im Fenster");
                outerGap = new Insets(
                        Double.parseDouble(text.getText().split(",")[0]),
                        Double.parseDouble(text.getText().split(",")[1]),
                        Double.parseDouble(text.getText().split(",")[2]),
                        Double.parseDouble(text.getText().split(",")[3])
                );
                //text.deleteText(0, text.getText().length());
                text.setText("");
            }
            //posOnScreen stage
            if (currentStage == 2) {
                String[] positions = text.getText().split(",");
                vPos = (positions[0].toLowerCase().equals("oben")) ? VPos.TOP :
                        (positions[0].toLowerCase().equals("mitte")) ? VPos.CENTER :
                                (positions[0].toLowerCase().equals("unten")) ? VPos.BOTTOM : null;

                posOnScreen = (positions[1].toLowerCase().equals("links")) ? ((VPos.TOP == vPos) ? Pos.TOP_LEFT : (VPos.CENTER == vPos) ? Pos.CENTER_LEFT : Pos.BOTTOM_LEFT) :
                        (positions[1].toLowerCase().equals("rechts")) ? ((VPos.TOP == vPos) ? Pos.TOP_RIGHT : (VPos.CENTER == vPos) ? Pos.CENTER_RIGHT : Pos.BOTTOM_RIGHT) :
                                (positions[1].toLowerCase().equals("mitte")) ? ((VPos.TOP == vPos) ? Pos.TOP_CENTER : (VPos.CENTER == vPos) ? Pos.CENTER : Pos.BOTTOM_CENTER) : null;

                //final setup
                HBox_Sample layout = new HBox_Sample();
                layout.start(stage);
                layout.hbox.setPadding(outerGap);
                layout.hbox.setAlignment(posOnScreen);
                layout.hbox.setSpacing(abstaende);
            }
            currentStage++;
        };

        //attach event handler
        ok.setOnAction(textReader);

        //final setup
        flowPane.getChildren().addAll(mainLabel, text, ok, abbr);
        stage.show();
    }
}
