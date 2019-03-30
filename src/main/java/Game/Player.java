package Game;

public class Player {


    private String player;
    private int id_player;
   private int score;
    static int count=0;
    Player(String player, int id){
        this.player = player;
        this.id_player = id;
        this.score = 0;
        count+=1;
    }

    public String getPlayer() {
        return player;
    }
    public int getPlayerId() {
        return id_player;
    }
    public void PlayerWin() {this.score+=1;}
    public void setPlayer(String name) {this.player=name;}
    public int getPlayerScore() {
        return score;
    }
}
