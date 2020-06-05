import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;


public class Main {
    private static class Edge implements  Comparable<Edge>{

        private final int v, w;
        private final double weight;

        public Edge(int v, int w, double weight){
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int either(){
            return v;
        }

        public int other(int vertex){
            if (vertex == v) return w;
            else return v;
        }

        public int compareTo(Edge that)	{
            if (this.weight < that.weight) return -1;
            else if (this.weight > that.weight) return +1;
            else return 0;
        }
    }
    public static class EdgeWeightedGraph {

        private final int V;
        private final HashSet<Edge>[] adj;
        public EdgeWeightedGraph(int V)	{
            this.V = V;
            adj = (HashSet<Edge>[]) new HashSet[V];
            for (int v = 0; v < V; v++)
                adj[v] = new HashSet<Edge>();
        }
        public void addEdge(Edge e){
            int v = e.either(), w = e.other(v);
            adj[v].add(e);
            adj[w].add(e);
        }
        public Iterable<Edge> adj(int v){
            return adj[v];
        }

        public int V() {
            return V;
        }
    }

    public static class LazyPrimMST {

        private boolean[] marked; // MST vertices
       // private Queue<Edge> mst; // MST edges
        private PriorityQueue<Edge> pq; // PQ of edges
        private int weight;
        int size;

        public LazyPrimMST(EdgeWeightedGraph G)	{
            pq = new PriorityQueue<Edge>();
          //  mst = new LinkedList<Edge>();
            marked = new boolean[G.V()];
            visit(G, 0);
            while (!pq.isEmpty() && size < G.V() - 1)	{
                Edge e = pq.remove();
                int v = e.either(), w = e.other(v);
                if (marked[v] && marked[w]) continue;
               // mst.add(e);
                size++;
                weight +=e.weight;
                if (!marked[v])
                    visit(G, v);
                if (!marked[w])
                    visit(G, w);
            }
        }

        private void visit(EdgeWeightedGraph G, int v)	{
            marked[v] = true;
            for (Edge e : G.adj(v))
                if (!marked[e.other(v)])
                    pq.add(e);
        }

//        public Iterable<Edge> mst()	{
//            return mst;
//        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arg;
        arg = br.readLine().split(" ");
        int V = Integer.parseInt(arg[0]);
        int E = Integer.parseInt(arg[1]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for(int i=0; i<E; i++){
            arg = br.readLine().split(" ");
            G.addEdge(new Edge(Integer.parseInt(arg[0])-1,Integer.parseInt(arg[1])-1,Integer.parseInt(arg[2])));
        }
        System.out.println((new LazyPrimMST(G)).weight);
    }
}
