package gitlet;
import java.io.File;
import java.io.Serializable;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Blob implements Serializable {
    private String version;
    private byte[] contend;
    private String fileName;
    private final File BLOBS_DIR = join(GITLET_DIR, "object", "blob");
    private String UID;

    /***
     * 创建新的Blob文件并初始化
     * @param version 版本号
     * @param filePath 文件路径
     */
    public Blob(String version, File filePath) {
        this.contend = Utils.readContents(filePath);
        this.version = version;
        this.fileName = filePath.getName();
        saveBlob();
    }

    public Blob(File filePath) {
        this.contend = Utils.readContents(filePath);
        this.version = "1.0";
        this.fileName = filePath.getName();
        saveBlob();
    }

    /****
     * 存储Blob块
     */
    private void saveBlob() {
        if (!BLOBS_DIR.exists()) {
            if(!BLOBS_DIR.mkdir()) {
                System.out.println("create project dir failed");
            }
        }
        this.UID = sha1(serialize(this));
        File blob = join(GITLET_DIR, UID);
        writeObject(blob, this);
    }

    public String getVersion() {
        return version;
    }

    public String toHashCode() {
        return Utils.sha1(serialize(this));
    }
}
