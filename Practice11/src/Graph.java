import java.io.InputStream;
import java.util.*;

public class Graph {
    HashSet<Integer>links[];
    int width;
    int height;
    private int V;
    private int E;
    Graph(int V){
        links = (HashSet<Integer>[])new HashSet[V];
        this.V = V;
    }
    Graph(InputStream in){
        Scanner s = new Scanner(in);

        width = s.nextInt();
        height = s.nextInt();
        int E = s.nextInt();
        V = width*height;
        links = (HashSet<Integer>[])new HashSet[V];
        for(int i=0; i<V; i++)
            links[i] = new HashSet<Integer>();
        int j=0;
        for(int i=0; i<E; i++)
            addEdge(s.nextInt(),s.nextInt());

    }
    void addEdge(int v, int w){

            links[v].add(w);
            links[w].add(v);
            E++;

    }
    Iterable<Integer>adj(int v){
        return links[v];
    }
    int V(){
        return V;
    }
    int E(){
        return E;
    }
    static int degree(Graph G, int v){
        int d=0;
        for(int w: G.adj(v))
            d++;
        return d;
    }
}


