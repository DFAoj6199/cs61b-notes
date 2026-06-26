import java.util.Scanner;

public class CollatzConjecture {
    public static int nextNumber(int num) {
        if (num % 2 == 0) {
            return num / 2;
        } else {
            return 3 * num + 1;
        }
    }

    public static void main(String[] args) {
        System.out.print("输入起始数字:");

        Scanner sc = new Scanner(System.in);
        int begin = sc.nextInt();

        System.out.print(begin + " ");

        while (begin != 1) {
            begin = nextNumber(begin);
            System.out.print(begin + " ");
        }
    }
}
