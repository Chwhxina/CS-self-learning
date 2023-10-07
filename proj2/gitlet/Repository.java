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
        } catch (SecurityException | NullPointerException e){
            e.printStackTrace();
        }
        //新建一个初始Commit
        Commit initCommit = new Commit("init message", null);
        //initUid存储初始Commit的UID
        String initUid = initCommit.saveCommit();
        //将UID写入MASTER
        writeContents(MASTER, initUid);
        //将MASTER的路径写入HEAD
        writeContents(HEAD, MASTER.getPath());
    }

    public static void add(File addFile) {
        
    }
}
