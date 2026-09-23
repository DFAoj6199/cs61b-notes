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
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int N, M;
        N = 1000;
        M = 10000;

        while (N <= 128000) {
            SLList<Integer> slList = new SLList<>();
            for (int i = 1; i <= N; i++) {
                slList.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < M; i++) {
                slList.getLast();
            }
            Double timeInSeconds = sw.elapsedTime();
            Ns.addLast(N);
            times.addLast(timeInSeconds);
            opCounts.addLast(M);
            N *= 2;
        }

        printTimingTable(Ns, times, opCounts);
    }

}
/*Result
           N     time (s)        # ops  microsec/op
------------------------------------------------------------
        1000         0.01        10000         1.40
        2000         0.03        10000         2.60
        4000         0.05        10000         5.30
        8000         0.11        10000        10.50
       16000         0.21        10000        21.20
       32000         0.44        10000        43.60
       64000         0.87        10000        86.50
      128000         1.89        10000       188.50
*
* */
