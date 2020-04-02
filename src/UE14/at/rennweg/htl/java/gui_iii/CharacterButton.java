package UE14.at.rennweg.htl.java.gui_iii;

import javafx.scene.control.Button;

public class CharacterButton extends Button {

    public char character;

    public CharacterButton(char ch) {
        this.character = ch;

        this.setText("?");

        this.setOnAction(btn -> {
            this.setText(this.character + "");
        });
    }

}
