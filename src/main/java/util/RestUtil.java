package util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class RestUtil {
    public static Long getLongPathVariable(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String[] arrayRequestURI = requestURI.split("/");
        String value = arrayRequestURI[arrayRequestURI.length - 1];
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String getStringPathVariable(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String[] arrayRequestURI = requestURI.split("/");
        if (arrayRequestURI.length == 1) {
            return null;
        } else {
            return arrayRequestURI[arrayRequestURI.length - 1];
        }
    }

    public static String readJsonFromRequestBody(HttpServletRequest req) throws IOException {
        StringBuilder builder = new StringBuilder();

        try(BufferedReader reader = req.getReader()) {
            int intValueOfChar;
            while ((intValueOfChar = reader.read()) != -1) {
                builder.append((char) intValueOfChar);
            }
        }

        return builder.toString();
    }
 }
