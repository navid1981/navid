package Data;

import java.util.*;

public class DS {

    HashSet<Integer> hashSet=new HashSet<>();
    Hashtable<Integer,Integer> hashtable=new Hashtable<>();
    HashMap<Integer,Integer> hashMap=new HashMap<>();

    TreeMap<Integer,Integer> treeMap=new TreeMap<>();
    TreeSet<Integer> treeSet=new TreeSet<>();

    LinkedHashMap<Integer,Integer> linkedHashMap=new LinkedHashMap<>();
    LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>();



//    Throws exception	Returns special value
//    Insert	add(e)	    offer(e)
//    Remove	remove()	poll()
//    Examine	element()	peek()
    Queue<Integer> queue=new LinkedList<>();

    Queue<Integer> queue1=new PriorityQueue<>((a,b)->a-b);

//    peek()
//    pop()
//    push(E item)
    Stack<Integer> stack=new Stack<>();

    public static void main(String[] args){
        DS ds=new DS();
        ds.testHash();
    }

    void testHash(){
        //exception
//        hashtable.put(1,null);
//        hashtable.put(null,1);

        hashMap.put(null,null);
        hashMap.put(null,null);

        //just add one
        hashSet.add(1);
        hashSet.add(1);
        hashSet.add(2);
        Iterator iterator=hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        queue1.add(3);
        queue1.add(1);
        System.out.println(queue1.poll());
    }
}
