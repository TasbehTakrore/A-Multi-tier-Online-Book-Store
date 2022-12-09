package frontEnd;

import java.util.HashMap;

public class LRUcacheInfo {
	 private HashMap<Integer, nodeInfo> map;
	    private int capacity, count;
	    private nodeInfo head, tail;
	  
	    public LRUcacheInfo(int capacity)
	    {
	        this.capacity = capacity;
	        map = new HashMap<>();
	        head = new nodeInfo(-1, null);
	        tail = new nodeInfo(-1, null);
	        head.next = tail;
	        tail.pre = head;
	        head.pre = null;
	        tail.next = null;
	        count = 0;
	    }
	  
	    public void deletenode(nodeInfo node)
	    {
	        node.pre.next = node.next;
	        node.next.pre = node.pre;
	    }
	  
	    public void addToHead(nodeInfo node)
	    {
	        node.next = head.next;
	        node.next.pre = node;
	        node.pre = head;
	        head.next = node;
	    }
	  
	    
	    public Book get(int key)
	    {
	        if (map.get(key) != null) {
	            nodeInfo node = map.get(key);
	            Book result = node.book;
	            deletenode(node);
	            addToHead(node);
	            System.out.println("Get the book from Info cach: " + result.getTitle()
	                               + " for the key: " + key);
	            return result;
	        }
	        System.out.println("Did not get any book in Info cach"
	                           + " for the key: " + key);
	        return null;
	    }
	  
	    
	    public void set(int key, Book book)
	    {
	        System.out.println("Going to set the (key, "
	                           + "book) : (" + key + ", "
	                           + book.getTitle() + ") to Info cach");
	        if (map.get(key) != null) {
	            nodeInfo node = map.get(key);
	            node.book = book;
	            deletenode(node);
	            addToHead(node);
	        }
	        else {
	            nodeInfo node = new nodeInfo(key, book);
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
	    public void removeHead(int key) {
	    	
	    	
	    	nodeInfo node = map.get(key);
	    	deletenode(node); // here.. delete head
	    	map.remove(key);
	    	count --;
	    	
	    }
	}
	  