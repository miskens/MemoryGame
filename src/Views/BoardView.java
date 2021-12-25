package Views;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardView {
    Stage window;
    Scene optionsScene;
    VBox optionsLayout1;
    int gameBoardWidth = 1000, gameBoardHeight = 500;
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
        FlowPane gameBoardPane = new FlowPane();
        gameBoardPane.setOrientation(Orientation.VERTICAL);
        
        Label[] playerNameLabels = new Label[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            playerNameLabels[i] = new Label();
            playerNameLabels[i].setText(playerNames[i]);

        }

        gameBoardPane.getChildren().addAll(playerNameLabels);

        Scene gameScene = new Scene(gameBoardPane, gameBoardWidth, gameBoardHeight);

        openNewView(window, gameScene);
    }
}