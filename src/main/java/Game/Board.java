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

    public void printBoard(){

        for(int i=0; i<height; i++) {
            System.out.println();
            for(int j=0; j<width; j++) {
                System.out.print(" "+board[i][j]);
            }
        }
    }

    public void setBoardSize(int row, int col) {
        if ((row<4)||(col<4)) throw new IllegalArgumentException("Za mała plansza! Musi być minimum 4x4!");
        height = row;
        width = col;
        board = new int[height][width];

    }




}
