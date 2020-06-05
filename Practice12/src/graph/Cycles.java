package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Cycles {
    Digraph D;

 private enum color{white, grey, black};
 private class CycleException extends Throwable{}
 private color colors[];
 private boolean hasCycle;
    Cycles(Digraph D){
        this.D = D;
       // queue = new LinkedList<>();
        colors = new color[D.V()];
        try {
            for (int i = 0; i < D.V(); i++)
                if (colors[i] != color.black)
                    dfs(i);

        }
        catch(CycleException c){
            hasCycle = true;
        }
    }
    boolean hasCycle(){
        return hasCycle;
    }
    private void dfs(int v) throws CycleException {
        colors[v] = color.grey;
       for(int w : D.adj(v)){
           if(colors[w] == color.grey) throw new CycleException();
           if(colors[w] == color.white) dfs(w);
       }
        colors[v] = color.black;
    }
}
