package gitlet;

import java.io.Serializable;
import java.util.*;

public class Stage implements Serializable {
    public Set<String> UIDs;
    public Stage() {
        UIDs = new TreeSet<>();
    }
}