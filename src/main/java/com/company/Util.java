package com.company;

public class Util {

    public static final String WHITE_SPACE = " ";

    public static boolean endsWithPunc(String s) {
        return s.endsWith(".") || s.endsWith(",") || s.endsWith(":") || s.endsWith(";") || s.endsWith("!")
                || s.endsWith("&") || s.endsWith("%") || s.endsWith("*") || s.endsWith("?") || s.endsWith("¡±") ||
                s.endsWith("\"");
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("^(\\d+|\\d{1,3}(,\\d{3})*)(\\.\\d+)?$");
    }

    /**
     * Parse word
     * Remove all chars that are not letter digit or -
     * Return null if word is numeric
     * @param s
     * @return parsed word
     */
    public static String parseWord(String s) {
        if (s == null || s.length() == 0) return null;
        String str = s.trim().replaceAll("\n","").replaceAll("[^\\w\\s\\-]","");
        if (str == null || str.length() == 0) return null;
        if (isNumeric(str)) return null;
        if (!Character.isLetterOrDigit(str.charAt(0))) return null;
        return str;
    }
}
