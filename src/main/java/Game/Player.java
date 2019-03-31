package Game;

public class Player {

    static int count = 0;
    private String player;
    private int id_player;
    private int score;

    Player(String player, int id) {
        this.player = player;
        this.id_player = id;
        this.score = 0;
        count += 1;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String name) {
        this.player = name;
    }

    public int getPlayerId() {
        return id_player;
    }

    public void PlayerWin() {
        this.score += 1;
    }

    public int getPlayerScore() {
        return score;
    }
}
