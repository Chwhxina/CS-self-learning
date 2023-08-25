package timingtest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Integer> test;
        AList<Integer> opCounts = new AList<>();
        AList<Double> times = new AList<>();
        int ops;
        double timeInSeconds;
        Stopwatch sw;
        int[] N = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        for (int i : N) {
            Ns.addLast(i);
            test = new AList<>();
            sw = new Stopwatch();
            ops = 0;
            for(int j = 0; j < i; j++){
                test.addLast(1);
                ops++;
            }
            timeInSeconds = sw.elapsedTime();
            opCounts.addLast(ops);
            times.addLast(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
