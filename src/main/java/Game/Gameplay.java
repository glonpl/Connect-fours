package Game;

import java.util.Scanner;

public class Gameplay {
    public Player player1=new Player("",0);//testowane wczesniej TW
    public Player player2=new Player("",1);//TW
    public Board plansza;//TW

private boolean turn=true;
    public int StandardBoard(String w) { //0wrong 1 standard  2change
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
    while( (plansza.winner.isEmpty())&(plansza.draw==false)){

        plansza.printBoard();

        System.out.println("\nGracz " + this.PlayerNameTurn(turn) + ". Wybierz gdzie chcesz wrzucic krazek(1,2,..) c by cofnąć");
        Scanner in = new Scanner(System.in);
        String f = in.next();
if(f.equals("c")){
    if (plansza.backCoin()){
    turn=!turn;}
}else {
    int k = Integer.parseInt(f)-1;
    if (turn) {
        if (plansza.putCoin(k, player1disc, this.PlayerNameTurn(turn))) {
            turn = false;

        }
    } else {
        if (plansza.putCoin(k, player2disc, this.PlayerNameTurn(turn))) {
            turn = true;

        }
    }
}
    }
    plansza.printBoard();
    if (plansza.draw){
        System.out.print("Remis!!\n");
    }else{
        System.out.print("Zwycięża "+plansza.winner+"!\n");
    }
    String f="";
    while(!(f.equals("T"))&!(f.equals("N")))
{
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
            k=StandardBoard(w);
        }
        if (k==2){
            System.out.print("Podaj rozmiary planszy\n");
            Scanner in = new Scanner(System.in);
            int column = in.nextInt();
            int row=in.nextInt();
            plansza.setBoardSize(column,row);
        }
while(player1.getPlayer().isEmpty()){
    System.out.print("Podaj nazwę gracza 1\n");
    Scanner in = new Scanner(System.in);
    String name = in.next();
    player1.setPlayer(name);
}
        while(player2.getPlayer().isEmpty()){
            System.out.print("Podaj nazwę gracza 2\n");
            Scanner in = new Scanner(System.in);
            String name = in.next();
            player2.setPlayer(name);
        }

    }
    public static void main(String args[]) throws Exception {

        Gameplay gameplay = new Gameplay();
        gameplay.matchPlay();


    }
}
