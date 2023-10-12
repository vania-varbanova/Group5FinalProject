package utils;

public class ConsoleLogger {
    public static final String ANSI_RESET = "\u001B[0m";

    // Declaring the color
    // Custom declaration
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public static void log(String message) {
        System.out.println(message);
    }

    public static void logSuccessfullMessage(String message) {
        System.out.println(ANSI_GREEN
                + message
                + ANSI_RESET);
    }

    public static void logLineSeparator() {
        String lineSeparator = "========================================================================";
        System.out.println(ANSI_BLUE
                + lineSeparator
                + ANSI_RESET);
    }

}
