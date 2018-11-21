public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst, nextLast;

    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, size());
        items = newItems;
    }

    private int nexthelper(int next) {
        if (next >= items.length) {
            return next - items.length;
        }
        if (next < 0) {
            return items.length + next;
        }
        return next;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size() * 2);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = nexthelper(nextFirst - 1);
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size() * 2);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = nexthelper(nextFirst + 1);
    }

    public boolean isEmpty() {
        return  size == 0;
    }

    public int size() {
        return  size;
    }

    public void printDeque() {
        for (nextFirst - 1) {
            System.out.print(item);
            System.out.print(' ');
        }
        System.out.print('\n');
    }

    private int removehelper(int remove) {

    }

    public T removeFirst() {
        size -= 1;
        nextFirst
    }

    public T removeLast(){

    }

    public T get(int index){

    }
}