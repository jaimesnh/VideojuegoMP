package tests;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class TestingUtils {
    public static void setInput(String input) {
        resetInput();
        flush();

        input += "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    public static void setInput(String... input) {
        setInput(String.join("\n", input));
    }

    public static void flush() {
        System.out.flush();
    }

    // Clean
    public static void resetInput() {
        System.setIn(System.in);
    }
}
