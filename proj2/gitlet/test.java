package gitlet;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.Serializable;
import static gitlet.Repository.*;
import static gitlet.Utils.*;

public class test implements Serializable {
    @Test
    public void test() {
        Repository repository = new Repository();
        Repository.add("1");
        Repository.commit("test");
    }

    @Test
    public void test2() {
        DumpObj.main();
    }
}
