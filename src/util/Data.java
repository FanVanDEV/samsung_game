package util;

import java.util.Scanner;

public class Data {
    private static final Scanner in = new Scanner(System.in);

    public static String getString() {
        return in.next();
    }

    public static int getInt() {
        return in.nextInt();
    }

    public static void send(String message) {
        System.out.println(message);
    }

    public static void sendError(String message) {
        System.out.println("Ошибка: " + message);
    }
}
