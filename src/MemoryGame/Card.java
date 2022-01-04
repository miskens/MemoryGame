package MemoryGame;

import javafx.scene.image.Image;

public class Card extends Image {
    private String visibleCardSource;
    private final String invisibleCardSource;

    public Card(String imageSource) {
        super(imageSource);
        this.invisibleCardSource = "Icons/turned.png";
    }

    public Card(String imageSource, String visibleCardSource) {
        super(imageSource);
        this.visibleCardSource = visibleCardSource; 
        this.invisibleCardSource = "Icons/turned.png";
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