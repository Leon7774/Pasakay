package org.example.javafxpractice;

public class ActiveSystem {
    private static String accountName;

    public static void setAccountName(String name) {
        accountName = name;
    }

    public static String getAccountName() {
        return accountName;
    }
}
