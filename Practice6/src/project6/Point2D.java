package project6;

import java.util.Comparator;
import edu.princeton.cs.introcs.StdDraw;

public class Point2D implements Comparable<Point2D>{
    //public static int ccw(Point2D a, Point2D b, Point2D c)
//public boolean equals(Object other)
//public String toString()
//public void draw()
//public void drawTo(Point2D that)
//public int compareTo(Point2D that)
//private class PolarOrder implements Comparator<Point2D>
    private final int x;
    private final int y;
    public final Comparator<Point2D> pOrder = new PolarOrder();

    public Point2D(int x, int y) { // створює точку (x, y)
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }
    public void draw() { // малює точку
        StdDraw.point(x, y);
    }
    public void drawTo(Point2D that) { // малює відрізок між цією точкою і that точкою
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    @Override
    public int compareTo(Point2D that) { // чи ця точка лексикографічно менша за that точку?
        if(this.y<that.y)return -1;
        else if(this.y==that.y && this.x<that.x) return -1;
        else if(this.y==that.y && this.x==that.x)return 0;
        return 1;
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof Point2D))
            return false;
        else {
            Point2D other = (Point2D)obj;
            return x == other.x && y == other.y;
        }
    }

    @Override
    public String toString() { // string подання
        return "(" + x + ", " + y + ")";
    }
    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {

            double dy1 = q1.y - y;
            double dy2 = q2.y - y;
            if(q1.equals(Point2D.this)) return -1;
            else if(q2.equals(Point2D.this)) return 1;
            if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 above; q2 below
            else if (dy2 >= 0 && dy1 < 0) return 1;    // q1 below; q2 above
            else return -ccw(Point2D.this, q1, q2);     // both above or below
        }
    }
    //    Counterclockwise turns. Given three points a, b, and c,
//    determine whether they form a counterclockwise angle.
//    The function ccw takes three Point inputs a, b, and c and
//    returns +1 if a->b->c is a counterclockwise angle,
//    -1 if a->b->c is a clockwise angle, and 0 if a->b->c are collinear
    public static int ccw(Point2D a, Point2D b, Point2D c){
        int area =  (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if     (area>0) return 1;
        else if(area<0) return -1;
        return 0;
    }
}