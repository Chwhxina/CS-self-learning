package deque;

import org.junit.Test;

import java.security.PublicKey;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class ArrayDeque<Item> implements Deque<Item>{
    protected Item[] items;
    protected int nextfirst;
    protected int nextlast;
    protected int size;
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextfirst = 4;
        nextlast = 5;
    }

    public ArrayDeque(int capacity) {
        if(capacity < 8) {
            items = (Item[]) new Object[8];
        }else {
            items = (Item[]) new Object[capacity];
        }
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
        Item x = items[(nextlast + items.length - 1) % items.length];
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
    public Item get(int index) {
        int first = (nextfirst + 1) % items.length;
        return items[(first + index) % items.length];
    }

    public class Itr implements Iterator<Item> {
        int remaining = size;
        private int thisIndex;
        public Itr() {
            thisIndex = (nextfirst + 1) % items.length;
        }
        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public Item next() {
            Item r = items[thisIndex];
            thisIndex = (thisIndex + 1) % items.length;
            remaining -= 1;
            return r;
        }
    }
    public Iterator<Item> iterator() {
        return new Itr();
    }

    @Override
    public boolean equals(Object o) {
        Deque<?> other;
        if(o instanceof Deque) {
            other = (Deque<?>) o;
        }else
            return false;
        for(int i = 0; i < size; i++) {
            if(!get(i).equals(other.get(i)))
                return false;
        }
        return true;
    }
}
