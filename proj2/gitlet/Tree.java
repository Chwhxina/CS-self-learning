package gitlet;

import javax.naming.spi.StateFactory;
import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Tree implements Serializable {
    private final Map<String, String> NametoBlob;
    private final Map<String, String> DirtoTree;
    private final File dirPoint;

    public Tree(File dirPoint) {
        this.NametoBlob = new HashMap<>();
        this.DirtoTree = new HashMap<>();
        this.dirPoint = dirPoint;
    }


    public String update() {
        Stage stage = Stage.load();
        for(var i : stage.Entry()) {
            File iFile = i.getKey();
            String iUid = i.getValue();

            //文件在当前Tree管理的目录下，直接进行更新
            if(iFile.toPath().getParent().equals(this.dirPoint.toPath())) {
                this.NametoBlob.put(iFile.getName(), iUid);
                stage.remove(iFile);
                continue;
            }

            //文件夹在Tree的目录的文件夹下，并且Tree有索引
            String dir = dirPoint.toPath().relativize(iFile.toPath()).subpath(0,1).toString();
            if(DirtoTree.containsKey(dir)) {
                readObject(join(TREE_DIR, DirtoTree.get(dir)), Tree.class).update();
                continue;
            }

            //文件夹在Tree的目录的文件夹下，并且Tree无索引
            Tree subTree = new Tree(join(this.dirPoint, dir));
            String subTreeUid = subTree.update();
            this.DirtoTree.put(dir, subTreeUid);
        }

        writeObject(join(TREE_DIR, toUID()), this);
        return toUID();
    }

    public static Tree getTree(String UID) {
        File tree = join(TREE_DIR, UID);
        if(!tree.exists()) {
            System.out.println("Tree is not exist");
        }
        return readObject(tree, Tree.class);
    }

    public String toUID() {
        return sha1(serialize(this));
    }
}
