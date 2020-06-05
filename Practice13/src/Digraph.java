import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Digraph {
    protected int V;
    protected int E;
    protected HashSet<Integer>[] links;
    Digraph(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        V = Integer.parseInt(br.readLine());

        links = (HashSet<Integer>[])new HashSet[V];
        for(int i=0; i<V; i++)
            links[i] = new HashSet<Integer>();

        for(int i=0; i<V; i++) {
            String[] ws = br.readLine().split(" ");
            for(int j=0; j<ws.length; j++)
                join(i, Integer.parseInt(ws[j]));

        }
        br.close();
    }
    public Digraph(String s,int V) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        links = (HashSet<Integer>[])new HashSet[V] ;
        for(int i = 0; i< V; i++) links[i] = new HashSet<Integer>();

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
    int adjCount(int v){
        int n =  links[v].size();
        //for loop
        if(links[v].contains(v)) n++;
        return n;
    }
    int inDegree(int v){
        return links[v].size();
    }
    int outDegree(int v){
        int count = 0;
        for(int i=0; i<V; i++)
            if(links[i].contains(v)) count++;
        return count;
    }

    Set<Integer> list(int v){
        return links[v];
    }


    int countE(){
        int count=0;
        for(int i=0; i<V; i++)
            count+=adjCount(i);
        return count/2;
    }

    public boolean isEdge(int v0, int v) {
        return links[v0].contains(v);
    }
}