package server;

public class Player {

    private String name;
    private int playerNumber;

    public Player(String name, int num) {
        this.name = name;
        this.playerNumber = num;

    }

    public String getName() { return this.name; }

    public int getPlayerNumber() { return this.playerNumber; }


}
