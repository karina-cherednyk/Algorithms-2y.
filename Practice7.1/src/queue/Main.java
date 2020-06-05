package queue;

import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        long next;

        for(int i = 0; i < n; i++) {
            next = in.nextLong();
            if (pq.size() != k) pq.add(next);
            else pq.add(pq.poll() + next);
        }
        while(pq.size() > 1) pq.poll();
        System.out.println(pq.poll());
    }
}