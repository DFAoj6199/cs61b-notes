package capers;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        File a = new File("abc");
        a.mkdir();
        File b = new File("./abc/aaa");
        b.mkdir();
    }
}
