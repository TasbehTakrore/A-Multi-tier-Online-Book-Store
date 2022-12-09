package frontEnd;

//this node used in LRUcach-get book info
class nodeInfo {
    int key;
    Book book;
    nodeInfo pre;
    nodeInfo next;
  
    public nodeInfo(int key, Book book)
    {
        this.key = key;
        this.book = book;
    }
}
