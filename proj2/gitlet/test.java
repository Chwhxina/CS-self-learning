package gitlet;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class test implements Serializable {
    @Test
    public void test() {
        //Blob b = new Blob(Utils.join(Repository.CWD, "1"));
        Blob b = Blob.getBlob(Utils.join(Repository.BLOBS_DIR, "34f34a9fcddcdfe1306506d2c557786fd9c929ba"));
        b.writeFile(Utils.join(Repository.CWD, "2"));
    }
}
