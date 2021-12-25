package Views;

import java.util.concurrent.Flow;

import MemoryGame.Player;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BoardView {
    Stage window;
    Scene optionsScene;
    VBox optionsLayout1;
    Player[] players;
    Button endGameButton, starNewGameButton;
    int gameBoardWidth = 1000, gameBoardHeight = 500;
    int optionsLayoutWidth = 500, optionsLayoutHeight = 500;
    int leftPaneWidth = 250;

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
        FlowPane leftGameBoardPane = new FlowPane();
        leftGameBoardPane.setOrientation(Orientation.VERTICAL);
        leftGameBoardPane.setVgap(5);
        
        Label[] playerNameLabels = new Label[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players = new Player[playerNames.length];
            players[i] = new Player(playerNames[i]);

            playerNameLabels[i] = new Label();
            playerNameLabels[i].setText(players[i].getPlayerName() + "\n" +
                                        players[i].getPoints());

            
            playerNameLabels[i].setMinSize(leftPaneWidth, 150);
            playerNameLabels[i].setAlignment(Pos.CENTER);
            playerNameLabels[i].setFont(new Font("Roman", 24));

            playerNameLabels[i].setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            BorderWidths.DEFAULT)));
        }

        leftGameBoardPane.getChildren().addAll(playerNameLabels);

        endGameButton = createButton("End Game!");
        leftGameBoardPane.getChildren().addAll(endGameButton);
        
        starNewGameButton = createButton("Start New Game");
        leftGameBoardPane.getChildren().addAll(starNewGameButton);
        

        Scene gameScene = new Scene(leftGameBoardPane, gameBoardWidth, gameBoardHeight);

        openNewView(window, gameScene);
    }

    private Button createButton(String buttonText) {
        Button btn = new Button();
        btn.setMinSize(leftPaneWidth, 50);
        btn.setText(buttonText);
        return btn;
    }
}