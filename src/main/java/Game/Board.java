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
    public void winCheck(int column, int row, int disc, String player) {

        int sumhor=0,sumver=0,sumdiagup=0,sumdiagdown=0;//-|/\
        //dsc 5 i 1, bo łatwo odróżnić od zera i siebie nawzajem
        //zaczynasz od miejsca wyladowania
        // sumhor( idz w prawo i while(disc==val[col][row+i]){sum+1; i+1} jak wyjddzie z while idz w lewo i while(disc==val[col][row-i]){sum+1; i+1}
        // sumver( idz tylko w dol )
        // diagup/dwn po skosie sprawdzenie gora i dol
    }



    }
