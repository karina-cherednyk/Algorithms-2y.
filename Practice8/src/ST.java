import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ST<Key extends Comparable<Key>,Value> {
    private class Node{
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        Key key;
        Value val;
        Node next;
    }
    private Node first;

    ST(){ };
    void put(Key key, Value val){
        if (key == null)
            return;
        if (isEmpty()){
            first = new Node(key,val);
            return;
        }
        Node temp = first;
        //check if value is present
        while (temp!=null){
            if (temp.key.equals(key)){
                temp.val=val;
                return;
            }
            temp = temp.next;
        }
        Node oldFirst = first;
        first = new Node(key,val);
        first.next=oldFirst;
    }
    int size(Key lo, Key hi){
        if(lo==null || hi == null) return 0;
        int count = 0;
        Node temp = first;
        while(temp!=null){
            if(temp.key.compareTo(lo)>-1 && temp.key.compareTo(lo)<1 ) count++;
            temp = temp.next;
        }
        return count;
    }
    List<Key> toKeyList(Key lo, Key hi){
        if(lo==null || hi == null) return null;
        List<Key> list = new LinkedList<>();
        Node temp = first;
        while(temp!=null){
            if(temp.key.compareTo(lo)>-1 && temp.key.compareTo(lo)<1 ) list.add(temp.key);
            temp = temp.next;
        }
        return list;
    }
    Value get(Key key){
        if(key==null) return null;
        Node temp = first;
        while(temp!=null){
            if(temp.key.equals(key)) return temp.val;
            temp = temp.next;
        }
        return null;
    }
    //return parent and child
    Object[] getNodes(Key key){
        if(key==null) return null;
        Node temp = first;
        Node parent = null;
        while(temp!=null){
            if(temp.key.equals(key)) {
                Object[] nodes = new Object[]{parent, temp};
                return nodes;
            }
            parent = temp;
            temp = temp.next;
        }
        return null;
    }
    public boolean contains(Key key){
        return get(key)!=null;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public void delete(Key key){
        if (key == null) return;
        Object[] nodes = getNodes(key);
        if(nodes==null) return;
        if(nodes[0]==null) first = ((Node)nodes[1]).next;
        else ((Node)nodes[0]).next = ((Node)nodes[1]).next;
    }
    public void deleteMin(){
        delete(min());
    }
    public void deleteMax(){
        delete(max());
    }
    public Key min(){
        if(first==null) return null;
        Node temp = first;
        Key min = first.key;
        while(temp!=null){
            if(temp.key.compareTo(min) <= -1) { min = temp.key;}
            temp = temp.next;
        }
        return min;
    }
    public int rank(Key key){
        if(first==null) return 0;
        Node temp = first;
        int count = 0;
        while(temp!=null){
            if(temp.key.compareTo(key) <0) count++;
            temp = temp.next;
        }
        return count;
    }
    public Key select(int k){
        if(first==null) return null;
        Node temp = first;
        int i = 0;
        while(temp!=null && i!=k){
            temp = temp.next;
        }
        return temp.key;
    }
    public Key floor(Key key){
        if(first==null) return null;
        Node temp = first;
        Key floor = null;
        while(temp!=null){
            if(floor==null && temp.key.compareTo(key) <1) floor = temp.key;
            else if(floor!=null && temp.key.compareTo(key) <1 &&
                    temp.key.compareTo(floor)>1) floor = temp.key;
            temp = temp.next;
        }
        return floor;
    }
    public Key ceiling(Key key){
        if(first==null) return null;
        Node temp = first;
        Key ceiling = null;
        while(temp!=null){
            if(ceiling==null && temp.key.compareTo(key) >-1) ceiling = temp.key;
            else if(ceiling!=null && temp.key.compareTo(key) >-1 &&
                    temp.key.compareTo(ceiling)<=-1) ceiling = temp.key;
            temp = temp.next;
        }
        return ceiling;
    }
    public Key max(){
        if(first==null) return null;
        Node temp = first;
        Key max = first.key;
        while(temp!=null){
            if(temp.key.compareTo(max) >= 1) max = temp.key;
            temp = temp.next;
        }
        return max;
    }
    public Iterable<Key> keys(){
        Iterable<Key> keys = new Iterable<>() {
            @Override
            public Iterator<Key> iterator() {
                return new KeyIterator();
            }
        };
        return keys;
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Iterable<Key> keys = new Iterable<>() {
            @Override
            public Iterator<Key> iterator() {
                return toKeyList(lo,hi).iterator();
            }
        };
        return keys;
    }


    private class KeyIterator implements Iterator<Key> {

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current!=null;
        }
        @Override
        public Key next() {
            Key key =  current.key;
            current = current.next;
            return key;
        }

    }
    public static void main(String[] args){
        ST<String, Integer> st = new ST<>();
        System.out.println(st.isEmpty());
        st.put("BA",2);
        st.put("AA",1);
        st.put("ZZ",7);
        System.out.println(st.isEmpty());
        System.out.println(st.max());
        System.out.println(st.ceiling("AB"));
        System.out.println(st.floor("AB"));
        System.out.println(st.rank("Q"));
        System.out.println(st.contains("BA"));
        st.delete("AA");
        System.out.println(st.min());
        System.out.println(st.max());
    }

}
