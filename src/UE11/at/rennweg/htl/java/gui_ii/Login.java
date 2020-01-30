package UE11.at.rennweg.htl.java.gui_ii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage stage) {

        //gridpane setup
        GridPane gdp = new GridPane();
        gdp.setHgap(15);
        gdp.setVgap(15);
        gdp.setPadding(new Insets(20));

        //scene setup
        Scene scene = new Scene(gdp, 300, 200); // w, h

        //screen setup
        stage.setTitle("GridPane Sample");
        stage.setMinWidth(400);
        stage.setMinHeight(250);
        stage.setScene(scene);

        //label (mail)
        Label labe = new Label("Email:");
        TextField tfe = new TextField("abc@xyz.at");

        //label (passwd)
        Label labp = new Label("Password:");
        TextField tfp = new TextField("***");

        //button setup
        Button loginButton = new Button("Login");

        //grid setup
        gdp.add(labe, 0, 0);
        gdp.add(tfe, 1, 0, 2, 1);
        gdp.add(labp, 0, 1);
        gdp.add(tfp, 1, 1, 2, 1);
        gdp.add(loginButton, 1, 2);

        //final setup
        stage.show();
    }
}
