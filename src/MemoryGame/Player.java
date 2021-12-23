package MemoryGame;
public class Player {
    private final String playerName;
    private int pPoints;
    private boolean pActive;  

    public Player(String name){
        this.playerName =  name;
        this.pPoints = 0;
        this.pActive = false; //I spellogiken hanterar vi vem som börjar (random)
    }

    public String getPlayerName(){
        return playerName;
    }

    /*public void setPlayerName(String name){
        this.playerName = name;

    }*/
    public boolean getActive(){
        return pActive;
    }
    
    public int getpPoints() {
        return pPoints;
    }
}
//active
//points
