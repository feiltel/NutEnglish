package com.nutstudio.nutenglish.Tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fei on 2015/10/24.
 */
public class WordDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "worddb";
    public static final String ENWORD = "en";
    public static final String ID = "_id";
    public static final String TIME = "time";
    public static final String CNWORD = "cn";
    public static final String EXPLAIN = "explain";
    public WordDB(Context context) {
        super(context, "word", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ENWORD + " TEXT,"
                + CNWORD + " TEXT,"
                + EXPLAIN + " TEXT,"
                + TIME + " TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
