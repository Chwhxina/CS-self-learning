package deque;

import jh61b.junit.In;

public class LinkedListDeque<Item> {
    private class IntNode {
        Item item;
        public IntNode sentinel;
        IntNode prev;
        IntNode next;
        public IntNode(Item i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }
        public Item getIndex(int index) {
            if(index == 0)
                return item;
            return next.getIndex(index - 1);
        }
    }
    private int size;
    private IntNode sentinel;

    public LinkedListDeque() {
        int size = 0;
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(Item x) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        size += 1;
    }

    public void addFirst(Item x) {
        sentinel.next = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(Item x) {
        sentinel.prev = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Item removeFirst() {
        if(isEmpty())
            return null;
        Item r = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return r;
    }

    public Item removeLast() {
        if(isEmpty())
            return null;
        Item r = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return r;
    }

    public int size() {
        return size;
    }

    public Item getRecursive (int index) {
        return sentinel.getIndex(index);
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


}
