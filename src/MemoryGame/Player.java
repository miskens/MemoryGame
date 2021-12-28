package MemoryGame;

public class Player {
    private int playerNr;
    private String playerName;
    private int points;
    private boolean active;
    private static int numberOfPlayers = 0; 

    public Player(String name){
        numberOfPlayers++;
        this.playerNr = numberOfPlayers;
        this.playerName =  name;
        this.points = 0;
        this.active = false;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String name){
        this.playerName = name;

    }
    public boolean getActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    
    public int getPoints() {
        return points;
    }
    public void addPoint(){
        this.points++;
    }

    public int getPlayerNr() {
        return this.playerNr;
    }

    public static int getNrOfPlayers() {
        return numberOfPlayers;
    }

    public static void resetNrOfPlayers() {
        numberOfPlayers = 0;
    }
}