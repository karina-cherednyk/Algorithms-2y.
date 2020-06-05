package graph;


public class DFS {
    private Digraph D;
    private boolean marked[];
    private int group[];
    private int[] distances;
    private int count;

    DFS(Digraph D){
        this.D = D;
        marked = new boolean[D.V()];
        group = new int[D.V()];
        distances = new int[D.V()];
        for(int i=0; i<D.V(); i++) distances[i] = -1;
        for(int i=0; i<D.V(); i++){
            if(!marked[i]) {
                marked[i] = true;
                dfs(i);
            }
            count++;
        }
    }
    private void dfs(int v){
        for(int w : D.adj(v))
            if(!marked[w]) {
                marked[w] = true;
                group[w] = count;
                distances[w] = distances[v]+1;
                dfs(w);
            }
    }



    public DFS(Digraph D, int s){
        distances = new int[D.V()];
        marked = new boolean[D.V()];
        group = new int[D.V()];
        for(int i=0; i<D.V(); i++) distances[i] = -1;
        distances[s] = 0;
        marked[s] = true;
        this.D = D;
        dfs(s);
        int k=0;
    }
    public int[] getDistances(){
        return distances;
    }
}
