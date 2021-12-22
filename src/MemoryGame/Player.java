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

