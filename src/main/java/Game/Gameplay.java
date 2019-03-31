package Game;

import java.io.*;
import java.util.Scanner;

public class Gameplay {
    public Player player1 = new Player("", 0);
    public Player player2 = new Player("", 1);
    public Board plansza;
    private boolean turn = true;

//********************************helpers****************************
    public boolean pickDisc(String f) {
        if (f.equals("T")) {
            return true;
        } else return false;
    }


    public String PlayerNameTurn(boolean turn) {
        if (turn) {
            return player1.getPlayer();
        } else {
            return player2.getPlayer();
        }
    }


    public int StandardBoard(String w) {
        if (w.equals("1")) {
            return 1;
        } else if (w.equals("2")) {
            return 2;
        } else {
            System.out.print("\nZła opcja!\n");
            return 0;
        }
    }

//********************************Save to file****************************
    public void SaveGame(String filename) {
        String text = "";
        text = text.concat(player1.getPlayer() + ",");
        text = text.concat(player1.getPlayerScore() + ",");
        text = text.concat(player2.getPlayer() + ",");
        text = text.concat(player2.getPlayerScore() + "");
        text = text.concat(plansza.printBoardString() + ";");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(text);

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
    }



//********************************Preparations****************************
    public void GameplaySetup() {
        plansza = new Board();
        int k = 0;
        while (k == 0) {
            System.out.print("Czy chcesz grać na standartowej planszy?(1-Tak/2-Nie)");
            Scanner in = new Scanner(System.in);
            String w = in.next();
            k = StandardBoard(w);
        }
        boolean token = false;
        if (k == 2) {
            while (!token) {
                System.out.print("Podaj rozmiary planszy\n");
                Scanner in = new Scanner(System.in);
                int column = in.nextInt();
                int row = in.nextInt();
                try {
                    plansza.setBoardSize(column, row);
                    token = true;
                } catch (Exception e) {
                    System.out.print("\nPlansza musi być przynajmniej 4x4!\n");
                }
            }
        }
        while (player1.getPlayer().isEmpty()) {
            System.out.print("Podaj nazwę gracza 1\n");
            Scanner in = new Scanner(System.in);
            String name = in.next();
            player1.setPlayer(name);
        }
        while (player2.getPlayer().isEmpty()) {
            System.out.print("Podaj nazwę gracza 2\n");
            Scanner in = new Scanner(System.in);
            String name = in.next();
            player2.setPlayer(name);
        }
    }

//********************************Game rules****************************
    public boolean play(String f, int player1disc, int player2disc) {
        if (f.equals("c")) {
            if (plansza.backCoin()) {
                turn = !turn;
                return true;
            }
        } else if (f.equals("s")) {

            SaveGame("saved.txt");
        } else {

            int k = Integer.parseInt(f) - 1;

            if (turn) {
                try {
                    if (plansza.putCoin(k, player1disc, this.PlayerNameTurn(turn))) {
                        turn = false;
                        return true;
                    }
                } catch (Exception e) {
                    System.out.print("\nNie ma takiej kolumny!\n");
                    return false;
                }
            } else {
                try {
                    if (plansza.putCoin(k, player2disc, this.PlayerNameTurn(turn))) {
                        turn = true;
                        return true;
                    }
                } catch (Exception e) {
                    System.out.print("\nNie ma takiej kolumny!\n");
                    return false;
                }
            }
        }
        return false;
    }

//********************************Actual gameplay****************************
    public void matchPlay() throws Exception {

        int player1disc = 1;
        int player2disc = 5;
        GameplaySetup();
        String f = "";
        while (!(f.equals("T")) & !(f.equals("N"))) {
            System.out.println("\nGracz " + player1.getPlayer() + ". Chcesz grać krążkami 1 czy zmienić na 5?(T-1/N-5)");//przetestowane
            Scanner in = new Scanner(System.in);
            f = in.next();
        }
        if (!(pickDisc(f))) {
            player1disc = 5;
            player2disc = 1;
        }

        while ((plansza.winner.isEmpty()) & (plansza.draw == false)) {

            plansza.printBoard();
            System.out.println("\nGracz " + this.PlayerNameTurn(turn) + ". Wybierz gdzie chcesz wrzucic krazek(1,2,..) c by cofnąć s by zapisać");//przetestowane
            Scanner in = new Scanner(System.in);
            f = in.next();
            play(f, player1disc, player2disc);

        }
        plansza.printBoard();
        if (plansza.draw) {
            System.out.print("\nRemis!!\n");
        } else {
            System.out.print("\nZwycięża " + plansza.winner + "!\n");
            if (plansza.winner == player1.getPlayer()) {
                player1.PlayerWin();
            } else if (plansza.winner == player2.getPlayer()) {
                player2.PlayerWin();
            }
        }
        f = "";
        while (!(f.equals("T")) & !(f.equals("N"))) {
            System.out.print(player1.getPlayer() + ": " + player1.getPlayerScore() + "  " + player2.getPlayer() + ": " + player2.getPlayerScore() + "\n");
            System.out.print("Czy gramy ponownie?(T/N)\n");
            Scanner in = new Scanner(System.in);
            f = in.next();
        }
        if (f.equals("T")) {
            matchPlay();
        }
    }


    public static void main(String args[]) throws Exception {

        Gameplay gameplay = new Gameplay();
        gameplay.matchPlay();
    }
}
