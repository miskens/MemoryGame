package Views;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardView {
    Stage window;
    Scene optionsScene1;
    FlowPane optionsLayout1, optionsLayout2;
    int optionsLayoutWidth = 500, optionsLayoutHeight = 500;

    public BoardView(Stage window) {
        this.window = window;
    }

    public void openGame() {
        window.setTitle("Memory Game Fun!!");

        OptionsView optionsView = new OptionsView();

        optionsLayout1 = optionsView.createOptionsLayout();

        optionsScene1 = new Scene(optionsLayout1, optionsLayoutWidth, optionsLayoutHeight);

        openNewView(window, optionsScene1);
    }

    private void openNewView(Stage window, Scene optionsScene1) {
        window.setScene(optionsScene1);
        window.show();
    }
}