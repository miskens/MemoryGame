package Views;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardView {
    Stage window;
    Scene optionsScene;
    VBox optionsLayout1;
    int optionsLayoutWidth = 500, optionsLayoutHeight = 500;

    public BoardView(Stage window) {
        this.window = window;
    }

    public void openGame() {
        window.setTitle("Memory Game Fun!!");

        OptionsView optionsView = new OptionsView(window);

        optionsLayout1 = optionsView.createOptionsLayout();

        optionsScene = new Scene(optionsLayout1, optionsLayoutWidth, optionsLayoutHeight);

        openNewView(window, optionsScene);
    }

    public void openNewView(Stage window, Scene scene) {
        window.setScene(scene);
        window.show();
    }

    public void buildGameboard(String[] playerNames, int rows, int cols) {
    }
}