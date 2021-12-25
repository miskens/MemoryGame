package MemoryGame;

import java.util.Random;

import Views.BoardView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class Game extends Application {
    Stage window;
    BoardView boardView;
    Scene gameScene;
    int nrOfClicks;
    ImageView imageViewClick1, imageViewClick2;
    Card card1, card2;
    String firstCardName, secondCardName;
    Player[] players;
    Label[] playerLabels;
    Player currentPlayer;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        boardView = new BoardView(window);      

        boardView.openStarPage();
    }

    public void PlayGame(Stage window, BoardView boardview, GridPane fullGameBoard, Player[] players, Label[] playerLabels) {
        this.playerLabels = playerLabels;
        this.players = players;
        gameScene = new Scene(fullGameBoard);

        boardView = new BoardView(window);
        
        Random random = new Random();
        Player currentPlayer = players[random.nextInt(players.length)];
        currentPlayer.setActive(true);
        
        setActivePlayerColor();
 
        boardView.openNewView(window, gameScene);
    }

    private void setActivePlayerColor() {
        for (int i = 0; i < playerLabels.length; i++) {
            if(players[i].getActive() == true) {
                playerLabels[i].setBackground(new Background(new BackgroundFill(Color.DARKTURQUOISE, new CornerRadii(0), new Insets(0))));
            }
            else {
                playerLabels[i].setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(0), new Insets(0))));
            }
        }
    }

    public void checkCards(MouseEvent e) {
        nrOfClicks++;

        if(nrOfClicks == 1) {
            handleFirstCardClick(e);
        }
        else if (nrOfClicks == 2) {
            handleSecondCardClick(e);    
        }
    }

    private void handleFirstCardClick(MouseEvent e) {
        imageViewClick1 = (ImageView)e.getSource();
        card1 = (Card)imageViewClick1.getImage();
        imageViewClick1.setImage(new Card(card1.getVisibleCardSource()));
        firstCardName = card1.getVisibleCardSource();
    }

    private void handleSecondCardClick(MouseEvent e) {
        imageViewClick2 = (ImageView)e.getSource();
        
        if( imageViewClick1 == imageViewClick2) {
            nrOfClicks = 0;

            imageViewClick1.setImage(new Card(card1.getInvisibleCardSource(), card1.getVisibleCardSource()));
            return;
        }

            card2 = (Card)imageViewClick2.getImage();
            imageViewClick2.setImage(new Card(card2.getVisibleCardSource()));
            secondCardName = card2.getVisibleCardSource();

            if(firstCardName.equals(secondCardName)) {
                
                PauseTransition pause = new PauseTransition(Duration.seconds((1)));
                pause.setOnFinished(ev -> {
                    imageViewClick1.setVisible(false); 
                    imageViewClick2.setVisible(false);
                    nrOfClicks = 0;
                });
                pause.play();

                // DONE!  Wait 1 second, then eliminate(remove them from the game) and 
                // TODO: give 2 points to the player
            }
            else {
                // DONE!  Show 2 seconds then hide them ( set invisible )
                // TODO: and change to next player!

                PauseTransition pause = new PauseTransition(Duration.seconds((2)));
                pause.setOnFinished(ev -> {
                    Card copyCard1 = new Card(card1.getInvisibleCardSource(), card1.getVisibleCardSource());
                    imageViewClick1.setImage(copyCard1);
                    Card copyCard2 = new Card(card2.getInvisibleCardSource(), card2.getVisibleCardSource());
                    imageViewClick2.setImage(copyCard2);
                    nrOfClicks = 0;
                });
                pause.play();
            }
    }
}
