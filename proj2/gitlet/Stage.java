package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Stage implements Serializable {
    private Map<File, String> FiletoUID;
    private Set<String> UIDs;

    public Stage() {
        FiletoUID = new HashMap<>();
        UIDs = new TreeSet<>();
        save();
    }
    public static Stage load() {
        return readObject(STAGE, Stage.class);
    }

    private void save() {
        writeObject(STAGE, this);
    }

    public void add(File file, String UID) {
        if(UIDs.contains(UID))
            return;
        FiletoUID.put(file, UID);
        UIDs.add(UID);
        save();
    }

    public Set<Map.Entry<File,String>> Entry() {
        return FiletoUID.entrySet();
    }

    public void clean() {
        FiletoUID = new HashMap<>();
        UIDs = new TreeSet<>();
        save();
    }

    public void remove(File file) {
        UIDs.remove(FiletoUID.get(file));
        FiletoUID.remove(file);
        save();
    }

    public boolean exist(String UID) {
        return UIDs.contains(UID);
    }

    public Set<Map.Entry<File, String>> getEntry() {
        return FiletoUID.entrySet();
    }

    public boolean isEmpty() {
        return UIDs.isEmpty();
    }
}