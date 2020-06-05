import java.util.*;

public class SearchDictionary {
    static final int ALPHABET_SIZE = 26;
    Node head = new Node('&');
    class Node{
        char ch;
        Node[] bottom;
        int cnt;
        boolean isEnd;
        String word;
        Node(char ch){
            this.ch = ch;
            bottom = new Node[5];
        }
        Node searchBottom(char c){
            for(int i=0; i<cnt; i++){
                if(bottom[i].ch == c) return bottom[i];
            }
            return null;
        }
        Node addBottom(char ch){
            bottom[cnt++] = new Node(ch);
            if(cnt>bottom.length/2) resize(bottom.length*2);
            return bottom[cnt-1];
        }

        private void resize(int length) {
            Node[] res = new Node[length];
            for(int i=0; i<cnt; i++){
                res[i] = bottom[i];
            }
            bottom = res;
        }

    }
    public SearchDictionary(){}

    public void addWord(String word){
        char[] arr = word.toCharArray();
        int i = 0;
        Node parent = head;
        while(i<arr.length) {
            char next = arr[i++];
            Node bottom = parent.searchBottom(next);
            if (bottom == null) bottom = parent.addBottom(next);
            parent = bottom;
        }
        parent.isEnd = true;
        parent.word = word;
    }



    public String delWord(String word){
        char[] arr = word.toLowerCase().toCharArray();
        int i = 0;
        Node parent = head;
        while(i<arr.length) {
            char next = arr[i++];
            Node bottom = parent.searchBottom(next);
            if (bottom == null) return null;
            if(i==arr.length) { bottom.isEnd = false; return bottom.word; }
            parent = bottom;
        }
        return null;
    }



    public boolean hasWord(String word){
        char[] arr = word.toLowerCase().toCharArray();
        int i = 0;
        Node parent = head;
        while(i<arr.length) {
            char next = arr[i++];
            Node bottom = parent.searchBottom(next);
            if (bottom == null) return false;
            if(i==arr.length)  return bottom.isEnd;
            parent = bottom;
        }
        return false;
    }


   void searchForLeaf(Node x,List<String> list){
        if(x.isEnd) list.add(x.word);
        for(int i=0; i<x.cnt; i++)
            searchForLeaf(x.bottom[i],list);
    }



    public Iterable<String> query(String query){
        char[] arr = query.toLowerCase().toCharArray();
        List<String> words= new LinkedList();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Node parent = head;
        while(i<arr.length) {
            char next = arr[i++];

            if(next == '*') {
                searchForLeaf(parent, words);
                break;
            }

            sb.append(next);
            Node bottom = parent.searchBottom(next);
            if (bottom == null) return null;
            if(i==arr.length && bottom.isEnd) words.add(sb.toString());
            parent = bottom;
        }
        return words;
    }


    public int countWords(){
        LinkedList<String> l = new LinkedList<>();
        searchForLeaf(head,l);
        return l.size();
    }
    public static void main(String[] args){
        SearchDictionary sd = new SearchDictionary();
        sd.addWord("hello");
        sd.addWord("good bye");
        sd.addWord("hell");
        int i = sd.countWords();
        sd.delWord("he");
        sd.delWord("hello");
        Iterable<String> list = sd.query("*");

    }
}
