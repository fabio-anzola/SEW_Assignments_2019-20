package UE10.at.rennweg.htl.java.gui_i;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class GroupLayout extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //setup
        stage.setTitle("GroupLayout");
        FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
        stage.setScene(new Scene(flowPane, 200, 65));
        flowPane.setPadding(new Insets(0, 0, 0, 2));
        flowPane.setHgap(20);
        flowPane.setVgap(10);
        //buttons
        Button play = new Button("Play");
        Button paus = new Button("Pause");
        Button stop = new Button("Stop");
        Button rewi = new Button("Rewind");
        Button forw = new Button("Forward");
        flowPane.getChildren().addAll(play, paus, stop, rewi, forw);
        stage.show();
        //final setup
        double maxWidth = paus.getWidth();
        play.setPrefWidth(maxWidth);
        stop.setPrefWidth(maxWidth);
        maxWidth = (paus.getWidth() * 3 + 20) / 2;
        rewi.setPrefWidth(maxWidth);
        forw.setPrefWidth(maxWidth);
    }
}
