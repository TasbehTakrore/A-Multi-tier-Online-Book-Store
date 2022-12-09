package frontEnd;

import java.util.HashMap;

public class RLUcacheSearch {
	 private HashMap<String, nodeSearch> map;
	    private int capacity, count;
	    private nodeSearch head, tail;
	  
	    public RLUcacheSearch(int capacity)
	    {
	        this.capacity = capacity;
	        map = new HashMap<>();
	        head = new nodeSearch("", null);
	        tail = new nodeSearch("", null);
	        head.next = tail;
	        tail.pre = head;
	        head.pre = null;
	        tail.next = null;
	        count = 0;
	    }
	  
	    public void deletenode(nodeSearch node)
	    {
	        node.pre.next = node.next;
	        node.next.pre = node.pre;
	    }
	  
	    public void addToHead(nodeSearch node)
	    {
	        node.next = head.next;
	        node.next.pre = node;
	        node.pre = head;
	        head.next = node;
	    }
	  
	    
	    public Book[] get(String key)
	    {
	        if (map.get(key) != null) {
	            nodeSearch node = map.get(key);
	            Book[] result = node.book;
	            deletenode(node);
	            addToHead(node);
	            System.out.println("Get the Topic from Search cach "
	                               + " for the key: " + key);
	            return result;
	        }
	        System.out.println("Did not get any book"
	                           + " for the key: " + key);
	        return null;
	    }
	  
	    
	    public void set(String key, Book[] book)
	    {
	        System.out.println("Going to set the (Topic):"
	                           + key + " to Search cach ");
	                           
	        if (map.get(key) != null) {
	            nodeSearch node = map.get(key);
	            node.book = book;
	            deletenode(node);
	            addToHead(node);
	        }
	        else {
	            nodeSearch node = new nodeSearch(key, book);
	            map.put(key, node);
	            if (count < capacity) {
	                count++;
	                addToHead(node);
	            }
	            else {
	                map.remove(tail.pre.key);
	                deletenode(tail.pre);
	                addToHead(node);
	            }
	        }
	    }
	}
	  
