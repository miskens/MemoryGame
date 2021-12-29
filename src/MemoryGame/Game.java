package MemoryGame;

import java.util.Optional;
import java.util.Random;

import Views.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
    int nrOfCards;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        boardView = new BoardView(window);

        boardView.openStarPage();
    }

    public void PlayGame(Stage window, BoardView boardview, GridPane fullGameBoard, Player[] players,
            Label[] playerLabels, int nrOfCards) {
        this.nrOfCards = nrOfCards;
        this.playerLabels = playerLabels;
        this.players = players;
        gameScene = new Scene(fullGameBoard);

        boardView = new BoardView(window);

        Random random = new Random();
        currentPlayer = players[random.nextInt(players.length)];
        currentPlayer.setActive(true);
        // setActivePlayerColor(players, playerLabels);
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i].setStyle(getLabelColor(players[i]));
        }
        
        boardView.openNewView(window, gameScene);
    }

    public String getLabelColor(Player player) {
        if (player.getActive() == true) {
            return "-fx-background-color: lightgreen;";
        } else {
            return "-fx-background-color: aliceblue;";
        }
    }

    public void checkCards(MouseEvent e) {
        nrOfClicks++;

        if (nrOfClicks == 1) {
            handleFirstCardClick(e);
        } else if (nrOfClicks == 2) {
            handleSecondCardClick(e);
        }
    }

    private void handleFirstCardClick(MouseEvent e) {
        imageViewClick1 = (ImageView) e.getSource();
        card1 = (Card) imageViewClick1.getImage();
        imageViewClick1.setImage(new Card(card1.getVisibleCardSource()));
        firstCardName = card1.getVisibleCardSource();
    }

    private void handleSecondCardClick(MouseEvent e) {
        imageViewClick2 = (ImageView) e.getSource();

        if (imageViewClick1 == imageViewClick2) {
            if (!imageViewClick2.getImage().getUrl().toString().contains("turrned.png")) {
                nrOfClicks = 1;
                return;
            }
            nrOfClicks = 0;

            imageViewClick1.setImage(new Card(card1.getInvisibleCardSource(), card1.getVisibleCardSource()));
            return;
        }

        card2 = (Card) imageViewClick2.getImage();
        imageViewClick2.setImage(new Card(card2.getVisibleCardSource()));
        secondCardName = card2.getVisibleCardSource();

        if (firstCardName.equals(secondCardName)) {

            pauseThenHideCards();

            currentPlayer.addPoint();

            playerLabels[currentPlayer.getPlayerNr() -1].setText(currentPlayer.getPlayerName() + "\n" + 
                        currentPlayer.getPoints());

            nrOfCards -= 2;
            if(nrOfCards == 0){
                announceWinner(); 
            }
        } else {
            PauseTransition pause = new PauseTransition(Duration.seconds((2)));
            pause.setOnFinished(ev -> {
                Card copyCard1 = new Card(card1.getInvisibleCardSource(), card1.getVisibleCardSource());
                imageViewClick1.setImage(copyCard1);
                Card copyCard2 = new Card(card2.getInvisibleCardSource(), card2.getVisibleCardSource());
                imageViewClick2.setImage(copyCard2);
                nrOfClicks = 0;

                currentPlayer = nextPlayer(currentPlayer);
            });
            pause.play();
        }
    }

    private void pauseThenHideCards() {
        PauseTransition pause = new PauseTransition(Duration.seconds((1)));
            pause.setOnFinished(ev -> {
                imageViewClick1.setVisible(false);
                imageViewClick2.setVisible(false);
                nrOfClicks = 0;
            });
            pause.play();
    }

    private void announceWinner() {
        String winner = "";

        ButtonType newGameButton = new ButtonType("New Game", ButtonData.OK_DONE);
        ButtonType endGamebutton = new ButtonType("Close Game", ButtonData.CANCEL_CLOSE);
        
        Alert alert = new Alert(AlertType.NONE, winner, endGamebutton, newGameButton);
        Image img;
        ImageView alertImageView = new ImageView();
    
        if(players[0].getPoints() == players[1].getPoints()){
            img = new Image("Icons/draw.png");
            winner = ("It was a draw!");
            alert.setContentText(winner + "\n Good game!");
            }
            else if(players[0].getPoints() > players[1].getPoints()){
                img = new Image("Icons/p1wins.png");
                winner = players[0].getPlayerName();
                alert.setContentText(winner + " is the winner!\n Congratulations!!");
            }
            else{
                img = new Image("Icons/p2wins.png");
                winner = players[1].getPlayerName();
                alert.setContentText(winner + " is the winner!\n Congratulations!!");
            }

        Player.resetNrOfPlayers();

        alertImageView.setImage(img);
        alert.setGraphic(alertImageView);

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType btn = result.orElse(result.get());
        if(btn.getButtonData() == ButtonData.CANCEL_CLOSE) {
            System.exit(0);
        }
        else if(btn.getButtonData() == ButtonData.OK_DONE){
            boardView.openStarPage();
        }
    }

    private Player nextPlayer(Player currentPlayer) {

        if (currentPlayer.getPlayerNr() == 1) {
            currentPlayer = players[1];
            playerLabels[1].setStyle("-fx-background-color: lightgreen;");
            playerLabels[0].setStyle("-fx-background-color: aliceblue;");
        } else {
            currentPlayer = players[0];
            playerLabels[1].setStyle("-fx-background-color: aliceblue;");
            playerLabels[0].setStyle("-fx-background-color: lightgreen;");
        }

        return currentPlayer;
    }
}
