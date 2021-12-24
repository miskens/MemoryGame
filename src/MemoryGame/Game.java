package MemoryGame;

import Views.BoardView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        BoardView boardView = new BoardView(window);

        boardView.openGame();

    }
}
