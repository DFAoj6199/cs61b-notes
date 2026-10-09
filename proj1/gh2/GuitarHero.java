package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static GuitarString[] strings = new GuitarString[37];

    public static double getFrequency(int index) {
        return 440.0 * Math.pow(2, (index - 24.0) / 12.0);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(getFrequency(i));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            double sample = 0.0;
            for (int i = 0; i < 37; i++) {
                sample += strings[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < 37; i++) {
                strings[i].tic();
            }
        }

    }
}
