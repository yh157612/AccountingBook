package com.group15.accountingbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static MySQLiteHelper mInstance = null;

    public static final String TABLE_RECORDS = "records";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DATE = "date";

    private static final String DATABASE_NAME = "records.db";
    private static final int DATABASE_VERSION = 2;

    // database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RECORDS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DESC + " text not null, "
            + COLUMN_AMOUNT + " real, "
            + COLUMN_DATE + " text not null);";

    public static MySQLiteHelper getInstance(Context context) {
        if (mInstance == null)
            mInstance = new MySQLiteHelper(context);
        return mInstance;
    }

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }

}
