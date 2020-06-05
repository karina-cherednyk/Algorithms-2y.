import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static class DNA implements Comparable<DNA>{
        public char[] dna;
        public int inv;
        public DNA(char[] s){
            dna = s;
            inv = countInversions(s);
        }
        @Override
        public int compareTo(DNA that) {
            return this.inv - that.inv;
        }
        private static int countInversions(char[] arr){
                int res = 0;
                for(int i=0; i<arr.length-1; i++)
                    for(int j=i+1; j<arr.length; j++)
                        if(arr[i] > arr[j]) res++;
                return res;
        }
        public String toString(){
            return String.valueOf(dna);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        int m,n;
        String[] s;
        DNA[] arr;
        while(M--!=0){
            br.readLine();
            s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
            arr = new DNA[m];
            for(int i=0; i<m; i++)  arr[i] = new DNA(br.readLine().toCharArray());
            Arrays.sort(arr);
            for(int i=0; i<m; i++) sb.append(arr[i]+"\n");
            System.out.println(sb);
            sb  = new StringBuilder();
        }
    }

}
