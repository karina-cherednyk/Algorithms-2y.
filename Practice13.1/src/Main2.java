import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main2 {
    static int n;
    static int x[] ;
    static int y[];


    static int sqr(int x) { return x*x; }

    static int distance_between(int i, int j) { return sqr(x[j] - x[i]) + sqr(y[j] - y[i]); }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arg;
        n = Integer.parseInt(br.readLine());
        x = new int[n];
        y = new int[n];
        boolean used[] = new boolean[n];
        int min_edge[] = new int[n];
        int last_edge[] = new int[n];
        for(int i=0; i<n; i++){
            arg = br.readLine().split(" ");
            x[i] = Integer.parseInt(arg[0]);
            y[i] = Integer.parseInt(arg[1]);
            min_edge[i] = Integer.MAX_VALUE;
            last_edge[i] = -1;
        }


        double minimal_distance = min_edge[1] = 0;


        for (int i = 0; i < n; i++)
        {
           int  v = -1;
            for (int j = 0; j < n; j++)
                if (!used[j] && (v == -1 || min_edge[j] < min_edge[v])) v = j;


            used[v] = true;
            if (last_edge[v] != -1) minimal_distance += Math.sqrt(distance_between(v, last_edge[v]));


            for (int to = 0; to < n; to++)
            {
                int distance = distance_between(v, to);
                if (!used[to] && distance < min_edge[to])
                {
                    min_edge[to] = distance;
                    last_edge[to] = v;
                }
            }
        }
        System.out.println(minimal_distance);

    }
}