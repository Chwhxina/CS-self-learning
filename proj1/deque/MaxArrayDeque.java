package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> arrayComparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        arrayComparator = c;
    }
    public T max() {
        T maxElement = get(0);
        for(int i = 0; i < size(); i++) {
            if(arrayComparator.compare(maxElement, get(i)) < 0)
                maxElement = get(i);
        }
        return maxElement;
    }
    public T max(Comparator<T> c) {
        T maxElement = get(0);
        for(int i = 0; i < size(); i++) {
            if(c.compare(maxElement, get(i)) < 0)
                maxElement = get(i);
        }
        return maxElement;
    }
}
