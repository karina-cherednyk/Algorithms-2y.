import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.StdDraw;
import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;


public class SeamCarving {

    private ArrayDeque<Integer> deque;
    private int[] carve;
    private int[][] image;
    private int height,width;

    public SeamCarving(String file) throws IOException {
        StdDraw.setPenRadius(0.01);
        BufferedImage img =
                ImageIO.read(new File(file));

        height = img.getHeight();
        width = img.getWidth();
        image = new int[width][height];

        for(int x=0; x<width; x++)
            for(int y=0; y<height; y++)
                image[x][y] = img.getRGB(x,y)& 0x00ffffff;


    }

    public static  void main(String[] args) throws Exception {
        String file = "pictures/shrek.png";
        SeamCarving sc = new SeamCarving(file);
        sc.carveX(40);
        sc.drawPic();
    }


    public void carveX(int times) throws Exception {
        for(int i=0; i<times; i++)
            carveX();

    }

    public void carveY(int times) throws Exception {
        for(int i=0; i<times; i++)
            carveY();

    }



    private int index(int x, int y){
        return y*width + x;
    }
//    private int val(int x0, int y0) {
//        int res = 0;
//        int top = y0 > 0 ? -1 : 0;
//        int bottom = y0<height-1 ? 1 : 0;
//        int left = x0 > 0 ? -1 : 0;
//        int right = x0<width-1 ? 1 : 0;
//
//        for(int x=left; x<=right; x++)
//            for(int y=top; y<=bottom; y++) {
//                if(x==0 && y==0) continue;
//                res += image[x0 + x][y0 + y];
//            }
//        return res;
//
  //  }
    private int val(int x, int y){
        int dxSq = 0; int dySq = 0;
        if(x<width-1 && x>0){
            int[] xp = getRGB(image[x-1][y]);
            int[] xn = getRGB(image[x+1][y]);

            dxSq = (xp[0] - xn[0])*(xp[0] - xn[0]) + (xp[1] - xn[1])*(xp[1] - xn[1]) +(xp[2] - xn[2])*(xp[2] - xn[2]) ;
        }
        else if(x<width-1){
            int[] xn = getRGB(image[x+1][y]);
            dxSq = xn[0]*xn[0] + xn[1]*xn[1] + xn[2]*xn[2];
        }
        else if(x>0){
            int[] xp = getRGB(image[x-1][y]);
            dxSq = xp[0]*xp[0] + xp[1]*xp[1] + xp[2]*xp[2];
        }

        if(y<height-1 && y>0){
            int[] yp = getRGB(image[x][y-1]);
            int[] yn = getRGB(image[x][y+1]);

            dxSq = (yp[0] - yn[0])*(yp[0] - yn[0]) + (yp[1] - yn[1])*(yp[1] - yn[1]) +(yp[2] - yn[2])*(yp[2] - yn[2]) ;
        }
        else if(y<height-1){
            int[] yn = getRGB(image[x][y+1]);
            dySq = yn[0]*yn[0] + yn[1]*yn[1] + yn[2]*yn[2];
        }
        else if(y>0){
            int[] yp = getRGB(image[x][y-1]);
            dySq = yp[0]*yp[0] + yp[1]*yp[1] + yp[2]*yp[2];
        }
        return dxSq + dySq;
    }
    private void carveX() throws Exception {
        if(width -1 == 0) throw new Exception("Can`t shrink image anymore");

        int vSource = width*height;
        int vDest = width*height+1;
     EdgeWeightedDigraph D = new EdgeWeightedDigraph(height*width + 2);
        for(int x=0; x<width; x++)
            D.addEdge(new DirectedEdge(vSource,index(x,0), val(x,0)));

       for(int y=0; y<height-1; y++){
        for(int x=0; x<width; x++){
            D.addEdge(new DirectedEdge(index(x,y), index(x,y+1), val(x,y+1)));
            if(x!=0 && x!=width-1){
                D.addEdge(new DirectedEdge(index(x,y), index(x-1,y+1), val(x-1,y+1)));
                D.addEdge(new DirectedEdge(index(x,y), index(x+1,y+1), val(x+1,y+1)));
            }
            else if(x!=0)
                D.addEdge(new DirectedEdge(index(x,y), index(x-1,y+1), val(x-1,y+1)));
            else if(x!=width-1)
                D.addEdge(new DirectedEdge(index(x,y), index(x+1,y+1), val(x+1,y+1)));

        }
       }

        for(int x=0; x<width; x++)
            D.addEdge(new DirectedEdge(index(x,height-1), vDest, 0));
        DijkstraSP dSP = new DijkstraSP(D, vSource);
        deque = dSP.pathTo(vDest);
        setPicX();
    }
    private void carveY() throws Exception {
        if(height -1 == 0) throw new Exception("Can`t shrink image anymore");

        int vSource = width*height;
        int vDest = width*height+1;
        EdgeWeightedDigraph D = new EdgeWeightedDigraph(height*width + 2);
        for(int y=0; y<height; y++)
            D.addEdge(new DirectedEdge(vSource,index(0,y), val(0,y)));

        for(int y=0; y<height; y++){
            for(int x=0; x<width-1; x++){
                D.addEdge(new DirectedEdge(index(x,y), index(x+1,y), val(x+1,y)));
                if(y!=0 && y!=height-1){
                    D.addEdge(new DirectedEdge(index(x,y), index(x+1,y+1), val(x+1,y+1)));
                    D.addEdge(new DirectedEdge(index(x,y), index(x+1,y-1), val(x+1,y-1)));
                }
                else if(y!=0)
                    D.addEdge(new DirectedEdge(index(x,y), index(x+1,y-1), val(x+1,y-1)));
                else if(y!=height-1)
                    D.addEdge(new DirectedEdge(index(x,y), index(x+1,y+1), val(x+1,y+1)));

            }
        }

        for(int y=0; y<height; y++)
            D.addEdge(new DirectedEdge(index(width-1,y), vDest, 0));
        DijkstraSP dSP = new DijkstraSP(D, vSource);
        deque = dSP.pathTo(vDest);
        setPicY();
    }

    private void setPicY() {
        int i =0;
        Integer[] pixels = deque.toArray(Integer[]::new);
        int[][] res = new int[width][height-1];
        int indOmit = 1;
        int omit;
        boolean shift;

        for(int x = 0; x < width; x++) {
            shift = false;
            omit = pixels[indOmit++]/width;
            for (int y = 0; y < height; y++){

                if(y==omit)  shift = true;
                else if(shift) res[x][y-1] = image[x][y];
                else res[x][y] = image[x][y];
            }
        }

        image = res;
        height = height-1;
    }

    private void setPicX() {
        int i =0;
        Integer[] pixels = deque.toArray(Integer[]::new);
        int[][] res = new int[width-1][height];
        int indOmit = 1;
        int omit;
        boolean shift;

        for(int y = 0; y < height; y++) {
            shift = false;
            omit = pixels[indOmit++]%width;
            for (int x = 0; x < width; x++){

                if(x==omit)  shift = true;
                else if(shift) res[x-1][y] = image[x][y];
                else res[x][y] = image[x][y];
            }
        }

        image = res;
        width = width-1;
    }
    public void drawPic(){

        StdDraw.setCanvasSize(width, height);
        StdDraw.setYscale(0,height);
        StdDraw.setXscale(0,width);

        for(int y = 0;  y < height; y++) {
            for (int x = 0; x < width; x++) {
                StdDraw.setPenColor(getColor(image[x][y]));
                StdDraw.line(x, height - y, x, height - y);
            }
        }

    }
    private Color getColor(int color){
        int blue = color & 0xff;
        int green = (color & 0xff00) >> 8;
        int red = (color & 0xff0000) >> 16;
        return new Color(red,green,blue);
    }
    private int[] getRGB(int color){
        int blue = color & 0xff;
        int green = (color & 0xff00) >> 8;
        int red = (color & 0xff0000) >> 16;
        return new int[]{red,green,blue};
    }
}
