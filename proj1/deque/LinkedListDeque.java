package deque;


import java.util.Iterator;
public class LinkedListDeque<T> implements Deque<T> {
    private class IntNode {
        T item;
        public IntNode sentinel;
        IntNode prev;
        IntNode next;
        public IntNode(T i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }
        public T getIndex(int index) {
            if(index == 0)
                return item;
            return next.getIndex(index - 1);
        }
    }
    private IntNode sentinel;
    private int size;
    public LinkedListDeque() {
        int size = 0;
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T x) {
        sentinel.next = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T x) {
        sentinel.prev = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public T removeFirst() {
        if(isEmpty())
            return null;
        T r = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return r;
    }

    public T removeLast() {
        if(isEmpty())
            return null;
        T r = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return r;
    }

    public T get(int index) {
        if(index >= size)
            return null;
        IntNode p = sentinel;
        for(int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public int size() {
        return size;
    }

    public T getRecursive (int index) {
        if(index >= size)
            return null;
        return sentinel.next.getIndex(index);
    }

    public void printDeque() {
        if(isEmpty())
            return;
        for(int i = 1; i <= size; i++) {
            if(i == 1) {
                System.out.print(getRecursive(i));
                continue;
            }
            System.out.print("<->" + getRecursive(i));
        }
    }

    private class Itr implements Iterator<T> {
        private IntNode thisNode;
        public Itr() {
            thisNode = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return thisNode != sentinel;
        }

        @Override
        public T next() {
            T r = thisNode.item;
            thisNode = thisNode.next;
            return r;
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

    public Iterator<T> iterator() {
        return new Itr();
    }

}
