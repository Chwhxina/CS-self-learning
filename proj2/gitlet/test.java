package gitlet;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class test implements Serializable {

    @Test
    public void test() {
        Repository r = new Repository();
        File a = Utils.join(Repository.CWD, "1");
        Blob b = new Blob(a);
    }
}
