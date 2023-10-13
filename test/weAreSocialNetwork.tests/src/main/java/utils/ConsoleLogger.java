package utils;

public class ConsoleLogger {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_BLUE = "\u001B[34m";

    public void log(String message) {
        System.out.println(message);
    }

    public void logSuccessfullMessage(String message) {
        System.out.println(ANSI_GREEN
                + message
                + ANSI_RESET);
    }

    public void logLineSeparator() {
        String lineSeparator = "========================================================================";
        System.out.println(ANSI_BLUE
                + lineSeparator
                + ANSI_RESET);
    }

}
