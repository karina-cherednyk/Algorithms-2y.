import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Digraph {
    protected int V;
    protected int E;
    protected HashSet<Integer>[] links;

    Digraph(int V){
        this.V = V;
        links = (HashSet<Integer>[])new HashSet[V] ;
        for(int i = 0; i< V; i++) links[i] = new HashSet<Integer>();
    }
    protected void join(int v, int w){
        links[v].add(w);
        E++;
    }
    public int V(){
        return V;
    }
    int E(){
        return E;
    }
    Iterable<Integer>adj(int v){
        return links[v];
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


    Set<Integer> list(int v){
        return links[v];
    }

    public boolean isEdge(int v0, int v) {
        return links[v0].contains(v);
    }
}
