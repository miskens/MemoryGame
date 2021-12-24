package Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class OptionsView {

    private Stage window;
    BoardView boardView;
    TextField nrOfPlayersTextField;
    TextField[] playerTxtFields;
    ComboBox<String> nrOfCardsBox;
    Button goToOptions2Btn, startGameBtn, backButton;
    int optionsLayoutWidth = 500, optionsLayoutHeight = 500;

    public OptionsView(Stage window) {
        this.window = window;
    }

    public VBox createOptionsLayout() {

        Label nrOfPlayerslabel = new Label("Choose number of players: "); // Will start with functionality for 2 players
                                                                          // only initially (2-4 if we have time)
        nrOfPlayersTextField = new TextField();
        nrOfPlayersTextField.setMaxWidth(40.0);

        Label nrOfCards = new Label("Choose size of play board: ");
        ObservableList<String> cardOptions = FXCollections.observableArrayList("3x4", "4x4", "4x5", "4x6", "5x6",
                "6x6");
        nrOfCardsBox = new ComboBox<String>();
        nrOfCardsBox.getItems().addAll(cardOptions);
        nrOfCardsBox.setValue("3x4");

        VBox layout = new VBox();
        layout = buildOptionsLayout1(layout, nrOfPlayerslabel, nrOfPlayersTextField, nrOfCards, nrOfCardsBox);

        return layout;
    }

    private int getNrOfRows(ComboBox<String> nrOfCardsBox) {
        switch (nrOfCardsBox.getValue()) {
            case "3x4":
                return 3;
            case "4x4":
            case "4x5":
            case "4x6":
                return 4;
            case "5x6":
                return 5;
            case "6x6":
                return 6;
            default:
                return 3;
        }
    }

    private int getNrOfCols(ComboBox<String> nrOfCardsBox) {
        switch (nrOfCardsBox.getValue()) {
            case "3x4":
            case "4x4":
                return 4;
            case "4x5":
                return 5;
            case "4x6":
            case "5x6":
            case "6x6":
                return 6;
            default:
                return 3;
        }
    }

    private ImageView getLogo() {

        ImageView logo = new ImageView();

        try {
            Image icon = new Image("Icons/memory.png");
            logo.setImage(icon);
        } catch (Exception e) {
            System.out.println(e);
        }

        return logo;
    }

    private VBox buildOptionsLayout1(VBox layout, Label nrOfPlayerslabel,
            TextField nrOfPlayersTextField2, Label nrOfCards, ComboBox<String> nrOfCardsBox2) {

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setSpacing(17);
        layout.setAlignment(Pos.CENTER);

        ImageView iconImage = getLogo();

        goToOptions2Btn = new Button("Continue!");
        goToOptions2Btn.setMinWidth(100);

        layout.getChildren().addAll(iconImage, nrOfPlayerslabel, nrOfPlayersTextField, nrOfCards, nrOfCardsBox,
                goToOptions2Btn);

        createButtonEvents(goToOptions2Btn);

        return layout;
    }

    private void buildOptionsLayout2(int nrOfPlayers, int rows, int cols) {
        boardView = new BoardView(window);
        VBox layout = new VBox();

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        ImageView iconImage = getLogo();
        Label playerLbl;

        layout.getChildren().add(iconImage);

        backButton = new Button("Go Back");
        backButton.setMinWidth(100);
        createButtonEvents(backButton);

        layout.getChildren().add(backButton);

        playerTxtFields = new TextField[nrOfPlayers];
        for (int i = 0; i < nrOfPlayers; i++) {
            playerLbl = new Label("Choose player " + (i + 1) + " name:");
            playerTxtFields[i] = new TextField();
            playerTxtFields[i].setMaxWidth(150.0);
            layout.getChildren().addAll(playerLbl, playerTxtFields[i]);
        }

        startGameBtn = new Button("Start Game!");
        startGameBtn.setMinWidth(100);
        createButtonEvents(startGameBtn);

        layout.getChildren().add(startGameBtn);

        Scene optionsScene2 = new Scene(layout, optionsLayoutWidth, optionsLayoutHeight);

        boardView.openNewView(window, optionsScene2);
    }

    private void createButtonEvents(Button btn) {

        String btnText = btn.getText();

        switch (btnText) {
            case "Continue!": {
                nrOfPlayersTextField.setOnKeyReleased(e -> {
                    int nrOfPlayers = Integer.parseInt(nrOfPlayersTextField.getText());
                    int rows = getNrOfRows(nrOfCardsBox);
                    int cols = getNrOfCols(nrOfCardsBox);

                    goToOptions2Btn.setOnMouseReleased(ev -> buildOptionsLayout2(nrOfPlayers, rows, cols));
                });
                nrOfCardsBox.setOnAction(e -> {
                    int nrOfPlayers = Integer.parseInt(nrOfPlayersTextField.getText());
                    int rows = getNrOfRows(nrOfCardsBox);
                    int cols = getNrOfCols(nrOfCardsBox);

                    goToOptions2Btn.setOnMouseReleased(ev -> buildOptionsLayout2(nrOfPlayers, rows, cols));
                });
            }
                return;
            case "Start Game!": {
                startGameBtn.setOnMouseReleased(e -> {
                    String[] playerNames = new String[playerTxtFields.length];

                    for (int i = 0; i < playerTxtFields.length; i++) {
                        playerNames[i] = playerTxtFields[i].getText();
                    }

                    int rows = getNrOfRows(nrOfCardsBox);
                    int cols = getNrOfCols(nrOfCardsBox);

                    boardView.buildGameboard(playerNames, rows, cols);
                });
                return;
            }
            case "Go Back": {
                backButton.setOnMouseClicked(e -> backToOptions1());
                return;
            }
            default:
                return;
        }

    }

    private void backToOptions1() {
        VBox layout = createOptionsLayout();
        BoardView boardView = new BoardView(window);
        Scene scene = new Scene(layout, optionsLayoutWidth, optionsLayoutHeight);
        boardView.openNewView(window, scene);
    }
}