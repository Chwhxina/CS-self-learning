package deque;

import java.security.PublicKey;

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextfirst;
    private int nextlast;

    boolean isEmpty() {
        return nextfirst + 1 == nextlast;
    }
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextfirst = 4;
        nextlast = 5;
    }

    public void addLast(Item x) {
        if(nextlast == nextfirst) {
            resize(items.length * 2);
        }
        items[nextlast] = x;
        nextlast = (nextlast + 1) % items.length;
        size += 1;
    }
    public void addFirst(Item x) {
        if(nextfirst == nextlast) {
            resize(items.length * 2);
        }
        items[nextfirst] = x;
        nextfirst = (nextfirst - 1 + items.length) % items.length;
        size += 1;
    }

    public Item removeFirst() {
        if(nextfirst + 1 == nextlast)
            return null;
        Item x = items[(nextfirst + 1) % items.length];
        nextfirst = (nextfirst + 1) % items.length;
        size -= 1;
        return x;
    }

    public Item removeLast() {
        if(nextlast - 1 == nextfirst)
            return null;
        Item x = items[nextlast - 1];
        nextlast = (nextlast - 1 + items.length) % items.length;
        size -= 1;
        return x;
    }

    public void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, (nextfirst + 1) % items.length, a, 0, items.length - nextfirst - 1);
        if(nextfirst == 0)
            return;
        else{
            System.arraycopy(items, 0, a, items.length - nextfirst - 1, nextlast);
        }
        items = a;
        nextlast = size;
        nextfirst = items.length - 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if(isEmpty())
            return;
        int mark = (nextfirst + 1) % items.length;
        for(int i = 0; i < size; i++) {
            if(i == 0)
                System.out.print(items[mark]);
            else{
                System.out.print(" <> " + items[mark]);
            }
            mark = (mark + 1) % items.length;
        }
    }
}
