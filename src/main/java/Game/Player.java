package Game;

public class Player {


    private String player;
    private int id_player;
   private int score;
    static int count=0;
    Player(String player){
        this.player = player;
        this.id_player = count;
        this.score = 0;
        count+=1;
    }

    public String getPlayerStr() {
        return player;
    }
    public int getPlayerId() {
        return id_player;
    }
    public void PlayerWin() {this.score+=1;}
}
