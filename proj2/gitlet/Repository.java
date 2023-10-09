package gitlet;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.security.PublicKey;

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
    public static final File PROJ_DIR = join(CWD, "proj2");
    public static final File GITLET_DIR = join(PROJ_DIR, ".gitlet");
    public static final File REFS_DIR = join(GITLET_DIR, "refs");
    public static final File OBJ_DIR = join(GITLET_DIR, "objects");
    public static final File BLOBS_DIR = join(OBJ_DIR, "blobs");
    public static final File COMMIT_DIR = join(OBJ_DIR, "commits");
    public static final File TREE_DIR = join(OBJ_DIR, "trees");
    public static final File STAGE = join(BLOBS_DIR, "stage");
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
            HEADS_DIR.mkdir();
        } catch (SecurityException | NullPointerException e){
            e.printStackTrace();
        }

        //初始化Commit
        Commit master = new Commit("initial commit", null, null);

        //初始化branch
        String masterUID = master.saveCommit();
        File MASTER = join(HEADS_DIR, "master");
        writeContents(MASTER, masterUID);
        if(MASTER.toPath().startsWith(GITLET_DIR.toPath())) {
            writeContents(HEAD, GITLET_DIR.toPath().relativize(MASTER.toPath()).toString());
        } else {
            System.out.println("HEAD ERROR");
        }

        //初始化STAGE AREA
        Stage stage = new Stage();
        stage.save();
    }

    public void add(String fileName) {
        File file = join(CWD, fileName);
        addFile(file);
    }

    private void addFile(File file) {
        //如果目的文件为文件夹
        if(file.isDirectory()) {
            for(var i : file.listFiles()) {
                addFile(i);
            }
            return;
        }

        //把文件写入暂存区
        Blob addBlob = new Blob(file);
        String UID = addBlob.saveBlob();
        Stage stage = Stage.load();
        stage.add(file, UID);
        stage.save();
    }
}
