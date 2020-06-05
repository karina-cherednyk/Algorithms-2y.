import java.util.Stack;

public class DFS {
    private Digraph G;
    private boolean[] visited;
    private int[] markers;
    private int marker = 1;
    DFS(Digraph G){
        this.G = G;
        visited = new boolean[G.V()];
        markers = new int[G.V()];
        for(int i=0;i<G.V(); i++){
            if(visited[i]) continue;
            bfs(i);
            marker++;
        }
    }

    boolean oneComponent(){
        for(int i=0;i<G.V(); i++)
            if(markers[i] != 1) return false;

        return true;
    }

    private void bfs(int s){
        Stack<Integer> stack = new Stack<>();
        visited[s] = true;
        markers[s] = marker;
        stack.push(s);
        while(!stack.isEmpty()){
            int v = stack.pop();
            for(Integer w : G.adj(v)){
                if(visited[w]) continue;
                visited[w] = true;
                markers[w] = marker;
                stack.push(w);
            }
        }
    }
}
