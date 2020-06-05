import java.io.*;
import java.util.*;
import edu.princeton.cs.introcs.StdDraw;

public class Labyrinth {
    int s = 0;
    int l;
    private int cols;
    private int rows;
    private int height;
    private int width;
    private Graph G;
    private Paths P;
    static Random r = new Random();
    private int pad = 1;
    private int margin = 2;
    private Iterable<Integer> bfs;
    private Iterable<Integer> dfs;
    Labyrinth(InputStream in){
      G = new Graph(in);
      cols = G.width;
      rows = G.height;
      width = 2*cols+1;
      height = 2*rows+1;
      P = new Paths(G,s);
     // l = r.nextInt(G.E()-1)+1;
        l = r.nextInt(G.E()-1)+1;
      bfs = P.pathToBFS(l);
      dfs = P.pathToDFS(l);
      drawPath();

    }
    void drawRect(double x, double y,double width,double height){
        StdDraw.filledRectangle(pad+x + width/2, pad+y - height/2,width/2, height/2);
    }
    void drawLine(double x1, double y1, double x2, double y2,double t){
        StdDraw.line(pad+x1+0.5,pad+y1-0.5,pad+x2+0.5,pad+y2-0.5);
    }

    private void drawPath() {
        StdDraw.setYscale(0,height+2*pad);
        StdDraw.setXscale(0,2*width+margin+2*pad);

       // StdDraw.setPenColor(StdDraw.BLACK);

       // drawRect(0,height,width,height);
       // drawRect(width+margin,height,width,height);


        StdDraw.setPenColor(StdDraw.BLACK);
        double thickness = 0.05;
        StdDraw.setPenRadius(thickness);
        int x1,x2,y1,y2;
        for(int v=0; v<G.V(); v++){
            x1 = (v%cols)*2 + 1;
            y1 = (v/cols)*2 + 2;
            drawRect(x1,y1,1,1);
            drawRect(x1+margin+width,y1,1,1);
            for(Integer w : G.adj(v)) {
                connectVerticesBFS(v, w, thickness);
                connectVerticesDFS(v, w, thickness);
            }
        }
        StdDraw.setPenColor(StdDraw.RED);
        if(P.hasPathTo(l)){
            int prev = s;
            for(int w : bfs){
                connectVerticesBFS(prev,w, thickness);
                prev = w;
            }
            prev = s;
            for(int w : dfs){
                connectVerticesDFS(prev,w, thickness);
                prev = w;
            }
            System.out.println("Path to point "+l+" from "+s+": ");
            System.out.println("Using bfs: "+P.pathToBFS(l) +", distance:"+P.distanceToBFS(l));
            System.out.println("Using dfs: "+P.pathToDFS(l)+", distance:"+P.distanceToDFS(l));
        }
        else System.out.println("Path to point "+l+" from "+s+" not found");
    }
    void connectVerticesBFS(int v, int w,double thickness){
        int x1 = (v%cols)*2 + 1;
        int y1 = (v/cols)*2 + 2;
        int x2 = (w%cols)*2 + 1;
        int y2 = (w/cols)*2 + 2;
        drawLine(x1,y1,x2,y2,thickness);
    }
    void connectVerticesDFS(int v, int w,double thickness){
        int x1 = (v%cols)*2 + 1;
        int y1 = (v/cols)*2 + 2;
        int x2 = (w%cols)*2 + 1;
        int y2 = (w/cols)*2 + 2;
        drawLine(x1+margin+width,y1,x2+margin+width,y2,thickness);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Labyrinth(new FileInputStream("labyrinths/lab.txt"));
    }
}
