package MemoryGame;
public class Player {
    private String playerName;
    private int points;
    private boolean active;  

    public Player(String name){
        this.playerName =  name;
        this.points = 0;
        this.active = false; //I spellogiken hanterar vi vem som börjar (random)
    }

    public String getPlayerName(){
        return playerName;
    }
//"vmArgs": "--module-path /home/theo/Hämtningar/openjfx-17.0.1_linux-aarch64_bin-sdk/javafx-sdk-17.0.1/lib/ --add-modules javafx.controls,javafx.fxml",
