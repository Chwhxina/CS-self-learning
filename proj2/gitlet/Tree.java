package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Tree implements Serializable {
    private final Map<String, Set<File>> UIDtoFile;
    private final Map<String, String> UIDtoTree;

    public Tree() {
        UIDtoFile = new HashMap<>();
        UIDtoTree = new HashMap<>();
    }

    /***
     *
     * @param UID 文件的hash值
     * @param name 文件的路径
     */
    public void addFile(String UID, File name) {
        if(UIDtoFile.containsKey(UID)) {

        }
    }

    public Set<File> getFile(String UID) {
        return UIDtoFile.get(UID);
    }

    private boolean isUidExist(String UID) {
        if(UIDtoFile.containsKey(UID))
            return true;
        for(var i : UIDtoTree.entrySet()) {
            var otherUID = i.getValue();
            var otherFile = join(TREE_DIR, otherUID);
            Tree other = readObject(otherFile, Tree.class);
            if(other.isUidExist(UID))
                return true;
        }
        return false;
    }
}
