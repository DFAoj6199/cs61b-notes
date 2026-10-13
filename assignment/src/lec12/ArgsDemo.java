package lec12;

public class ArgsDemo {
    public static void main(String[] args) {
        int sum = 0;
        for(String s : args) {
            sum += Integer.parseInt(String.valueOf(s));
        }
        System.out.println(sum);
    }
}
