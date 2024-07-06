package org.homework;

import java.io.IOException;
import java.io.PrintWriter;

public class Saver {
    public static void save(String path, String text) {
        try(var pw = new PrintWriter(path)) {
            pw.write(text);
        } catch (IOException e) {
            System.err.println("Save error: " + e.getMessage());
        }
    }
}
