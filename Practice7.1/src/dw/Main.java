package dw;

import java.io.*;
import java.util.*;

public class Main {
    static boolean  isok(PriorityQueue<Integer> pq){
        System.out.println(pq);
       while(!pq.isEmpty()) {
           int head = pq.poll();
           if (pq.size() < head) return false;
           Queue<Integer> guests = new LinkedList<>();
           for (int i = 0; i < head; i++) {
               int g = pq.poll();
               if (g > 1) guests.add(g - 1);
           }
           while (!guests.isEmpty()) pq.add(guests.poll());
       }
       return true;
   }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] data;

        String line;
        while((line = br.readLine())!= null){
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            data = line.split(" ");
            for (String s:data) pq.add(Integer.parseInt(s));
            if(isok(pq))sb.append("ok\n\n");
            else sb.append("fail\n\n");
        }
        System.out.println(sb);
    }
}
