package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Stage implements Serializable, Dumpable {
    private Map<String, String> FiletoUID;
    private Set<String> UIDs;
    private Set<String> usedUID;

    public Stage() {
        FiletoUID = new HashMap<>();
        UIDs = new TreeSet<>();
        usedUID = new TreeSet<>();
        save();
    }
    public static Stage load() {
        return readObject(STAGE, Stage.class);
    }

    private void save() {
        writeObject(STAGE, this);
    }

    public void add(String relative, String UID) {
        FiletoUID.put(relative, UID);
        UIDs.add(UID);
        save();
    }

    public Set<Map.Entry<String,String>> Entry() {
        return FiletoUID.entrySet();
    }

    public void used(String relative, String UID) {
        usedUID.add(UID);
    }

    public void clean() {
        //清除所有冗余的Blob
        for(var i : UIDs) {
            if(usedUID.contains(i)) {
                File del = join(BLOBS_DIR, i);
                restrictedDelete(del);
            }
        }
        //重置stage
        this.FiletoUID = new HashMap<>();
        this.UIDs = new TreeSet<>();
        this.usedUID = new TreeSet<>();
        save();
    }

    public boolean exist(String UID) {
        return UIDs.contains(UID);
    }

    public boolean isEmpty() {
        return UIDs.isEmpty();
    }

    @Override
    public void dump() {
        System.out.println("---------------------");
        System.out.println("stage area:");
        System.out.printf("the number of files: %d\nthe number of UIDs: %d%n", this.FiletoUID.size(), this.UIDs.size());
        for(var i : this.FiletoUID.entrySet()) {
            System.out.printf("%s: %s%n", i.getValue(), i.getKey());
        }
        System.out.println("---------------------");
    }
}