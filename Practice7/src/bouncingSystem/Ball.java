package bouncingSystem;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

import java.awt.Color;

public class Ball
{

    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius; // radius
    private final Color color = ColorRand.sample();
    public Ball(double radius, double rx, double ry, double vx, double vy) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }
    public Ball() {
        this.rx = StdRandom.uniform(0.01,1.0);
        this.ry = StdRandom.uniform(0.0,1.0);
        this.vx = StdRandom.uniform(-0.05, 0.05);
        this.vy = StdRandom.uniform(-0.05,0.05);
        this.radius = StdRandom.uniform(0.01, 0.05);
    }
    public void move(double dt)
    {
        if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) { vx = -vx; }
        if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) { vy = -vy; }
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }
}
