package Views;

import java.util.Optional;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class OptionsView {

    private Stage window;
    BoardView boardView;
    VBox layout;
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

    private VBox buildOptionsLayout1(VBox layout, Label nrOfPlayerslabel,
            TextField nrOfPlayersTextField2, Label nrOfCards, ComboBox<String> nrOfCardsBox2) {

        configureLayout(layout);

        addMemoryGameLogo(layout);

        layout.getChildren().addAll(nrOfPlayerslabel, nrOfPlayersTextField, nrOfCards, nrOfCardsBox);

        createOptions2Btn(layout);

        return layout;
    }

    private void configureLayout(VBox layout) {
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setSpacing(17);
        layout.setAlignment(Pos.CENTER);
    }

    private void createOptions2Btn(VBox layout) {
        goToOptions2Btn = new Button("Continue!");

        goToOptions2Btn.setId("optionsBtn");
        goToOptions2Btn.setMinWidth(100);
        createButtonEvents(goToOptions2Btn);

        layout.getChildren().add(goToOptions2Btn);
    }

    private void buildOptionsLayout2(int nrOfPlayers, int rows, int cols) {
        boardView = new BoardView(window);
        layout = new VBox();

        configureLayout(layout);

        addMemoryGameLogo(layout);

        createBackButton(layout);

        createPlayerLabelsAndFields(layout, nrOfPlayers);

        createStartButton(layout);

        Scene optionsScene2 = new Scene(layout, optionsLayoutWidth, optionsLayoutHeight);

        boardView.openNewView(window, optionsScene2);
    }

    private void addMemoryGameLogo(VBox layout) {
        ImageView logo = new ImageView();

        try {
            Image icon = new Image("Icons/memory.png");
            logo.setImage(icon);
        } catch (Exception e) {
            System.out.println(e);
        }

        layout.getChildren().add(logo);
    }

    private void createStartButton(VBox layout) {
        startGameBtn = new Button("Start Game!");
        startGameBtn.setId("startBtn");;
        startGameBtn.setMinWidth(100);
        createButtonEvents(startGameBtn);

        layout.getChildren().add(startGameBtn);
    }

    private void createPlayerLabelsAndFields(VBox layout, int nrOfPlayers) {
        Label playerLbl = new Label();
        playerTxtFields = new TextField[nrOfPlayers];
        for (int i = 0; i < nrOfPlayers; i++) {
            playerLbl = new Label("Choose player " + (i + 1) + " name:");
            playerTxtFields[i] = new TextField();
            playerTxtFields[i].setMaxWidth(150.0);
            layout.getChildren().addAll(playerLbl, playerTxtFields[i]);
        }
    }

    private void createBackButton(VBox layout) {
        backButton = new Button("Go Back");

        backButton.setId("backBtn");
        backButton.setMinWidth(100);
        createButtonEvents(backButton);

        layout.getChildren().add(backButton);
    }

    public void createButtonEvents(Button btn) {

        String btnId = btn.getId();

        switch (btnId) {
            case "optionsBtn": {
                nrOfPlayersTextField.setOnKeyReleased(e -> {
                    createGotoOptions2ButtonEvent();
                });
                nrOfCardsBox.setOnAction(e -> {
                    createGotoOptions2ButtonEvent();
                });
            }
                return;
            case "startBtn": {
                btn.setOnMouseReleased(e -> {
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
            case "backBtn": {
                btn.setOnMouseClicked(e -> backToOptions1());
                return;
            }
            case "startNewGameBtn": {
                btn.setOnMouseReleased(e -> {

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setHeaderText("Alert");
                    alert.setContentText("Are you sure?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        backToOptions1();
                    } else {
                        return;
                    } 
                });
                return;
            }

            default:
                return;
        }
    }

    private void createGotoOptions2ButtonEvent() {
        int nrOfPlayers = Integer.parseInt(nrOfPlayersTextField.getText());
        int rows = getNrOfRows(nrOfCardsBox);
        int cols = getNrOfCols(nrOfCardsBox);

        goToOptions2Btn.setOnMouseReleased(ev -> buildOptionsLayout2(nrOfPlayers, rows, cols));
    }

    private void backToOptions1() {
        VBox layout = createOptionsLayout();
        BoardView boardView = new BoardView(window);
        Scene scene = new Scene(layout, optionsLayoutWidth, optionsLayoutHeight);
        boardView.openNewView(window, scene);
    }
}