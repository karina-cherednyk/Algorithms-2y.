package project6;

import edu.princeton.cs.introcs.StdDraw;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class ConvexHull {
    public static void main(String[] args) throws FileNotFoundException{
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        int file = 9; //5 6 7 8 9
        FileReader fr = new FileReader(SourceFile.file(file));
        Scanner s = new Scanner(fr);
        int num = 0;
        int x,y;
        if(s.hasNextInt()) num =s.nextInt();
        Point2D[] ps = new Point2D[num];
        for(int i=0; i<num; i++){
            x = s.nextInt();
            y = s.nextInt();
            ps[i] = new Point2D(x,y);
        }

        drawPoints(ps);
        drawConvexHull(ps);
    }
    public static void drawPoints(Point2D[] ps){
        for(int i=0; i<ps.length; i++) ps[i].draw();
    }
    public static void drawConvexHull(Point2D[] ps){
        Point2D p = findMin(ps);
        Arrays.sort(ps, p.pOrder);
        LinkedList<Point2D> linked = new LinkedList<>(); //points connected with lowest point
        linked.add(p);
        for(int i=1; i<ps.length;){
            if(linked.size()==1) linked.add(ps[i++]);
            else if(Point2D.ccw(linked.get(linked.size()-2), linked.getLast(), ps[i] ) == 1 ) linked.add(ps[i++]);
            else linked.removeLast();
        }
        for(int i=0; i<linked.size()-1; i++) linked.get(i).drawTo(linked.get(i+1));
        p.drawTo(linked.getLast());
    }


    public static Point2D findMin(Point2D[] ps){
        Point2D p = ps[0];
        for(int i=1; i<ps.length; i++)
            if(ps[i].getY()<p.getY()
                    || (ps[i].getY()==p.getY() && ps[i].getX()>p.getX()))
                        p = ps[i];
        return p;
    }
}
