package gitlet;
import static gitlet.Repository.*;
/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if(args == null){
            System.out.println("Please enter a command.");
            return;
        }
        Repository repo;
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                repo = new Repository();
                break;
            case "add":
                // TODO: handle the `add [filename]` command

                break;
            // TODO: FILL THE REST IN
        }
    }
}
