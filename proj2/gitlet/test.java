package gitlet;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class test implements Serializable {
    @Test
    public void test() {
        Repository repository = new Repository();
        repository.add("1");
        Stage stage = Stage.load();
    }
}
