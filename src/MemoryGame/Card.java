import java.beans.Visibility;

import javafx.scene.image.Image;

public class Card extends Image {
    private Visibility visibility;
    private String visibleCardSource;
    private final String invisibleCardSource;

    public Card(String imageSource) {
        super(imageSource);
        this.visibility = Visibility.Invisible; 
        this.invisibleCardSource = "Icons/turned.png";
    }

    public Card(String imageSource, String visibleCardSource) {
        super(imageSource);
        this.visibleCardSource = visibleCardSource;
        this.visibility = Visibility.Invisible; 
        this.invisibleCardSource = "Icons/turned.png";
    }

    public Visibility getVisibility() {
        return this.visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getInvisibleCardSource() {
        return invisibleCardSource;
    }

    public String getVisibleCardSource() {
        return visibleCardSource;
    }

    public void setVisibleCardSource(String cardSource) {
        this.visibleCardSource = cardSource;
    }
}