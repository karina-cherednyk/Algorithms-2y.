import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        //Scanner s = new Scanner(System.in);
        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(s.readLine());
        ArrayList<Integer>[] arr2 = (ArrayList<Integer>[]) new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr2[i] = new ArrayList<Integer>();
        }
        String edges[];
        for (int i = 1; i <= N; i++) {
            String line = s.readLine();
            if(line.isEmpty()) continue;
            edges = line.split(" ");
            for(int j=0; j<edges.length; j++) arr2[Integer.parseInt(edges[j])].add(i);
        }
       StringBuilder sb = new StringBuilder();
        sb.append(N+"\n");

        for (int i = 1; i <= N; i++) {
            for(int j=0; j<arr2[i].size()-1; ++j) {
                sb.append(arr2[i].get(j)+" ");
            }
            if(arr2[i].size()!=0)
                sb.append(arr2[i].get(arr2[i].size()-1)+"");
            sb.append("\n");
        }
        System.out.println(sb);
        }

    }

