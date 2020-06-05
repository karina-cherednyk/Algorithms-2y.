import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int arr[][] = new int[N][N];
        for(int r=0; r<N; r++)
            for(int c=0; c<N; c++)
                arr[r][c] = s.nextInt();

         boolean no = true;
        for(int r=0; r<N; r++) {
            if(arr[r][r]!=0){
                no = false;
                break;
            }
            for (int c = r + 1; c < N; c++)
                if (arr[r][c] != arr[c][r]) {
                    no = false;
                    break;
                }
            if(!no) break;
        }
        System.out.println((no ? "YES" : "NO"));
    }
}
