import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {
    final int[][] blocks;
    int N;

    public Board(int[][] blocks)    {
        this.blocks = blocks;
        this.N = blocks.length;
    }       // конструюємо дошку у вигляді двовимірного масиву N на N
    // (blocks[i][j] =блок в ряду i, колонці j)
    public int dimension()             {
        return N;
    }    // розмірність дошки N
    public int hamming()       {
        int r,c;
        int count = 0;
        for(int i=0; i<N-1; i++){
            r = i/N; c = i - r*N;
            if(blocks[r][c] != i+1) count++;
        }
     //   if(blocks[N-1][N-1] != 0) count++;
        return count;
    }            // кількість блоків не на своєму місці
    public int manhattan()      {
        int r1,r2,c1,c2;
        int count = 0;
        for(int i=0; i<N*N-1; i++){
            r1 = i/N; c1= i-r1*N;
            int j = blocks[r1][c1] - 1; //correct position
            r2 = j/N; c2= j-r2*N;
            count+=(abs(r1-r2) + abs(c1-c2));
        }
        return count;
    }           // сума Манхатенських відстаней між блоками і цільовим станом

    int abs(int a){
        return a>0 ? a: -1;
    }

    public boolean isGoal()       {
        int r1, c1;
        for(int i=0; i<N*N-1; i++){
             r1 = i/ N; c1 = i - r1*N;
            if(blocks[r1][c1] != i+1) return false;
        }
        return true;
    }         // чи є ця дошка цільовим станом
    public boolean equals(Object y)   {
            if(y.getClass() != this.getClass()) return false;
            y = (Board)y;
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++) if(blocks[r][c] != ((Board) y).blocks[r][c]) return false;
            }
            return true;
    }     // чи ця дошка рівна y?
    public Iterable<Board> neighbors()   {
            int r0=0, c0=0;
            //find position of 0
            boolean br = false;
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    if(blocks[r][c] == 0) {r0 = r; c0 = c; br =true; break;}
                }
                if(br)break;
            }
            //find all Neighbors
        List<Board> boards = new LinkedList<>();
        if(r0-1>=0) boards.add(new Board(getBoard(r0,c0,r0-1,c0)));
        if(r0+1<N) boards.add(new Board(getBoard(r0,c0,r0+1,c0)));
        if(c0-1>=0) boards.add(new Board(getBoard(r0,c0,r0,c0-1)));
        if(c0+1<N) boards.add(new Board(getBoard(r0,c0,r0,c0+1)));
        return boards;
    }
    private int[][] getBoard(int r0, int c0, int r1, int c1){
        int[][] res = Arrays.stream(blocks).map(int[]::clone).toArray(int[][]::new);;
        res[r0][c0] = blocks[r1][c1];
        res[r1][c1] = 0;
        return res;
    }
    // всі сусiдні дошки
    public String toString()    {
        StringBuilder sb = new StringBuilder();
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++)
                sb.append(blocks[r][c] +" ");
            sb.append("\n");
        }
        return sb.toString();
    }           // строкове подання
}