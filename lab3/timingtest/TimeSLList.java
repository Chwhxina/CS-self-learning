package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        System.out.println("Timing table for getLast");
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        SLList<Integer> test;
        int ops;
        double timeInSeconds;
        Stopwatch sw;
        int[] N = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        for (int i : N) {
            Ns.addLast(i);
            test = new SLList<>();
            for(int j = 0; j < i; j++){
                test.addFirst(1);
            }
            sw = new Stopwatch();
            ops = 10000;
            for(int j = 0; j < ops; j++){
                test.getLast();
            }
            timeInSeconds = sw.elapsedTime();
            opCounts.addLast(ops);
            times.addLast(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
