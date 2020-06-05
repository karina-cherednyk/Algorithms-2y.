import java.awt.*;
import java.io.*;

import edu.princeton.cs.introcs.StdDraw;

public class EulerianGraph {
    class Vertex{
        int x; int y;
        final static double radius = 0.5;
        Vertex(int x0, int y0){
            x=x0; y=y0;
        }
        public String toString(){
           return "{"+x+", "+y+"}" ;
        }
    }
    private Vertex[] coords;
    private Graph G;
    private int maxX, maxY;
    EulerianGraph(InputStream graph, InputStream coord) throws IOException {
        G = new Graph(graph);
        BufferedReader br = new BufferedReader(new InputStreamReader(coord));
        int V = Integer.parseInt(br.readLine());
        if(V!=G.V()) throw new IllegalArgumentException();
        coords = new Vertex[V];

        for(int i=0; i<V; i++) {
            String[] c = br.readLine().split(" ");
            int x = Integer.parseInt(c[0]);
            int y = Integer.parseInt(c[1]);
            coords[i] =  new Vertex(x, y);
            if(x>maxX) maxX = x;
            if(y>maxY) maxY = y;
        }
        br.close();
    }
    void show(){
        StdDraw.setYscale(0,maxY+2);
        StdDraw.setXscale(0,maxX+2);
        Vertex vrt;

        for(int v=0; v<G.V(); v++)
            for(int w : G.adj(v))
                join(v,w);


        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.01);
        CycleGraph cg = new CycleGraph(G);
        if(!cg.hasEulerCycle()) return;
        Iterable<Integer> eulerPath = cg.getEulerCycle();
        boolean first = true;
        int v0 = -1;
        for(int v : eulerPath) {
            if (first) {
                v0 = v;
                first = false;
            } else {
                join1(v0, v);
                v0 = v;
            }
        }
        StdDraw.setPenColor(Color.BLACK);
        for(int i=0; i<G.V(); i++) {
            vrt = coords[i];
            StdDraw.filledCircle(vrt.x, vrt.y, Vertex.radius);
            StdDraw.text(vrt.x,vrt.y, i+"");
        }
        StdDraw.setPenColor(Color.WHITE);
        for(int i=0; i<G.V(); i++) {
            vrt = coords[i];
            StdDraw.text(vrt.x,vrt.y, i+"");
        }
        for(int v : eulerPath)
            System.out.print(v+" - ");
    }
    private void join(int v, int w){
        Vertex vrt1 = coords[v];
        Vertex vrt2 = coords[w];
        StdDraw.line(vrt1.x, vrt1.y,vrt2.x,vrt2.y);
    }
    private void join1(int v, int w){
        Vertex vrt1 = coords[v];
        Vertex vrt2 = coords[w];
        StdDraw.line(vrt1.x, vrt1.y,vrt2.x,vrt2.y);
        StdDraw.show(1000);
    }
    public static void main(String[] args) throws IOException {
        String test = "testFiles/eulear.txt";
        String coords = "testFiles/eulearCoords.txt";
        EulerianGraph G = new EulerianGraph(new FileInputStream(test), new FileInputStream(coords));
        G.show();
    }
}
