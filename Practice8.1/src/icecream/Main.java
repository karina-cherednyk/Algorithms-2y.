package icecream;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int[] st = new int[n];
       for(int i=0; i<n; i++) st[i] = s.nextInt();

        int MIN = 0; //minimum distance between stalls
        int MAX = 1000000000; //maximum distance
        int MEDIUM = (MIN+MAX)/2; ; //minimum of largest distances between stalls;

        //binary search of appropriate distance - log2 (10^9)
        while(MIN<=MAX){
            MEDIUM = (MIN+MAX)/2;
            //if all stalls can have no less then this distance between each other, find bigger distance that can be between stalls
            if(distPass(MEDIUM,st,k)) MIN = MEDIUM+1;
            //else find smaller distance
            else MAX = MEDIUM-1;
        }
        //in last cycle distPass() returned false, so MEDIUM doesn`t fit anymore, so the smallest distance is MEDIUM-1
        System.out.println(MIN-1);
    }

    private static boolean distPass(int medium, int[] st, int k) {
        int count = 1; //counts how many stalls can be no less then MEDIUM away from each other
        int dist = 0;//distance between two stalls
        for(int i=0; i<st.length-1; i++){
            dist += st[i+1]-st[i]; //while distance is less then MEDIUM, search for appropriate distance
            if(dist >= medium) {  //one more stall can fit
                count++;
                dist = 0;
            }
        }
        return (count>=k); //all stalls can have such distance between them
    }
}
