package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Tree implements Serializable, Dumpable {
    private final Map<String, String> NametoBlob;
    private final Map<String, String> DirtoTree;
    private final File dirPoint;

    public Tree(File dirPoint) {
        this.NametoBlob = new HashMap<>();
        this.DirtoTree = new HashMap<>();
        this.dirPoint = dirPoint;
    }

    public void update() {
        Stage stage = Stage.load();
        updateInTree(stage);
        stage.clean();
    }

    public void updateInTree(Stage stage) {
        Iterator<Map.Entry<String, String>> iterator = stage.Entry().iterator();
        while (iterator.hasNext()) {
            var i = iterator.next();
            File iFile = join(CWD, i.getKey());
            String iUid = i.getValue();

            //文件在当前Tree管理的目录下，直接进行更新
            if (iFile.toPath().getParent().equals(this.dirPoint.toPath())) {
                this.NametoBlob.put(iFile.getName(), iUid);
                stage.used(i.getKey(), iUid);
                continue;
            }

            //文件夹在Tree的目录的文件夹下，并且Tree有索引
            String dir = dirPoint.toPath().relativize(iFile.toPath()).subpath(0, 1).toString();
            if (DirtoTree.containsKey(dir)) {
                readObject(join(TREE_DIR, DirtoTree.get(dir)), Tree.class).update();
                continue;
            }

            //文件夹在Tree的目录的文件夹下，并且Tree无索引
            if (iFile.toPath().startsWith(this.dirPoint.toPath())) {
                Tree subTree = new Tree(join(this.dirPoint, dir));
                subTree.updateInTree(stage);
                String subTreeUid = subTree.toUID();
                this.DirtoTree.put(dir, subTreeUid);
            }
        }

        writeObject(join(TREE_DIR, toUID()), this);
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

    @Override
    public void dump() {
        System.out.println("type: Tree");
        System.out.println("---------------------------------");
        System.out.printf("dir point: %s%n", dirPoint);
        System.out.println("---------------------------------");
        for(var i : NametoBlob.entrySet()) {
            System.out.println("following is blob to filename");
            System.out.printf("%s: %s%n", i.getValue(), i.getKey());
        }
        for(var i : DirtoTree.entrySet()) {
            System.out.println("following is Tree to dirname");
            System.out.printf("%s: %s%n", i.getValue(), i.getKey());
        }
    }
}
