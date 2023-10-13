package gitlet;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.Serializable;
import static gitlet.Repository.*;
import static gitlet.Utils.*;

public class test implements Serializable {
    @Test
    public void init() {
        Main.main(new String[]{"init"});
    }
    @Test
    public void add() {
        Main.main((new String[] {"add", "2.txt"}));
    }
    @Test
    public void commit() {
        Main.main(new String[] {"commit", "test commit"});
    }
    @Test
    public void dump() {
        String catfile = "-s";
        Main.main(new String[] {"dump", catfile});
    }
    @Test
    public void test2() {
        Stage stage = Stage.load();
  }
}
