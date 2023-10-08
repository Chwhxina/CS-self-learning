package gitlet;
import java.io.File;
import java.io.Serializable;
import static gitlet.Utils.*;
import static gitlet.Repository.*;

public class Blob implements Serializable {
    private byte[] contend;
    /***
     * 初始化Blob
     * @param filePath 序列化的文件路径
     */
    public Blob(File filePath) {
        readFile(filePath);
    }
    /***
     * 将文件读取转化为byte[]数组
     * @param filePath 路径
     */
    private void readFile(File filePath) {
        this.contend = Utils.readContents(filePath);
    }
    /***
     * 将自身存储的byte[]存储为文件
     * @param filepath 路径
     */
    public void writeFile(File filepath) {
        writeContents(filepath, this.contend);
    }
    /***
     * 将序列化后的文件转换为Blob类
     * @param file 被序列化后的文件路径
     * @return 对应的Blob文件
     */
    public static Blob getBlob(File file) {
        return readObject(file, Blob.class);
    }
    /****
     * 存储Blob块
     */
    public String saveBlob() {
        if (!BLOBS_DIR.exists()) {
            if(!BLOBS_DIR.mkdir()) {
                System.out.println("create project dir failed");
            }
        }
        var sha1code = sha1(serialize(this));
        writeObject(join(BLOBS_DIR, sha1code), this);
        return sha1code;
    }
}
