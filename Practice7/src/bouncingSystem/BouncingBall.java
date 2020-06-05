package bouncingSystem;

import edu.princeton.cs.introcs.StdDraw;

class BouncingBalls
{
    public static void main(String[] args)
    {
        int N = 10;
        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++)
            balls[i] = new Ball();
        while(true)
        {
            StdDraw.clear();
            for (int i = 0; i < N; i++)
            {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}