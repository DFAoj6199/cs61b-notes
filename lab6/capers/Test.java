package capers;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        File b = new File("./.abc/aaa");
        b.mkdirs();
        File c = new File(".abc/aaa/.abc");
        c.createNewFile();
    }
}
