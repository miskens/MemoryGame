package Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

public class OptionsView {

    TextField nrOfPlayersTextField;
    ComboBox<String> nrOfCardsBox;
    Button goToOptions2Btn;

    public FlowPane createOptionsLayout() {

        ImageView iconImage = getLogo();

        Label nrOfPlayerslabel = new Label("Choose number of players: "); // Will start with functionality for 2 players only initially (2-4 if we have time)
        nrOfPlayersTextField = new TextField();
        nrOfPlayersTextField.setMaxWidth(40.0);

        Label nrOfCards = new Label("Choose size of play board: ");
        ObservableList<String> cardOptions = FXCollections.observableArrayList("3x4", "4x4", "4x5", "4x6", "5x6", "6x6");
        nrOfCardsBox = new ComboBox<String>();
        nrOfCardsBox.getItems().addAll(cardOptions);

        goToOptions2Btn = new Button("Continue!");
        goToOptions2Btn.setMinWidth(100);

        FlowPane layout = new FlowPane();
        layout.setOrientation(Orientation.VERTICAL);
        layout = buildOptionsLayout1(layout, iconImage, nrOfPlayerslabel, nrOfPlayersTextField, nrOfCards, nrOfCardsBox, goToOptions2Btn);

        // goToOptions2Btn.setOnMouseClicked(e -> dothis());

        return layout;
    }

    private ImageView getLogo() {
        
        ImageView logo = new ImageView();

        try {
            Image icon = new Image("Icons/memory.png");
            logo.setImage(icon);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return logo;
    }

    private FlowPane buildOptionsLayout1(FlowPane layout, ImageView iconImage, Label nrOfPlayerslabel,
                                                TextField nrOfPlayersTextField2, Label nrOfCards, ComboBox<String> nrOfCardsBox2, Button goToOptions2Btn2) {

        layout.setPadding(new Insets(10, 10, 10, 10));
        // layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(iconImage, nrOfPlayerslabel, nrOfPlayersTextField, nrOfCards, nrOfCardsBox, goToOptions2Btn);
        
        return layout;
    }
}
