package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Stage implements Serializable {
    private Map<File, String> FiletoUID;

    public Stage() {
        FiletoUID = new HashMap<>();
    }
    public static Stage load() {
        return readObject(STAGE, Stage.class);
    }

    public void save() {
        writeObject(STAGE, this);
    }

    public void add(File file, String UID) {
        FiletoUID.put(file, UID);
    }

    public Set<Map.Entry<File, String>> getEntry() {
        return FiletoUID.entrySet();
    }
}