package statistics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * This class implements a MultiMap data structure. A MultiMap is an associative structure where multiple elements can be mapped
 * under a one key. An an example consider the following sequence of statements:
 * 1. map.put(k1, v1);
 * 2. map.put(k1, v2);
 * 3. List<V> = map.get(k1);
 * 
 * The last operation should return a List<V> of values comprised of the entries {v1, v2}
 */
public class MultiMap<K, V> {
	private class Node<K,V>{
		K key;
		V value;
		Node next=null;
		Node(K k, V v){
			key = k;
			value = v;
		}
	}
	Node first=null, last=null;

	/**
	 * Sole constructor of the class which builds an empty MultiMap.
	 */
	public MultiMap() {}

	public String toString(){
		String ret = "";
		Node temp = first;
		for(int i =0;temp != null; i++){
			ret += "Nodo n: " +(i+1)+" key: "+ temp.key + " value: "+temp.value+"\n";
			temp = temp.next;
		}
		return ret;
	}
		
	/**
	 * Stores the value (value)given as an input under the key (key).
	 * 
	 * @param key: 		key parameter which serves as an index in the MultiMap data structure
	 * @param value: 	value parameter to be index under the key
	 */
	public void put(K key, V value) {
		if(first==null){
			first = new Node(key,value);
			last = first;
		}
		else{
			last.next = new Node(key,value);
			last = last.next;
		}
	}
	
	/**
	 * Returns a Set view of the keys contained in this map.
	 * 
	 * @return a set view of the keys contained in this map.
	 */
	public Set<K> keySet() {
		HashSet<K> set = new HashSet<>();
		Node temp = first;
		while(temp!=null){
			set.add((K) temp.key);
			temp=temp.next;
		}
		return set;
	}

	/**
	 * Returns a List of objects stored under the key (key).
	 * 
	 * @return a List of objects stored under the key.
	 */
	public List<V> get(Object key) {
		ArrayList<V> list = new ArrayList<>();

		Node temp = first;
		while (temp != null) {
			if ((temp.key).equals(key))
				list.add((V) (temp.value));
			temp = temp.next;
		}
		return list;

	}
}
