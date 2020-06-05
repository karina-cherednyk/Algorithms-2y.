import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N;
       String[] arr;
        boolean ok;
        while(T-- !=0){
            ok = true;
            N = Integer.parseInt(br.readLine());
            arr = new String[N];
            for(int i=0; i<N; i++) arr[i] = br.readLine();
            Arrays.parallelSort(arr);
            for(int i=0; i<N-1; i++) {
                if(arr[i+1].length()<arr[i].length()) continue;
                if(arr[i+1].substring(0,arr[i].length()).equals(arr[i])) {ok=false; break;}
            }

            if(ok) System.out.println("YES");
            else System.out.println("NO");
        }
    }


}