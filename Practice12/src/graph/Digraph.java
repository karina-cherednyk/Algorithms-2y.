package graph;

import wordnet.WordNet;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Digraph {
    protected int V;
    protected int E;
    protected HashSet<Integer>[] edges;
    public Digraph(InputStream in){
        Scanner s = new Scanner(in);
        V = s.nextInt();
        E = s.nextInt();
        edges = (HashSet<Integer>[])new HashSet[V] ;
        for(int i = 0; i< V; i++) edges[i] = new HashSet<Integer>();

        for(int i=0;i<E;i++)
            join(s.nextInt(),s.nextInt());
    }
    public Digraph(String s,int V) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        edges = (HashSet<Integer>[])new HashSet[V] ;
        for(int i = 0; i< V; i++) edges[i] = new HashSet<Integer>();

        String line;
        String[] term;
        this.V = V;
        while((line = br.readLine())!=null){
            term = line.split(",");
            int v = Integer.parseInt(term[0]);
            for(int w=1;w<term.length;w++)
                join(v, Integer.parseInt(term[w]));
        }
        br.close();
    }
    Digraph(int V){
        this.V = V;
        edges = (HashSet<Integer>[])new HashSet[V] ;
        for(int i = 0; i< V; i++) edges[i] = new HashSet<Integer>();
    }
    protected void join(int v, int w){
        edges[v].add(w);
        E++;
    }
    public int V(){
        return V;
    }
    int E(){
        return E;
    }
    Iterable<Integer>adj(int v){
        return edges[v];
    }
    Digraph reverse(){
        Digraph reversed = new Digraph(V);
        for(int v=0; v<V; v++)
            for(int w : adj(v)) {
                reversed.join(w, v);
                reversed.E++;
            }
        return reversed;
    }
}
