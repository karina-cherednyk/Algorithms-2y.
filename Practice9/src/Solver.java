import java.util.*;

//Вирішувач.
public class Solver {
    private int moves;
    private ArrayDeque<Board> solution = new ArrayDeque<>();
    private class Node implements Comparable<Node>{
        private Node prev;
        private Board b;
        private int steps;
        private int prior;

        Node(Node prev, Board b){
            this.prev = prev;
            this.b = b;
            this.steps = prev.steps+1;
            this.prior = steps + b.manhattan();
        }
        Node(Board b){
            this.b = b;
            this.prior = steps + b.manhattan();
        }

        @Override
        public int compareTo(Node that) {
            return this.prior - that.prior;
        }
        public Collection<Node> neighbors(){
            List<Node> list = new LinkedList<>();
            for(Board n: b.neighbors()) list.add(new Node(this, n));
            return list;
        };
    }

    private Board initial;
    private int N;
    public Solver(Board initial)   {
        this.initial = initial;
        this.N = initial.dimension();
        if(isSolvable()) solve();
    }         // знайти рішення для дошки initial
    private void solve(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(initial));
        Node prev;
        do{
            prev = pq.poll();
            for( Node n : prev.neighbors()) pq.add(n);
        }
        while(!prev.b.isGoal());

        solution.push(prev.b);
        while(prev.prev!=null) {
            prev = prev.prev;
            solution.push(prev.b);
            moves++;
        }


    }


    public boolean isSolvable()        {
        int[] arr = create1DimArr();
        return countLinks(arr)%2 == 0;
    }     // чи має початкова дошка розв’язок
    private static int countLinks(int[] arr){
        int links = 0;
        int totalLinks = 0;
        int fst;
        int j;
        int N = arr.length-1;
        int prev;
        for(int i=1; i<=N; i++){
            fst = arr[i];
            if(fst==-1) continue;
            j = i;
            if(fst != j) {
                links = 1;
                j = arr[j];
                while (arr[j] != i) {
                   prev = j;
                    j = arr[j];
                    arr[prev] = -1;
                    links++;
                }
                arr[j] = -1;
            }
            else continue;
            totalLinks+=links;
        }
        return totalLinks;
    }
    public int[] create1DimArr(){
        int r1,c1;
        int[] one = new int[N*N+1];
        for(int i=0; i<N*N; i++){
            r1 = i/ N; c1 = i - r1*N;
            int k = initial.blocks[r1][c1];
           one[i+1] = k!=0 ? k : N*N;
        }
        return one;
    }
    public int moves()                     {
        return moves;
    } // мінімальна кількість кроків для вирішення дошки, -1 якщо немає рішення
    public Iterable<Board> solution()   {
        return solution;
    }    // послідовність дошок в найкоротшому рішенні; null якщо немає рішення

    public static void main(String[] args){
        int[][] a = {{5,1,3},
                     {6, 2, 4},
                     {8,7,0}};
        Board b = new Board(a);
        Solver s = new Solver(b);
        System.out.println(s.isSolvable());
    }
}