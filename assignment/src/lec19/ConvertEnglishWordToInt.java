package lec19;

import java.util.Scanner;

public class ConvertEnglishWordToInt {
    public static int convert(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            res += ((int)(c - 'a' + 1) * (int)Math.pow(27.0, (n - 1 - i)));
        }
        return res;
    }

    static void main() {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(convert(s));
    }
}
