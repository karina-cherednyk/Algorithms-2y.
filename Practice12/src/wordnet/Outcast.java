package wordnet;

import graph.DFS;
import graph.Digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Outcast {
    WordNet wn;
    Digraph D;
    // конструктор приймає об’єкт WordNet ​
    public Outcast(WordNet wordnet){
        wn = wordnet;
        D = wn.D;
    }

// маючи масив WordNet іменників, повернути «ізгоя»​

    public String outcast(String[] nouns){

       int[] dist = new int[nouns.length];
        int n=0;
       for(int i=0; i<nouns.length; i++){
           for(int j=0;j<nouns.length; j++){
               n = wn.length(nouns[i],nouns[j]);
               if(n==-1) dist[i] +=10000;
               else dist[i]+=n;
           }
       }
       int outcast =0;
       for(int i=1; i<nouns.length; i++)
           if(dist[i]>dist[outcast]) outcast = i;

    return nouns[outcast];
    }

    public static void main(String[] args) throws IOException {
        String synsets = "testFiles/synsets.txt";
        String hypernyms = "testFiles/hypernyms.txt";
        WordNet wordnet = new WordNet(synsets,hypernyms);

        Outcast outcast = new Outcast(wordnet);
        String[] files = {"testFiles/outcast1.txt","testFiles/outcast2.txt","testFiles/outcast3.txt"};

        for (int t = 0; t < files.length; t++) {

            String[] nouns = readStrings(files[t]);

            System.out.println(files[t] + ": " + outcast.outcast(nouns));

        }

    }
    static String[] readStrings(String str) throws FileNotFoundException {
        List<String> list = new LinkedList<>();
        Scanner sc  = new Scanner(new FileInputStream(str));
        while(sc.hasNext()) Collections.addAll(list, sc.nextLine().split(" "));
        return list.toArray(String[]::new);
    }
}
