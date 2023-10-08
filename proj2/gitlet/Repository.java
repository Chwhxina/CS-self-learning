package gitlet;

import java.io.File;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File REFS_DIR = join(GITLET_DIR, "refs");
    public static final File OBJ_DIR = join(GITLET_DIR, "objects");
    public static final File BLOBS_DIR = join(OBJ_DIR, "blobs");
    public static final File COMMIT_DIR = join(OBJ_DIR, "commits");
    public static final File TREE_DIR = join(OBJ_DIR, "trees");
    public static final File HEADS_DIR = join(REFS_DIR, "heads");
    public static final File HEAD = join(GITLET_DIR, "head");
    /* TODO: fill in the rest of this class. */
    public Repository() {
        init();
    }

    /***
     * 初始化仓库
     */
    private void init() {
        //MASTER 表示master分支的文件
        File MASTER = join(REFS_DIR, "master");
        //HEAD文件内存储master的路径
        File HEAD = join(GITLET_DIR, "HEAD");
        //检查是否有仓库存在
        if(GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system " +
                    "already exists in the current directory.");
            return;
        }
        try {
            GITLET_DIR.mkdir();
            REFS_DIR.mkdir();
            OBJ_DIR.mkdir();
            TREE_DIR.mkdir();
            COMMIT_DIR.mkdir();
            BLOBS_DIR.mkdir();
        } catch (SecurityException | NullPointerException e){
            e.printStackTrace();
        }
        Commit master = new Commit("initial commit", null, null);
        String masterUID = master.saveCommit();


    }

    public static void add(File addFile) {
        
    }
}
