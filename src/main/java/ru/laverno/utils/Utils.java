package ru.laverno.utils;

public class Utils {

    private Utils() {}

    public static String convertPhoneIntoFormat(String phone) {
        String result = phone;
        if (result.startsWith("+")) {
            result = result.replace("+", "");
        }
        if (result.startsWith("8")) {
            result = result.substring(1);
            result = "7".concat(result);
        }
        if (result.contains("-")) {
            result = result.replace("-", "");
        }
        if (result.contains(" ")) {
            result = result.replace(" ", "");
        }
        if (result.contains("(") || result.contains(")")) {
            result = result.replace("(", "");
            result = result.replace(")", "");
        }

        return result;
    }
}
