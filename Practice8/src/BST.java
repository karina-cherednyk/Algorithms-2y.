
import java.util.LinkedList;
import java.util.Queue;


public class BST <Key extends Comparable<Key>, Value>{

    private Node root;
    private class Node{
        Key key;
        Value val;
        Node left, right;
        int count;
        Node(Key k , Value v){
            key = k;
            val = v;
        }
    }
    //
    public void put(Key key, Value val){
        root = put(root, key,val);
    }
    //
    public Value get(Key key){
        Node node = root;
        while(node!= null){
            int cmp = key.compareTo(root.key);
            if(cmp > 0) node = node.right;
            else if(cmp < 0) node = node.left;
            else return node.val;
        }
        return null;
    }
    //
    public void delete(Key key){
        root  = delete(root, key);
    }
    //
    public Node delete(Node root, Key key){
        if(root == null) return null;
        int cmp = key.compareTo(root.key);
        if(cmp > 0) root.right = delete(root.right, key);
        else if(cmp < 0) root.left = delete(root.left, key);
        else {
            if(root.right == null) return root.left;
            if(root.left == null) return root.right;
                Node t = root;
                root = min(t.right);
                root.right = deleteMin(t.right);
                root.left = t.left;
        }
        root.count = size(root.left) + size(root.right) + 1;
        return root;
    }
    //
    public void deleteMin(){
        root = deleteMin(root);
    }
    //
    private Node deleteMin(Node node) {
        //found min element
        //replace parents left child with min`s right child (both are less than min`s parent)
       if(node.left==null) return node.right;
       node.left = deleteMin(node.left);
       node.count = size(node.left) + size(node.right) + 1;
       return node;
    }
    //
    public Node put(Node root, Key key, Value val){
        if(root == null) return new Node(key,val);
        int cmp = key.compareTo(root.key);
        if(cmp > 0) root.right = put(root.right, key, val);
        else if(cmp < 0) root.left = put(root.left, key, val);
        else root.val = val;
        root.count = size(root.left) + size(root.right) + 1;
        return root;
    }
    //
    int size(Node n){
        return n==null ? 0 : n.count;
    }
    //
    public Key min(){
        Node node = root;
        while(node.left!=null) node = node.left;
        return node.key;
    }
    public Node min(Node node){
        while(node.left!=null) node = node.left;
        return node;
    }
    //
    public Key max(){
        Node node = root;
        while(node.right!=null) node = node.right;
        return node.key;
    }

    //
    public Key ceiling(Key key){
        return ceiling(root, key);
    }
    //
    public Key ceiling(Node root, Key key){
        if(root == null ) return null;
        int cmp = key.compareTo(root.key);
        if(cmp == 0) return root.key;
        else if(cmp>0) return ceiling(root.right, key);
        else {
            Key k = ceiling(root.left, key);
            if(k==null) return root.key;
            else return k;
        }
    }
    //
    public Key floor(Key key){
        return floor(root, key);
    }
    //
    public Key floor(Node root, Key key){
        if(root == null ) return null;
        int cmp = key.compareTo(root.key);
        if(cmp == 0) return root.key;
        else if(cmp<0) return floor(root.left, key);
        else {
            Key k = floor(root.right, key);
            if(k==null) return root.key;
            else return k;
        }
    }
    public Node floorNode(Node root, Key key){
        if(root == null ) return null;
        int cmp = key.compareTo(root.key);
        if(cmp == 0) return root;
        else if(cmp>0) return floorNode(root.left, key);
        else {
            Node k = floorNode(root.right, key);
            if(k==null) return root;
            else return k;
        }
    }
    //
    public int rank(Key key){
        return rank(root, key);
    }
    //
    public int rank(Node root, Key key){
        if(root == null ) return 0;
        int cmp = key.compareTo(root.key);

        if(cmp<0) return rank(root.left, key);
        else if(cmp>0) return 1+ size(root.left) + rank(root.right,key);
        else return size(root.left);
    }
    //
    public int size(){
        return size(root);
    }

    //
    public Iterable<Key> keys(){
        Queue<Key> q = new LinkedList<>();
        inorder(root,q);
        return q;
    }
    //
    private void inorder(Node root, Queue<Key> q){
        if(root==null) return;
        inorder(root.left,q);
        q.add(root.key);
        inorder(root.right,q);
    }
    public static void main(String[] args){
        BST<String, Integer> st = new BST<>();
        System.out.println(st.isEmpty());
        st.put("BA",2);
        st.put("AA",1);
        st.put("ZZ",7);
        System.out.println(st.isEmpty());
        System.out.println(st.max());
        System.out.println(st.ceiling("AB"));
        System.out.println(st.floor("AB"));
        st.delete("AA");
        System.out.println(st.min());
        System.out.println(st.max());
    }

    private boolean isEmpty() {
        return root==null;
    }
}
