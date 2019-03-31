package Game;

import java.util.ArrayList;

public class Stats {
    protected static ArrayList<Player> list;
    Stats(){
       reset();
    }

    void reset(){list=new ArrayList<Player>(10);}
    void Add(Player e){list.add(e);}
    void PrintAll(){
     int i=0;
        while (i!=list.size()){
        System.out.println(list.get(i).getPlayer()+":"+Integer.toString(list.get(i).getPlayerScore()));
        i+=1;
        }
    }
}