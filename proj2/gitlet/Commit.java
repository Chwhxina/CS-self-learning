package gitlet;

// TODO: any imports you need here
import static gitlet.Utils.*;
import static gitlet.Repository.*;

import java.io.File;
import java.io.Serializable;
import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String timestamp;
    private String parent;
    private String Path;
    private String UID;
    /* TODO: fill in the rest of this class. */
    public static final File COMMIT_DIR = join(GITLET_DIR, "");
    public Commit(String message, String parent) {
        this.message = message;
        this.parent = parent;
        if(this.parent == null) {
            this.timestamp = "00:00:00 UTC, Thursday, 1 January 1970";
        }
    }

    public String saveCommit() {
        if(!COMMIT_DIR.exists()) {
            if(!COMMIT_DIR.mkdir()) {
                throw new IllegalArgumentException("create commit dir failed");
            }
        }
        this.UID = sha1(serialize(this));
        File file = join(COMMIT_DIR, this.UID);
        this.Path = file.getPath();
        writeObject(file, this);
        return this.UID;
    }

    public String getPath() {
        return this.Path;
    }
}
