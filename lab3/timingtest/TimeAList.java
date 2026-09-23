package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.A;

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
        AList<Integer> list = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        Stopwatch sw = new Stopwatch();
        int nextRecord = 1000;
        for (int i = 1; i <= 524288000; i++) {
            list.addLast(1);
            if (i == nextRecord) {
                double timeInSeconds = sw.elapsedTime();
                Ns.addLast(i);
                times.addLast(timeInSeconds);
                nextRecord *= 2;
            }
        }
        printTimingTable(Ns, times, Ns);
    }
}

/*
* Resize strategy: additive
*            N     time (s)        # ops  microsec/op
------------------------------------------------------------
        1000         0.00         1000         0.00
        2000         0.00         2000         1.00
        4000         0.01         4000         2.50
        8000         0.03         8000         3.50
       16000         0.08        16000         5.06
       32000         0.24        32000         7.38
       64000         0.79        64000        12.41
      128000         3.01       128000        23.52

* Resize strategy: multiplicative (*2)
*           N     time (s)        # ops  microsec/op
           N     time (s)        # ops  microsec/op
------------------------------------------------------------
        1000         0.00         1000         0.00
        2000         0.00         2000         0.00
        4000         0.00         4000         0.00
        8000         0.00         8000         0.00
       16000         0.00        16000         0.06
       32000         0.00        32000         0.03
       64000         0.00        64000         0.03
      128000         0.00       128000         0.02
      256000         0.00       256000         0.02
      512000         0.01       512000         0.01
     1024000         0.01      1024000         0.01
     2048000         0.02      2048000         0.01
     4096000         0.04      4096000         0.01
     8192000         0.08      8192000         0.01
    16384000         0.16     16384000         0.01
    32768000         0.35     32768000         0.01
    65536000         1.06     65536000         0.02
   131072000         2.39    131072000         0.02
   262144000         5.10    262144000         0.02
   524288000        10.71    524288000         0.02
*
* Resize strategy: multiplicative (*1.01)
*            N     time (s)        # ops  microsec/op
------------------------------------------------------------
        1000         0.00         1000         0.00
        2000         0.00         2000         0.00
        4000         0.00         4000         0.25
        8000         0.00         8000         0.25
       16000         0.00        16000         0.19
       32000         0.01        32000         0.16
       64000         0.01        64000         0.14
      128000         0.02       128000         0.13
      256000         0.03       256000         0.10
      512000         0.05       512000         0.10
     1024000         0.13      1024000         0.13
* */
