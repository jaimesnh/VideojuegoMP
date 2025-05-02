package Utils;

import java.util.Arrays;

public class MenuUtils {

    private static boolean clean = true;
    private static final int MENU_WIDTH = 60;
    private static String[] formValues;
    private static boolean configFormUniqueValues = false;
    private static boolean configLastAsZero = false;

    // Simplified strings
    private static String HINT = "Elija un número de la lista y presione ENTER";
    private static String CHOOSE_OPTION = "Elija una opción";
    private static String WARN = "Alerta";
    private static String INVALID_OPTION = "Opción no válida!";
    private static String MUST_BE_A_NUMBER = "El valor de entrada debe de ser un numero";
    private static String MUST_NOT_BE_EMPTY = "El valor de entrada no debe de estar vacío";
    private static String ASK_YES_NO = "Si=[1] / No=[0]";
    private static String MUST_BE_YES_NO = "El numero de entrada debe ser 1 (Si) o 0 (No)";
    private static String MUST_BE_POSITIVE = "El valor de entrada debe de ser un entero positivo";
    private static String FIELD_NOT_UNIQUE = "El valor de entrada debe ser único";

    public static int menu(String title, String[] options) {
        int numOptions = options.length;

        if (clean) InOutMannager.cls();

        InOutMannager.print("\n");
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));
        InOutMannager.print(String.format("%s\n", centerString(title, MENU_WIDTH)));
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));

        InOutMannager.print(String.format("%s\n", HINT));
        for (int i = 0; i < options.length; i++) {
            int index = (configLastAsZero && i == options.length - 1) ? 0 : i + 1;
            InOutMannager.print(String.format("[%d] %s\n", index, options[i]));
        }

        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));

        try {
            String prompt = configLastAsZero
                    ? String.format("%s (0-%d): ", CHOOSE_OPTION, numOptions - 1)
                    : String.format("%s (1-%d): ", CHOOSE_OPTION, numOptions);
            setClean(true);
            int option = InOutMannager.readInt(prompt);

            if (option < (configLastAsZero ? 0 : 1) || option > numOptions) {
                alert(WARN, INVALID_OPTION);
                return menu(title, options);
            }

            resetSettings();
            return option;
        } catch (NumberFormatException e) {
            alert(WARN, MUST_BE_A_NUMBER);
            return menu(title, options);
        }
    }

    public static String readString(String prompt) {
        if (clean) InOutMannager.cls();

        InOutMannager.print("\n");
        InOutMannager.print(String.format("%s\n", prompt));
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));

        String input = InOutMannager.readString(" > ");
        if (input == null || input.length() == 0) {
            alert(WARN, MUST_NOT_BE_EMPTY);
            return readString(prompt);
        } else {
            resetSettings();
            return input;
        }
    }

    public static boolean askYesNo(String prompt) {
        if (clean) InOutMannager.cls();

        InOutMannager.print("\n");
        InOutMannager.print(String.format("%s\n", prompt));
        InOutMannager.print(String.format("%s\n", ASK_YES_NO));
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));

        String input = InOutMannager.readString(" > ");
        if (input == null || input.length() == 0) {
            alert(WARN, MUST_NOT_BE_EMPTY);
            return askYesNo(prompt);
        } else if (input.equals("1")) {
            resetSettings();
            return true;
        } else if (input.equals("0")) {
            resetSettings();
            return false;
        } else {
            alert(WARN, MUST_BE_YES_NO);
            return askYesNo(prompt);
        }
    }

    public static int readInt(String prompt) {
        if (clean) InOutMannager.cls();

        InOutMannager.print("\n");
        InOutMannager.print(String.format("%s\n", prompt));
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));

        String input = InOutMannager.readString(" > ");
        try {
            int val = Integer.parseInt(input);
            if (val < 0) {
                alert(WARN, MUST_BE_POSITIVE);
                return readInt(prompt);
            }
            resetSettings();
            return val;
        } catch (NumberFormatException e) {
            alert(WARN, MUST_BE_A_NUMBER);
            return readInt(prompt);
        }
    }

    public static int readInt(String prompt, int min, int max) {
        int val = readInt(prompt);
        if (val < min || val > max) {
            alert(WARN, INVALID_OPTION);
            return readInt(prompt, min, max);
        }
        return val;
    }

    public static void alert(String title, String msg) {
        if (clean) InOutMannager.cls();

        InOutMannager.print("\n");
        InOutMannager.print(String.format("%s\n", title));
        InOutMannager.print(String.format("%s\n", msg));
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));
        InOutMannager.pause();
    }

    public static void doc(String title, String[] lines) {
        if (clean) InOutMannager.cls();

        InOutMannager.print("\n");
        InOutMannager.print(String.format("%s\n", title));
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));
        for (String line : lines) {
            InOutMannager.print(String.format("%s\n", line));
        }
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));

        InOutMannager.pause();
        resetSettings();
    }

    public static String[] form(String title, String[] labels) {
        if (clean) InOutMannager.cls();

        InOutMannager.print("\n");
        InOutMannager.print(String.format("%s\n", title));
        InOutMannager.print(String.format("%s\n", "-".repeat(MENU_WIDTH)));

        if (MenuUtils.formValues == null) MenuUtils.formValues = new String[labels.length];

        for (int i = 0; i < labels.length; i++) {
            if (MenuUtils.formValues[i] != null) {
                InOutMannager.print(String.format("%s: %s\n", labels[i], MenuUtils.formValues[i]));
                continue;
            }

            String name = InOutMannager.readString(String.format("%s: ", labels[i]));

            if (name == null || name.length() == 0) {
                alert(WARN, MUST_NOT_BE_EMPTY);
                return form(title, labels);
            } else if (configFormUniqueValues && Arrays.asList(MenuUtils.formValues).contains(name)) {
                alert(WARN, FIELD_NOT_UNIQUE);
                return form(title, labels);
            }

            MenuUtils.formValues[i] = name;
        }

        String[] values = MenuUtils.formValues.clone();
        resetSettings();
        return values;
    }

    public static void resetSettings() {
        MenuUtils.clean = true;
        MenuUtils.formValues = null;
        MenuUtils.configLastAsZero = false;
        MenuUtils.configFormUniqueValues = false;
    }

    public static String centerString(String text, int len) {
        String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
        float mid = out.length() / 2;
        float start = mid - len / 2;
        float end = start + len;
        return out.substring((int) start, (int) end);
    }

    public static String leftString(String text, int len) {
        return String.format("%-" + len + "s", text);
    }

    public static String leftString(String text, int len, int padding) {
        return String.format("%-" + len + "s", " ".repeat(padding) + text);
    }

    public static String rightString(String text, int len) {
        return String.format("%" + len + "s", text);
    }

    public static String rightString(String text, int len, int padding) {
        return String.format("%" + len + "s", text + " ".repeat(padding));
    }

    // GETTERS/SETTERS
    public static void setClean(boolean clean) {
        MenuUtils.clean = clean;
    }

    public static int getMenuWidth() {
        return MENU_WIDTH;
    }

    public static boolean isClean() {
        return clean;
    }

    public static String[] getFormValues() {
        return formValues;
    }

    public static void setFormValues(String[] formValues) {
        MenuUtils.formValues = formValues;
    }

    public static boolean isConfigFormUniqueValues() {
        return configFormUniqueValues;
    }

    public static void setConfigFormUniqueValues(boolean configFormUniqueValues) {
        MenuUtils.configFormUniqueValues = configFormUniqueValues;
    }

    public static boolean isConfigLastAsZero() {
        return configLastAsZero;
    }

    public static void setConfigLastAsZero(boolean configLastAsZero) {
        MenuUtils.configLastAsZero = configLastAsZero;
    }
}