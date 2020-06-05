import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Paths{
    private boolean[] visitedBFS;
    private boolean[] visitedDFS;
    private int[] edgeToBFS;
    private int[] edgeToDFS;
    private int[] distToBFS;
    private int[] distToDFS;
    private final int s;
    private final Graph G;
    Paths(Graph G, int s){
        this.G = G;
        this.s = s;
        visitedBFS = new boolean[G.V()];
        visitedDFS = new boolean[G.V()];
        edgeToBFS = new int[G.V()];
        edgeToDFS = new int[G.V()];
        distToBFS = new int[G.V()];
        distToDFS = new int[G.V()];
        for(int i=0; i<G.V(); i++)
            edgeToBFS[i] = edgeToDFS[i] = distToBFS[i] = distToDFS[i] = -1;
        distToBFS[s] = distToDFS[s] = 0;
        bfs();
        dfs();

    }
    boolean hasPathTo(int v){
        return visitedBFS[v];
    }
    Iterable<Integer>pathToBFS(int v){
        if(!hasPathTo(v)) return null;
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.push(v);
        do{
            v = edgeToBFS[v];
            ad.push(v);
        }while(v!=s);
        return ad;
    }
    Iterable<Integer>pathToDFS(int v){
        if(!hasPathTo(v)) return null;
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.push(v);
        do{
            v = edgeToDFS[v];
            ad.push(v);
        }while(v!=s);
        return ad;
    }
    int distanceToDFS(int v){
        return distToDFS[v];
    }
    int distanceToBFS(int v){
        return distToBFS[v];
    }
    private void bfs(){
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visitedBFS[s] = true;
        while(!stack.isEmpty()){
            int v = stack.pop();
            for(Integer w : G.adj(v)){
                if(visitedBFS[w]) continue;
                visitedBFS[w] = true;
                edgeToBFS[w] = v;
                distToBFS[w] = distToBFS[v] + 1;
                stack.push(w);
            }
        }
    }
    private void dfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visitedDFS[s] = true;
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(Integer w : G.adj(v)){
                if(visitedDFS[w]) continue;
                visitedDFS[w] = true;
                edgeToDFS[w] = v;
                distToDFS[w] = distToDFS[v] + 1;
                queue.add(w);
            }
        }
    }
}
