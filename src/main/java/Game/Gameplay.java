package Game;

import java.io.*;
import java.util.Scanner;

public class Gameplay {
    public Player player1=new Player("",0);
    public Player player2=new Player("",1);
    public Board plansza;

private boolean turn=true;

    public void SaveGame(String filename){
        String text="";
       text= text.concat(player1.getPlayer()+",");
        text= text.concat(player1.getPlayerScore()+",");
       text= text.concat(player2.getPlayer()+",");
        text= text.concat(player2.getPlayerScore()+"");
        text= text.concat(plansza.printBoardString()+";");


       BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter( new FileWriter( filename));
            writer.write( text);

        }
        catch ( IOException e)
        {
        }
        finally
        {
            try
            {
                if ( writer != null)
                    writer.close( );
            }
            catch ( IOException e)
            {
            }
        }
    }
public boolean play(String f, int player1disc, int player2disc)throws Exception {
    if (f.equals("c")) {
        if (plansza.backCoin()) {
            turn = !turn;
            return true;}
        }else
            if (f.equals("s")) {

            SaveGame("saved.txt");
        } else {
        int k = Integer.parseInt(f) - 1;
        if (turn) {
            if (plansza.putCoin(k, player1disc, this.PlayerNameTurn(turn))) {
                turn = false;
                return true;
            }
        } else {
            if (plansza.putCoin(k, player2disc, this.PlayerNameTurn(turn))) {
                turn = true;
                return true;
            }
        }
    }
    return false;
}
    public int StandardBoard(String w) {
        if (w.equals("1")){
            return 1;
        }else if (w.equals("2")) {
            return 2;
        }else{
            System.out.print("\nZła opcja!\n");
          return 0;
        }
    }
public String PlayerNameTurn(boolean turn){
        if(turn){
            return player1.getPlayer();
        }
        else{
            return player2.getPlayer();
        }

}

public void matchPlay()throws Exception {

    int player1disc = 1;
    int player2disc = 5;
GameplaySetup();
    while( (plansza.winner.isEmpty())&(plansza.draw==false)){//przetestowane

        plansza.printBoard();//przetestowane

        System.out.println("\nGracz " + this.PlayerNameTurn(turn) + ". Wybierz gdzie chcesz wrzucic krazek(1,2,..) c by cofnąć");//przetestowane
        Scanner in = new Scanner(System.in);
        String f = in.next();
        play(f,player1disc,player2disc);//przetestowane

    }
    plansza.printBoard();
    if (plansza.draw){//przetestowane
        System.out.print("\nRemis!!\n");
    }else{
        System.out.print("\nZwycięża "+plansza.winner+"!\n");//przetestowane
        if(plansza.winner==player1.getPlayer()){
            player1.PlayerWin();
        }else if(plansza.winner==player2.getPlayer()){
            player2.PlayerWin();
        }
    }
    String f="";
    while(!(f.equals("T"))&!(f.equals("N")))
{
    System.out.print(player1.getPlayer()+": "+player1.getPlayerScore()+"  "+player2.getPlayer()+": "+player2.getPlayerScore()+"\n");
    System.out.print("Czy gramy ponownie?(T/N)\n");
    Scanner in = new Scanner(System.in);
    f = in.next();}
    if(f.equals("T")){
        matchPlay();
    }
    }


    public void GameplaySetup()throws Exception {
        plansza = new Board();
        int k=0;
        while (k==0){
            System.out.print("Czy chcesz grać na standartowej planszy?(1-Tak/2-Nie)");
            Scanner in = new Scanner(System.in);
            String w = in.next();
            k=StandardBoard(w);//przetestowane
        }
        if (k==2){
            System.out.print("Podaj rozmiary planszy\n");
            Scanner in = new Scanner(System.in);
            int column = in.nextInt();
            int row=in.nextInt();
            plansza.setBoardSize(column,row);//przetestowane
        }
while(player1.getPlayer().isEmpty()){
    System.out.print("Podaj nazwę gracza 1\n");
    Scanner in = new Scanner(System.in);
    String name = in.next();
    player1.setPlayer(name);//przetestowane
}
        while(player2.getPlayer().isEmpty()){
            System.out.print("Podaj nazwę gracza 2\n");
            Scanner in = new Scanner(System.in);
            String name = in.next();
            player2.setPlayer(name);//przetestowane
        }

    }
    public static void main(String args[]) throws Exception {

        Gameplay gameplay = new Gameplay();
        gameplay.matchPlay();


    }
}
