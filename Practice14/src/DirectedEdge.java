public class DirectedEdge {
    private int from , to;
    private double weight;
    DirectedEdge(int v, int w, double weight){
        from = v;
        to = w;
        this.weight = weight;
    }
    int from(){
        return from;
    }
    int to(){
        return to;
    }
    double weight(){
        return weight;
    }
    public String toString(){
        return "";
    }
}
