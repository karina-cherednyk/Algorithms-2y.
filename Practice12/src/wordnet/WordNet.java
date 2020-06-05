package wordnet;

import graph.DFS;
import graph.Digraph;

import java.io.*;
import java.util.*;

public class WordNet {

    Digraph D;

    private class Node{
        int num;
        String term;
        String description;
        Node(String i, String t, String d){
            num = Integer.parseInt(i);
            term = t;
            description = d;
        }
    }
    BufferedReader br;
    LinkedList<Node> list = new LinkedList<>();
    HashSet<String> nouns = new HashSet<>();

    // конструктор приймає назви двох файлів
    public WordNet(String synsets, String hypernyms) throws IOException {
        br = new BufferedReader(new FileReader(synsets));
        fillMap();
        D = new Digraph(hypernyms,list.size());
    }
    private void fillMap() throws IOException {
        String line;
        String[] term;
        while((line = br.readLine())!=null){
            term = line.split(",");
            assert(term.length==3);
            list.add(new Node(term[0],term[1],term[2]));
            String[] defs = term[1].split(" ");
            Collections.addAll(nouns,defs);
        }
        br.close();
    }
    // множина іменників, що повертається як ітератор (без дублікатів)
    public Iterable<String> nouns(){
        return nouns;
    }
    // чи є слово серед WordNet іменників?
    public boolean isNoun(String word){
         return nouns.contains(word);
    }
    // відстань між nounA і nounB
    public int distance(String nounA, String nounB){
        int[] distances =  (new DFS(D,getNum(nounA))).getDistances();
        return distances[getNum(nounB)];
    }
    int getNum(String nounA){
        int n1 = -1;
        for(Node n : list)
            if(n.term.equals(nounA)) return n.num;
        for(Node n : list)
            if(Arrays.stream(n.term.split(" ")).anyMatch(nounA::equals))
                return n.num;

        System.out.println(nounA);
        throw new IllegalArgumentException();
    }
    String getTerm(int num){
        for(Node n : list)
            if(n.num == num) return n.term;
        throw new IllegalArgumentException();
    }
    // синсет що є спільним предком nounA і nounB ​
    // в найкоршому шляху до предка
    public String sap(String nounA, String nounB){
        if(!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
      SAP sap =   new SAP(D);
      int term = sap.ancestor(getNum(nounA), getNum(nounB));
      return getTerm(term);
    }
    public int  length(String nounA, String nounB){
        if(!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        SAP sap =   new SAP(D);
        int length = sap.length(getNum(nounA), getNum(nounB));
        return length;
    }
    // тестування
    public static void main(String[] args) throws IOException {
        WordNet wn = new WordNet("testFiles/synsets.txt","testFiles/hypernyms.txt");
        System.out.println(wn.isNoun("water"));
        System.out.println(wn.isNoun("soda"));
    }
}
