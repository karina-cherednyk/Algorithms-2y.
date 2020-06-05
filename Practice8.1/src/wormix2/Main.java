package wormix2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    private static class Mission {
        int score;
        int time;
        public Mission(int points, int time) {
            this.score = points;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums;
        nums = br.readLine().split(" ");
        int N = Integer.parseInt(nums[0]);
        int K = Integer.parseInt(nums[1]);
        Mission[] m = new Mission[N];
        int MAX = 0;
        for(int i=0; i<N; i++){
            nums = br.readLine().split(" ");
            m[i] = new Mission(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]));
            MAX += m[i].score;
        }
        System.out.println(MINTIME(m, N, K, MAX));
    }
    private static int MINTIME(Mission[] m, int n, int SC, int MAX) {
        if(SC>MAX) return -1;
        if(SC==0) return 0;
        int[][] stats = new int[n][SC+1];

        for(int sc=1; sc<=SC; sc++){
            stats[0][sc] = sc <= m[0].score ? m[0].time:-1;
        }
        for(int i=1; i<n; i++){
            for(int sc=1; sc<=SC; sc++){
                if(sc>m[i].score) {
                    int d = sc - m[i].score;
                    if(stats[i - 1][d] == -1)
                        stats[i][sc] = -1;
                    else if(stats[i-1][sc]!=-1)
                        stats[i][sc] = min(stats[i-1][sc], stats[i-1][d] + m[i].time);
                    else stats[i][sc] = stats[i-1][d] + m[i].time;
                }
                else if(stats[i - 1][sc]==-1)stats[i][sc]=m[i].time;
                else stats[i][sc] = min(m[i].time, stats[i - 1][sc]);
            }
        }

        return stats[n-1][SC];
    }

    private static int min(int j, int i) {
        return j<i ? j : i;
    }
}