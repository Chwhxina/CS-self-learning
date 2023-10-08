package gitlet;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository implements Serializable {
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
    public static final File STAGE_DIR = join(OBJ_DIR, "stage");
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
            STAGE_DIR.mkdir();
        } catch (SecurityException | NullPointerException e){
            e.printStackTrace();
        }

        //初始化Commit
        Commit master = new Commit("initial commit", null, null);

        //初始化branch
        String masterUID = master.saveCommit();
        File MASTER = join(HEADS_DIR, masterUID);
        writeContents(MASTER, masterUID);
        if(MASTER.toPath().startsWith(GITLET_DIR.toPath())) {
            writeContents(HEAD, MASTER.toPath().relativize(GITLET_DIR.toPath()));
        } else {
            System.out.println("HEAD ERROR");
        }
    }

    public static void add(File addFile) {
        //如果目的文件为文件夹
        if(addFile.isDirectory()) {
            for(var i : addFile.listFiles()) {
                add(i);
            }
            return;
        }

        Blob addBlob = new Blob(addFile);

    }
}
