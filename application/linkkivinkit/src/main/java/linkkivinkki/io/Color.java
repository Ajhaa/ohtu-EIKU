package linkkivinkki.io;

public enum Color {

    Black ("\u001b[30m"),
    Red ("\u001b[31m"),
    Green ("\u001b[32m"),
    Yellow ("\u001b[33m"),
    Blue ("\u001b[34m"),
    Magenta("\u001b[35m"),
    Cyan ("\u001b[36m"),
    White ("\u001b[37m"),

    Reset("\u001b[0m");

    private final String value;


    Color(String s) {
        value = s;
    }

    public String toString() {
        return value;
    }

    public static String coloredString(String text, Color color) {
        return color + text + Reset;
    }

    public static String cyanText(String text) {
        return coloredString(text, Cyan);
    }

    public static String redText(String text) {
        return coloredString(text, Red);
    }

    public static String greenText(String text) {
        return coloredString(text, Green);
    }
}