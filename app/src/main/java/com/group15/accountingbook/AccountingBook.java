package com.group15.accountingbook;

import java.util.ArrayList;
import java.util.List;

public class AccountingBook {
    public static List<Item> ITEMS = new ArrayList<>();

    static {
        addItem(new Item("doge", -99.99, "2015/4/1"));
        addItem(new Item("wow", 1000.0, "2015/3/31"));
    }

    public static void addItem(Item item) {
        ITEMS.add(item);
    }

    public static double getBalance() {
        double result = 0;
        for (Item item : ITEMS) {
            result += item.amount;
        }
        return result;
    }
}
