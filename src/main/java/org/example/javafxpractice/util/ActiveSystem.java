package org.example.javafxpractice.util;

import org.example.javafxpractice.objects.Account;

public class ActiveSystem {
    private static Account activeAccount;

    public static void setActiveAccount(Account account) {
        activeAccount = account;
    }

    public static void printActiveAccount() {
        System.out.println(activeAccount.getFirstName());
    }
}
