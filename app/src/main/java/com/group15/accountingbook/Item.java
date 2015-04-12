package com.group15.accountingbook;

public class Item {
    public String description;
    public double amount;
    public String date;

    public Item() {
        this.description = "";
        this.amount = 0;
        this.date = "";
    }

    public Item(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String toString() {
        return this.description + "   " + ((this.amount > 0) ? "+" : "") + this.amount +
                "   " + this.date;
    }
}
