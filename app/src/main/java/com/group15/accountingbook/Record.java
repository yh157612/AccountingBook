package com.group15.accountingbook;

public class Record {

    public long id;
    public String description;
    public double amount;
    public String date;

    public Record() {
        this.id = -1;
        this.description = "";
        this.amount = 0;
        this.date = "";
    }

    public Record(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

}
