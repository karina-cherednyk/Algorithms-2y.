package collisionSystem;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

public class CollisionSystem {
    private MinPQ<Event> pq;        // the priority queue
    private double t  = 0.0;        // simulation clock time
    private double hz = 0.5;        // number of redraw events per clock tick
    private Particle[] particles;   // the array of particles

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;  // .clone() can be used for defensive copying
    }

    private void predict(Particle a, double limit) {
        if (a == null) return;

        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particles[i]));
        }

        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) pq.insert(new Event(t + dtX, a, null));
        if (t + dtY <= limit) pq.insert(new Event(t + dtY, null, a));
    }

    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(10);
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / hz, null, null));
        }
    }

    public void simulate(double limit) {
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i], limit);
        }
        pq.insert(new Event(0, null, null));        // redraw event

        while (!pq.isEmpty()) {
            Event e = pq.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            for (int i = 0; i < particles.length; i++)
                particles[i].move(e.time - t);
            t = e.time;

            if      (a != null && b != null) a.bounceOff(b);              // particle-particle collision
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) redraw(limit);               // redraw event

            predict(a, limit);
            predict(b, limit);
        }
    }




    public static void main(String[] args) {

        StdDraw.setCanvasSize(800, 800);

        StdDraw.show(0);

        int N = 20;
        Particle[] particles = new Particle[N];
        for (int i = 0; i < N; i++)
            particles[i] = new Particle();

        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000);
    }

}