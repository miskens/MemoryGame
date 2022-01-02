package Views;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import MemoryGame.Card;
import MemoryGame.Game;
import MemoryGame.Player;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BoardView {
    Stage window;
    Scene optionsScene;
    VBox optionsLayout1;
    Game game = new Game();
    Player[] players;
    Button endGameButton, startNewGameButton;
    ImageView imageView;
    Card card1, card2;
    int nrOfCards;
    String firstCardName, secondCardName;
    int optionsLayoutWidth = 500, optionsLayoutHeight = 500;
    int leftPaneWidth = 250;

    public BoardView(Stage window) {
        this.window = window;
    }

    public void openStarPage() {
        window.setTitle("Memory Game Fun!!");

        OptionsView optionsView = new OptionsView();

        optionsLayout1 = optionsView.createOptionsLayout(window);

        optionsScene = new Scene(optionsLayout1, optionsLayoutWidth, optionsLayoutHeight);

        openNewView(window, optionsScene);
    }

    public void openNewView(Stage window, Scene scene) {
        window.setScene(scene);
        window.show();
    }

    public void buildGameboard(String[] playerNames, int rows, int cols) {
        OptionsView optionsView = new OptionsView();

        FlowPane leftGameBoardPane = createLeftGameBoardPane();

        Label[] playerNameLabels = createPlayersAndPlayerLabels(playerNames);
        leftGameBoardPane.getChildren().addAll(playerNameLabels);

        addGameBoardButtons(optionsView, leftGameBoardPane);

        GridPane cardsGrid = CreateCardsGrid(rows, cols);

        GridPane fullGameBoard = new GridPane();
        configureGameBoard(fullGameBoard);
        GridPane.setConstraints(leftGameBoardPane, 0, 0);
        GridPane.setConstraints(cardsGrid, 1, 0);

        fullGameBoard.getChildren().addAll(leftGameBoardPane, cardsGrid);
        
        game.PlayGame(window, this, fullGameBoard, players, playerNameLabels, nrOfCards);
    }

    private void configureGameBoard(GridPane fullGameBoard) {

        Image img = new javafx.scene.image.Image("Icons/smoothBackgrnd.jpg");
        BackgroundImage bckgroundImage = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bGround = new Background(bckgroundImage);

        fullGameBoard.setBackground(bGround);
        fullGameBoard.setPadding(new Insets(10,10,10,10));
        fullGameBoard.setVgap(4); fullGameBoard.setHgap(4);
    }

    private FlowPane createLeftGameBoardPane() {
        FlowPane leftGameBoardPane = new FlowPane();
        
        leftGameBoardPane.setOrientation(Orientation.VERTICAL);
        leftGameBoardPane.setVgap(5);

        return leftGameBoardPane;
    }

    private Label[] createPlayersAndPlayerLabels(String[] playerNames) {
        Label[] playerNameLabels =new Label[playerNames.length];
        players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            
            players[i] = new Player(playerNames[i]);

            playerNameLabels[i] = configurePlayerLabel(players[i]);
            playerNameLabels[i].setId("p" + i + "Label");

            playerNameLabels[i].setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            BorderWidths.DEFAULT)));
        }
        return playerNameLabels;
    }

    private void addGameBoardButtons(OptionsView optionsView, FlowPane leftGameBoardPane) {
        endGameButton = createButton("End Game!");
        endGameButton.setId("endGameBtn");
        
        startNewGameButton = createButton("Start New Game");
        startNewGameButton.setId("startNewGameBtn");

        createButtonEvents(endGameButton);
        createButtonEvents(startNewGameButton);

        leftGameBoardPane.getChildren().addAll(endGameButton, startNewGameButton);
    }

    public Label configurePlayerLabel(Player player) {
        Label playerLabel = new Label();
        playerLabel.setText(player.getPlayerName() + "\n" + 
                            player.getPoints());
   
        playerLabel.setStyle("-fx-background-color: aliceblue;");
        playerLabel.setMinSize(leftPaneWidth, 140);
        playerLabel.setAlignment(Pos.CENTER);
        playerLabel.setFont(new Font("Roman", 24));

        return playerLabel;
    }

    private Button createButton(String buttonText) {
        Button btn = new Button();
        btn.setMinSize(leftPaneWidth, 50);
        btn.setText(buttonText);
        return btn;
    }

    private GridPane CreateCardsGrid(int rows, int cols) {
        Random random = new Random();

        GridPane gridPaneLayout = new GridPane();

        Card[] cards = new Card[rows * cols];
        nrOfCards = cards.length;

        for (int i = 0; i < cards.length; i++) {
            int randomNr = random.nextInt(25) + 1;

            cards[i] = new Card("Icons/turned.png", "Icons/fruit_" + randomNr + ".png");
            cards[i].setVisibleCardSource("Icons/fruit_" + randomNr + ".png");
            cards[i+1] = new Card("Icons/turned.png", "Icons/fruit_" + randomNr + ".png");
            cards[i+1].setVisibleCardSource("Icons/fruit_" + randomNr + ".png");

            i++;
        }

        List<Card> cardsList = Arrays.asList(cards);
        Collections.shuffle(cardsList);

        int c = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {

                Card card = cardsList.get(c);

                imageView = new ImageView();
                imageView.setImage(card);

                gridPaneLayout.add(imageView, i, j);

                imageView.setOnMouseClicked(e -> game.checkCards(e));
                c++;
            }
        }
        
        return gridPaneLayout;
    }

    public void createButtonEvents(Button btn) {

        String btnId = btn.getId();

        switch(btnId) {
            case "startNewGameBtn": {
                btn.setOnMouseReleased(e -> {

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setHeaderText("Are you sure?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        openStarPage();
                    } else {
                        return;
                    } 
                });
                return;
            }
            case "endGameBtn": {
                btn.setOnMouseReleased(e -> {

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setHeaderText("End Game?");
                    
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        System.exit(0);
                    } else {
                        return;
                    } 
                });
                return;
            }
        }
    }
}