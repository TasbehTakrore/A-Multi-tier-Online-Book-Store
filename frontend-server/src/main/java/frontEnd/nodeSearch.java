package frontEnd;

public class nodeSearch {
    String key;
    Book[] book;
    nodeSearch pre;
    nodeSearch next;
  
    public nodeSearch(String key, Book[] book)
    {
        this.key = key;
        this.book = book;
    }
}
