package Game;

import java.io.*;
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
    public void SaveToFile(String filename) {
        String text = "";
        int i=0;
        while (i!=list.size()){
            text = text.concat(list.get(i).getPlayer()+":"+Integer.toString(list.get(i).getPlayerScore())+"\n");
            i+=1;
        }
        try(FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(text);

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
    public void ReadFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}