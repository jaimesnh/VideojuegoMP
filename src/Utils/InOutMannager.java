package Utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class InOutMannager implements Serializable{

    private static boolean debug = false; // Enables debug mode to print debug messages (True = on, False = off)
    private static Scanner scanner = new Scanner(System.in);
    private static String PRINT_PADDING = "|    ";

    public static void print(String msg) {
        System.out.print(PRINT_PADDING + msg);
    }


    public static void cls() {
        for (int i = 0; i < 100; i++) print("\n");

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {}
    }


    public static void moveCursorUp(int lines) {
        System.out.print("\033[" + lines + "A");
    }


    public static void moveCursorDown(int lines) {
        System.out.print("\033[" + lines + "B");
    }

    public static boolean askYesNo(String prompt) {
        InOutMannager.print("\n");

        print(String.format("%s (%s=[1] / %s=[0])?\n", prompt, "Si", "No"));

        int option = readInt("Escoja una opción: ", 0, 1);

        return option == 1;
    }


    public static void pause() {
        print("Presiona Enter para continuar...");
        System.out.print("\033[8m\033[?25l"); // Hide the cursor and disable echo
        scanner.nextLine();
        System.out.print("\033[28m\033[?25h"); // Reset the cursor and enable echo
    }

    public static int readInt() throws NumberFormatException {
        return readInt("");
    }


    public static int readInt(String prompt) throws NumberFormatException {
        String input = readString(prompt);
        int value = Integer.parseInt(input);

        return value;
    }

    public static int readInt(String prompt, int min, int max) throws NumberFormatException {
        int option = readInt(prompt);
        while (option < min || option > max) {
            print("Opción invalida. Por favor intentelo de nuevo.\n");
            option = readInt(prompt);
        }
        return option;
    }


    public static String readString() {
        return readString("");
    }

    public static String readString(String prompt) {
        // Print the prompt
        print(prompt);
        return InOutMannager.scanner.nextLine();
    }

    public static String readString(String prompt, Object... params) {
        return readString(String.format(prompt, (Object[]) params));
    }

    public static void log(Object message) {
        if (InOutMannager.debug) {
            System.out.print("[DEBUG] ");
            System.out.println(message);
        }
    }

    // Getters//Setters
    public static boolean isDebug() {
        return debug;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setDebug(boolean debug) {
        InOutMannager.debug = debug;
    }

    public static void setScanner(Scanner scanner) {
        InOutMannager.scanner = scanner;
    }

    public static String getPRINT_PADDING() {
        return PRINT_PADDING;
    }

    public static void setPRINT_PADDING(String p) {
        PRINT_PADDING = p;
    }
}
