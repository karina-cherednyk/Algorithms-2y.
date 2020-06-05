package wordnet;

import graph.DFS;
import graph.Digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SAP {
    Digraph D;
    public SAP(Digraph G){
        D=G;
    }


private int[] sap(int v, int w){
    int[] distancesA;
    int[] distancesB;
    DFS d1 = new DFS(D,v);
    DFS d2 = new DFS(D,w);
    distancesA = d1.getDistances();
    distancesB = d2.getDistances();

    int min = -1;
    boolean start = true;
    int sum;
    int num=-1;
    for(int i=0; i<D.V(); i++)
        if(distancesA[i]!=-1 && distancesB[i]!=-1) {
            if (start) {
                min = distancesA[i] + distancesB[i];
                num = i;
                start = false;
            } else {
                sum = distancesA[i] + distancesB[i];
                if(sum<min){
                    min = sum;
                    num = i;
                }
            }
        }
    return new int[]{num, min};
}
// довжина найкоротшого шляху до спільного батька v та w​
//-1 якщо такого шляху немає​

    public int length(int v, int w){
        return sap(v,w)[1];
    }
// спільний батько v та w,  з найкоротшого шляху ​
//-1 якщо такого шляху немає​

    public int ancestor(int v, int w){
       return sap(v,w)[0];
    }

// довжина найкоротшого шляху між будь-якою вершиною з v та з w; ​
//-1 якщо такого шляху немає​

    public int length(Iterable<Integer> v0, Iterable<Integer> w0){
        int min = 100000000;
        for(int v : v0)
            for(int w : w0) {
                int l = length(v, w);
                if ( l!= -1 && l<min) min = l;
            }
        if(min==100000000)return -1;
        return min;
    }

// спільний батько з найкоротшого шляху …; ​
//-1 якщо такого шляху немає​

    public int ancestor(Iterable<Integer> v0, Iterable<Integer> w0){
        int min = 100000000;
        int an=-1;
        for(int v : v0)
            for(int w : w0) {
                int[] arr = sap(v, w);
                int l = arr[1];
                if ( l!= -1 && l<min) {
                    min = l;
                    an = arr[0];
                }
            }
        return an;
    }

    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream("test/sap1.txt");

        Digraph G = new Digraph(fis);

        SAP sap = new SAP(G);
        Scanner in = new Scanner(System.in);
        while (!in.hasNext()) {
            int v = in.nextInt();
            int w = in.nextInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            System.out.printf("length = %d, ancestor = %d\n", length, ancestor);

        }

    }
}
