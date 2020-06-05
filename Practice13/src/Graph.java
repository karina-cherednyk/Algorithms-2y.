import java.io.*;
import java.util.*;

public class Graph extends Digraph{


    Graph(InputStream in) throws IOException {
      super(in);
    }
    public Graph(String s,int V) throws IOException {
       super(s,V);
    }
    Graph(int V){
        super(V);
    }
    public void join(int v, int w){

        links[v].add(w);
        links[w].add(v);
        E++;

    }

}


