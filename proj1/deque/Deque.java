package deque;

import net.sf.saxon.type.ItemType;

import java.lang.reflect.Type;
import java.util.Iterator;

public interface Deque<Item> extends Iterable<Item> {
    public void addFirst(Item item);
    public void addLast(Item item);
    default boolean isEmpty() {
        return size() == 0;
    }
    public int size();
    public void printDeque();
    public Item removeFirst();
    public Item removeLast();
    public Item get(int index);
    Iterator<Item> iterator();
}
