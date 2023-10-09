package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Tree implements Serializable {
    private final Map<String, String> UIDtoName;
    private final Map<String, String> UIDtoTree;

    public Tree() {
        UIDtoName = new HashMap<>();
        UIDtoTree = new HashMap<>();
        update();
    }

    public Tree(Tree tree) {
        this.UIDtoName = tree.UIDtoName;
        this.UIDtoTree = tree.UIDtoTree;
        update();
    }

    private void update() {
        Stage stage = Stage.load();
        for(var i : stage.getEntry()) {

        }
    }

    public String getFile(String UID) {
        return UIDtoName.get(UID);
    }

    private boolean isUidExist(String UID) {
        if(UIDtoName.containsKey(UID))
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
