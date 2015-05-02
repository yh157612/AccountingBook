package com.group15.accountingbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AccountingBook {

    // prevent creating more than one instance
    private static AccountingBook mInstance = null;

    // database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_DESC,
            MySQLiteHelper.COLUMN_AMOUNT,
            MySQLiteHelper.COLUMN_DATE
    };

    public static AccountingBook getInstance(Context context) {
        if (mInstance == null)
            mInstance = new AccountingBook(context);
        return mInstance;
    }

    public AccountingBook(Context context) {
        dbHelper = MySQLiteHelper.getInstance(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Record addRecord(String desc, double amount, String date) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DESC, desc);
        values.put(MySQLiteHelper.COLUMN_AMOUNT, amount);
        values.put(MySQLiteHelper.COLUMN_DATE, date);
        long insertId = database.insert(MySQLiteHelper.TABLE_RECORDS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_RECORDS, allColumns,
                MySQLiteHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);
        cursor.moveToFirst();
        Record newRecord = cursorToRecord(cursor);
        cursor.close();
        return newRecord;
    }

    public void deleteRecord(Record record) {
        database.delete(MySQLiteHelper.TABLE_RECORDS,
                MySQLiteHelper.COLUMN_ID + "=" + record.id, null);
    }

    public List<Record> getAllRecords() {
        List<Record> records = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_RECORDS,
                allColumns, null, null, null, null,
                MySQLiteHelper.COLUMN_ID + " DESC");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            records.add(cursorToRecord(cursor));
        }
        cursor.close();
        return records;
    }

    private Record cursorToRecord(Cursor cursor) {
        Record record = new Record();
        record.id = cursor.getLong(0);
        record.description = cursor.getString(1);
        record.amount = cursor.getDouble(2);
        record.date = cursor.getString(3);
        return record;
    }

    public double getBalance() {
        double result = 0;
        List<Record> records = getAllRecords();
        for (Record record : records) {
            result += record.amount;
        }
        return result;
    }
}
