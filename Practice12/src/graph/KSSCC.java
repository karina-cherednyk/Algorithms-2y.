package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Queue;

public class KSSCC {
    private Digraph D;
    private boolean marked[];
    private int[] id;
    private int count;

    public KSSCC(Digraph G){
        D = G;
        marked = new boolean[G.V()];
        id = new int[G.V()];
        Digraph r = G.reverse();
        Iterator<Integer> order = new TopologicOrder(r).order();
        while(order.hasNext()){
            int v = order.next();
            if (!marked[v]){
                dfs(v);
                count++;
            }
        }
    }

    private void dfs( int v){
        marked[v] = true;
        id[v] = count;
        for (int w : D.adj(v))
            if (!marked[w])
                dfs(w);
    }

    public boolean stronglyConnected(int v, int w){ return id[v] == id[w]; }
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fr = new FileInputStream("testFiles/test1.txt");
        Digraph D = new Digraph(fr);
        new KSSCC(D);
    }
}
