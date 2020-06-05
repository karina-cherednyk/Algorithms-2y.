import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CycleGraph {
    private Graph G;
    CycleGraph(Graph G){
        this.G = G;
    }
    boolean hasEulerCycle(){
        if( !(new DFS(G)).oneComponent()) return false;
        for(int i=0; i<G.V(); i++)
            if(G.adjCount(i) % 2 == 1) return false;
        return true;
    }
    LinkedList<Integer>[] copyLinks(){
        LinkedList<Integer>[] links = (LinkedList<Integer>[])new LinkedList[G.V()];
        for(int i=0; i<G.V(); i++)
            links[i] = new LinkedList<Integer>(G.list(i));
        return links;
    }

    Iterable<Integer> getEulerCycle(){
        int size = G.countE();
        ArrayDeque<Integer> path = new ArrayDeque<>();
        ArrayDeque<Integer> cycle = new ArrayDeque<>();
        LinkedList<Integer>[] links = copyLinks();
        int cnt=0;

        int begin = 0;
        path.push(begin);
        while(true){
            Integer v = path.peek();
            Integer w = links[v].removeFirst();
            links[w].remove(v);
            if(w==begin){
                cycle.push(w);
                while(path.size()!=0){
                    int last = path.removeFirst();
                    if(links[last].size() == 0)cycle.push(last);
                    else {
                        path.push(last);
                        begin = last;
                        break;
                    }
                }
                if(path.size()==0) break;
            }
            else path.push(w);
        }

        assert(cycle.getFirst() == cycle.getLast());
        return cycle;
    }
    Iterable<Integer> getHamiltonCycle(){
        ArrayDeque<Integer> path = new ArrayDeque<>();
        int V = G.V();
        boolean marked[] = new boolean[V];
        int mCount=0;
        int v0 = 0;
        int v = v0;
        path.push(v);
        marked[v] = true;
        mCount++;
        while(mCount!=V){
            int w= -1;
            for(int w1 : G.adj(v))
                if(!marked[w1]) { w = w1; break; }
            assert(w!=-1);
            v = w;
            path.push(v);
            marked[v] = true;
            mCount++;
        }
        path.push(v0);
        return path;
    }

    public static void main(String[] args) throws IOException {
        String test = "testFiles/eulear.txt";
        Graph G = new Graph(new FileInputStream(test));
        CycleGraph cg = new CycleGraph(G);
        boolean has = cg.hasEulerCycle();
        System.out.println("Graph1 has euler cycle: "+has);
        if(has){
            Iterable<Integer> cycle= cg.getEulerCycle();
            for(int v : cycle)
                System.out.print(v+" - ");
        }

        System.out.println("\n ---------------------------------- ");
        String test2 = "testFiles/hamilton.txt";
        Graph G2 = new Graph(new FileInputStream(test2));
        CycleGraph cg2 = new CycleGraph(G2);
        Iterable<Integer> cycle= cg2.getHamiltonCycle();
        for(int v : cycle)
            System.out.print(v+" - ");
    }
}
