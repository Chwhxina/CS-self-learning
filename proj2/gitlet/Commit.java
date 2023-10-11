package gitlet;

// TODO: any imports you need here
import static gitlet.Utils.*;
import static gitlet.Repository.*;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable, Dumpable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String timestamp;
    private String parentUID;
    private String treeUID;
    /* TODO: fill in the rest of this class. */
    public Commit(String message, String parentUID) {
        this.message = message;
        this.parentUID = parentUID;
        if(this.parentUID == null) {
            this.timestamp = "00:00:00 UTC, Thursday, 1 January 1970";
            this.treeUID = null;
        } else {
            //获取父Commit
            Commit parent = readObject(join(COMMIT_DIR, parentUID), Commit.class);
            //设置时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss 'CST', EEEE, d MMMM yyyy");
            this.timestamp = sdf.format(date);
            //更新树
            Tree tree = parent.getTree();
            tree.update();
            this.treeUID = tree.toUID();
        }
    }

    public String saveCommit() {
        String UID = sha1(serialize(this));
        File file = join(COMMIT_DIR, UID);
        writeObject(file, this);
        return UID;
    }

    public static Commit getCommit(File file) {
        return readObject(file, Commit.class);
    }

    public Tree getTree() {
        if(this.treeUID == null)
            return new Tree(CWD);
        return readObject(join(TREE_DIR, treeUID), Tree.class);
    }

    @Override
    public void dump() {
        System.out.println("type: Commit");
        System.out.println("-------------------------------------------");
        System.out.printf("timestamp: %s%n",timestamp);
        System.out.printf("message: %s%n", message);
        if(parentUID == null)
            System.out.println("parent: null");
        else
            System.out.printf("parent: %s%n", parentUID);
        System.out.printf("tree: %s%n", treeUID);
        System.out.println("-------------------------------------------");
    }
}
