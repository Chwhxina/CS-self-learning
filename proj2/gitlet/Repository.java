package gitlet;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.security.PublicKey;
import java.util.Objects;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository implements Serializable, Dumpable {
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
    public static final File STAGE = join(BLOBS_DIR, "stage");
    public static final File HEADS_DIR = join(REFS_DIR, "heads");
    public static final File HEAD = join(GITLET_DIR, "head");
    public static final File INDEX = join(GITLET_DIR, "index");
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
        Commit master = new Commit("initial commit", null);

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
        save();
    }

    public static void add(String fileName) {
        File file = join(CWD, fileName);
        if(!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        Repository repository = load();
        repository.addFile(file);
    }

    public static void commit(String message) {
        //检查Stage
        Stage stage = Stage.load();
        if(stage.isEmpty()) {
            System.out.println("No changes added to the commit.");
            return;
        }
        //找到head
        String head = readContentsAsString(HEAD);
        String parentUid = readContentsAsString(join(GITLET_DIR, head));
        //建立新Commit
        Commit newCommit = new Commit(message, parentUid);
        String newHead = newCommit.saveCommit();
        //修改head
        writeContents(join(GITLET_DIR, head), newHead);
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
        Stage stage = Stage.load();
        String UID = addBlob.saveBlob();
        stage.add(file, UID);
    }

    public static String simpleNameToName(String simple) {
        if(simple.equals("-s")) {
            return STAGE.getPath();
        }

        if(!OBJ_DIR.exists()) {
            System.out.println("OBJ_DIR is null");
            return null;
        }
        for(var i : Objects.requireNonNull(BLOBS_DIR.listFiles())) {
            if(i.getName().startsWith(simple)) {
                return i.getPath();
            }
        }

        for (var i : Objects.requireNonNull(COMMIT_DIR.listFiles())) {
            if(i.getName().startsWith(simple)) {
                return i.getPath();
            }
        }

        for (var i : Objects.requireNonNull(TREE_DIR.listFiles())) {
            if(i.getName().startsWith(simple)) {
                return i.getPath();
            }
        }

        System.out.println("no needed file");
        return null;
    }

    private void save() {
        writeObject(INDEX, this);
    }

    public static Repository load() {
        return readObject(INDEX, Repository.class);
    }

    @Override
    public void dump() {
        File branchfile = join(GITLET_DIR, readContentsAsString(HEAD));
        String branch = branchfile.getName();
        String commitID = readContentsAsString(branchfile);
        System.out.println("type: Repository");
        System.out.printf("Branch: %s \n CommitID: %s%n", branch, commitID);
    }
}
