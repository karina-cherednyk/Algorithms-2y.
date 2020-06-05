import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;

public class CycleDigraph {
    private Digraph G;
    CycleDigraph(Digraph G){
        this.G = G;
    }
    boolean hasEulerCycle(){
        if( !(new DFS(G)).oneComponent()) return false;
        for(int i=0; i<G.V(); i++)
            if(G.inDegree(i) != G.outDegree(i)) return false;
        return true;
    }
    LinkedList<Integer>[] copyLinks(){
        LinkedList<Integer>[] links = (LinkedList<Integer>[])new LinkedList[G.V()];
        for(int i=0; i<G.V(); i++)
            links[i] = new LinkedList<Integer>(G.list(i));
        return links;
    }

    Iterable<Integer> getEulerCycle(){
        ArrayDeque<Integer> path = new ArrayDeque<>();
        ArrayDeque<Integer> cycle = new ArrayDeque<>();
        LinkedList<Integer>[] links = copyLinks();
        int cnt=0;

        int begin = 0;
        path.push(begin);
        while(true){
            Integer v = path.peek();
            Integer w = links[v].removeFirst();
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
        Iterator<Integer> path = (new TopologicOrder(G)).order();
        LinkedList<Integer> res = new LinkedList<>();
        boolean first = true;
        int v0 = -1;
        while (path.hasNext()){
            if(first){
                v0 = path.next();
                res.add(v0);
                first = false;
            }
            else {
                int v  = path.next();
                if(!G.isEdge(v0,v)) return null;
                v0 = v;
                res.add(v0);
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        String test = "testFiles/eulear2.txt";
        Digraph G = new Digraph(new FileInputStream(test));
        CycleDigraph cg = new CycleDigraph(G);
        boolean has = cg.hasEulerCycle();
        System.out.println("Graph1 has euler cycle: "+has);
        if(has){
            Iterable<Integer> cycle= cg.getEulerCycle();
            for(int v : cycle)
                System.out.print(v+" - ");
        }

        System.out.println("\n ---------------------------------- ");
        String test2 = "testFiles/hamilton2.txt";
        Digraph G2 = new Digraph(new FileInputStream(test2));
        CycleDigraph cg2 = new CycleDigraph(G2);
        Iterable<Integer> cycle= cg2.getHamiltonCycle();
        for(int v : cycle)
            System.out.print(v+" - ");
    }
}
