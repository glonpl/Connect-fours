package Game;

public class Board {
    private int height = 6;
    private int width = 7;
    public int[][] board = new int[height][width];

    public void createBoard() {
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                board[i][j] = 0;
            }
        }
    }

}
