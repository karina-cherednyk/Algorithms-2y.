package graph;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class TopologicOrder {
    private Digraph D;
    private LinkedList<Integer> queue;
    private boolean marked[];
    TopologicOrder(Digraph D){
        this.D = D;
        queue = new LinkedList<>();
        marked = new boolean[D.V()];
        for(int i=0; i<D.V();i++)
            if(!marked[i]) dfs(i);


    }
    private void dfs(int v){
        marked[v] = true;
        for(int w : D.adj(v))
            if(!marked[w])
                dfs(w);

        queue.add(v);
    }
    Iterator<Integer> order(){
        return queue.descendingIterator();
    }
}
