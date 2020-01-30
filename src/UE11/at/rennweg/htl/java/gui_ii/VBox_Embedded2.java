package UE11.at.rennweg.htl.java.gui_ii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VBox_Embedded2 extends Application {

    @Override
    public void start(Stage stage) {

        //Screen setup
        stage.setTitle("VBox in BorderPane");

        //setup
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 400, 200);
        stage.setScene(scene);

        //Checkbox Pic setup
        CheckBox cbB = new CheckBox("Bild");
        cbB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Checkbox Text setup
        CheckBox cbT = new CheckBox("Text");
        cbT.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Button setup
        Button button = new Button("Darstellen");

        //Stackpane setup
        StackPane stp = new StackPane();
        stp.getChildren().add(button);
        stp.setAlignment(Pos.BOTTOM_CENTER);

        //vb setup
        VBox vb = new VBox();
        vb.setPadding(new Insets(20));
        vb.setSpacing(20);
        vb.setBackground(new Background(
                new BackgroundFill(Color.rgb(251, 0, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        vb.getChildren().addAll(cbB, cbT, stp);

        //Final setup
        borderPane.setRight(vb);
        BorderPane.setMargin(vb, new Insets(5));
        stage.show();
    }
}
