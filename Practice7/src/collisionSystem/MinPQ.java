package collisionSystem;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;                    // store items at indices 1 to N
    private int N;                       // number of items on priority queue

    public MinPQ(int initCapacity) {
        pq = (Key[]) new Comparable[initCapacity + 1];
        N = 0;
    }

    public MinPQ() {
        this(1);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }


    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key x) {
        if (N == pq.length - 1) resize(2 * pq.length);
        pq[++N] = x;
        swim(N);
    }

    public Key delMin() {
        exch(1, N);
        Key min = pq[N--];
        sink(1);
        pq[N+1] = null;
        if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length  / 2);
        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    private boolean greater(int i, int j) {
        return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

}
