import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int cap[][],res[][];
    static boolean used[];
    static int n;

    static int aug(int x, int t, int curFlow){
        if(x==t)return curFlow;
        if(used[x]) return 0;
        used[x]= true;
        int flow;
        for(int y=0; y<n; y++){
            if(res[x][y] > 0 && ((flow = aug(y,t,min(curFlow, res[x][y])))!=0)){
                res[x][y] -=flow;
                res[y][x] +=flow;
                return flow;
            }
        }
        return 0;
    }
    static int mincut(int s, int t){
        for(int i=0; i<n*n; i++)res[i/n][i%n] = cap[i/n][i%n];
        int x;
        int flow = 0;
        do{
            for(int i=0; i<n; i++) used[i] = false;
        }
        while(
                (x=aug(s,t,Integer.MAX_VALUE))!=0 && (flow+=x)!=0
        );
        return flow;
    }
    static int requiredCost(){
        int best = Integer.MAX_VALUE;
        for(int s=1; s<n; s++)
            best = min(best,mincut(0,s));
        return best;
    }

    static int min(int j, int i) {
        return j>i ? i : j;
    }

    public  static  void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arg;
        String ns;
        while((ns = br.readLine())!=null) {
            n = Integer.parseInt(ns);
            cap = new int[n][n];
            res = new int[n][n];
            used = new boolean[n];

            for (int i = 0; i < n; i++) {
                arg = br.readLine().split("");
                for (int j = 0; j < n; j++)
                    cap[i][j] = Integer.parseInt(arg[j]);
            }
            System.out.println(requiredCost());
        }
    }
}
