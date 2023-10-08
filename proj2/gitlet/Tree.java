package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree implements Serializable {
    private final Map<File, String> NametoUID;

    public Tree() {
        NametoUID = new HashMap<>();
    }

    public boolean exist(File file) {
        return NametoUID.containsKey(file);
    }

    /***
     *
     * @param UID 文件的hash值
     * @param name 文件的路径
     */
    public void addItem(String UID, File name) {
        NametoUID.put(name, UID);
    }

    /***
     * 返回Tree中的所有File指针
     * @return File列表
     */
    public List<File> getFile() {
        List<File> res = new ArrayList<File>();
        for(var i : NametoUID.entrySet()) {
            res.add(i.getKey());
        }
        return res;
    }
}
