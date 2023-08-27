package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    Comparator<Item> arrayComparator;
    public MaxArrayDeque(Comparator<Item> c) {
        arrayComparator = c;
    }
    public Item max() {
        Item maxElement = items[(nextfirst + 1) % items.length];
        int mark = (nextfirst + 1) % items.length;
        for(int i = 0; i < size; i++) {
            if(arrayComparator.compare(maxElement, items[mark]) < 0)
                maxElement = items[mark];
            mark = (mark + 1) % items.length;
        }
        return maxElement;
    }
}
