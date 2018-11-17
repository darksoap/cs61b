public class LinkedListDeque<T> {

    private class Node<T> {
        public T item;
        public Node prev, next;
        
        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(24, sentinel, sentinel);
        size = 0;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(T item) {
        size += 1;
        if (isEmpty()) {
            Node node = new Node(item, sentinel, sentinel);
            sentinel.next = node;
            sentinel.prev = node;
        }
        else {
            Node node = new Node(item, sentinel.next, sentinel);
            sentinel.next = node;
        }
    }
    
    /** Adds an item to the last of the list. */
    public void addLast(T item) {
        size += 1;
        if (isEmpty()) {
            Node node = new Node(item, sentinel, sentinel);
            sentinel.prev = node;
            sentinel.next = node;
        }
        else {
            Node node = new Node(item, sentinel, sentinel.prev);
            sentinel.prev = node;
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else 
            return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel;
        if (isEmpty()) {
            return;
        }
        else
        while (p.next != sentinel) {
            System.out.println(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        else {
            size -= 1;
            Node tmp = sentinel.next;
            sentinel.next = sentinel.next.next;
            return (T) tmp.item;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        else {
            size -= 1;
            Node tmp = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            return (T) tmp.item;
        }
    }

    public T get(int index) {
        if (index > size - 1){
            return null;
        }
        else {
            int count = 0;
            Node p = sentinel;
            while (count <= index) {
                p = p.next;
            }
            return (T) p.item;
        }
    }
}