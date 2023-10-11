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
        Main.main(new String[]{"init"});
        Main.main(new String[]{"add", "1"});
        Main.main(new String[]{"commit", "test"});
    }

    @Test
    public void test2() {
        Stage stage = Stage.load();
  }
}
