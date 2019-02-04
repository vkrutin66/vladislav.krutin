package com.test.util.reporter;

import com.test.util.Formatter;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reporter {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("H:mm:ss:SSS");
    private static final Logger LOGGER = Logger.getLogger(Reporter.class.getName());
    private static boolean outEnabled = true;

    public static void log(String s) {
        String message = "[" + FORMAT.format(System.currentTimeMillis()) + "]: " + Formatter.escapeCharacters(s) + "<br/>\n";
        org.testng.Reporter.log(message);
        printStdOut(message);
    }

    public static void log(HttpRequestBase request) {
        String message = "[" + FORMAT.format(System.currentTimeMillis()) + "]: Request \n" + Formatter.escapeCharacters(request.toString()) + "<br/>\n";
        for (Header header : request.getAllHeaders())
            message += header.toString() + "<br/>\n";
        org.testng.Reporter.log(message);
        printStdOut(message);
    }

    public static void log(HttpResponse response) {
        String message = "[" + FORMAT.format(System.currentTimeMillis()) + "]: " + response.getStatusLine() + "<br/>\n";
        org.testng.Reporter.log(message);
        StringBuilder log = new StringBuilder(message + "<br/>\n");
        for (Header header : response.getAllHeaders()) {
            org.testng.Reporter.log(header.toString());
            log.append(header.toString()).append("<br/>\n");
        }
        org.testng.Reporter.log(log.toString());
        printStdOut(log);
    }

    public static void log(Throwable throwable) {
        String message = "[" + FORMAT.format(System.currentTimeMillis()) + "]: " + Formatter.escapeCharacters(throwable.getMessage()) + "<br/>\n";
        org.testng.Reporter.log(message);
        printStdOut(message);
        for (StackTraceElement stack : throwable.getStackTrace()) {
            org.testng.Reporter.log(stack.toString() + "<br/>\n");
        }
    }

    private static void printStdOut(Object message) {
        if (outEnabled) {
            LOGGER.log(Level.INFO, message.toString());
        }
    }

    public static void setOutEnabled(boolean enabled) {
        outEnabled = enabled;
    }

    public static void setEscapeHtml(boolean escape) {
        org.testng.Reporter.setEscapeHtml(escape);
    }

}
