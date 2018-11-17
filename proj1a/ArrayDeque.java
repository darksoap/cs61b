public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst, nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int next(int n) {
        if (n < 0) {
            return -n;
        }
        else if (n > items.length) {
            return n -items.length - 1;
        }
        else
            return n;
    }

    public void resize(){

    }

    public void addFirst(T item){
        size += 1;
        items[nextFirst] = item;
        nextFirst = next(nextFirst -1);
    }

    /** Inserts X into the back of the list. */
    public void addLast(T item) {
        size += 1;
        items[nextLast] = item;
        nextLast = next(nextLast + 1);
    }

    /** Returns the item from the back of the list. */
    public int getLast() {

        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {

        return items[i];
    }

    public boolean isEmpty(){

    }

    /** Returns the number of items in the list. */
    public int size() {

        return size;
    }

    public T removeFirst(){

    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        int x = getLast();
        size = size - 1;
        return x;
    }

    public T get(int index){

    }
} 