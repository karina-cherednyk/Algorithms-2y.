import java.util.Stack;

public class Degrees {

    Digraph G;
    Degrees(Digraph G){
        this.G = G;
    }
    int indegree(int v){
        return G.inDegree(v);
    }
    int outdegree(int v){
        return G.outDegree(v);
    }
    Iterable<Integer> sources(){
        Stack<Integer> res = new Stack<>();
        for(int i=0; i<G.V; i++)
            if(indegree(i)==0 && outdegree(i)!=0) res.push(i);

        return res;
    }
    Iterable<Integer> sinks(){
        Stack<Integer> res = new Stack<>();
        for(int i=0; i<G.V; i++)
            if(indegree(i)!=0 && outdegree(i)==0) res.push(i);

        return res;
    }
    boolean isMap(){
        for(int i=0; i<G.V; i++)
            if(outdegree(i)!=1) return false;
         return true;
    }
}
