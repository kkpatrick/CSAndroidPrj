package com.example.abc.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by abc on 2/24/15.
 */
public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "My_Database_1";
    private static final String TABLE_NAME = "My_Table_1";
    private static final int VERSION = 1;
    private static Database database;

    public static final String KEY_ID = "_id";
    public static final String KEY_VALUE = "value";
    private static final String KEY_NAME = "name";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_VALUE + " INTEGER);";
    private static final String TAG = "Database";

    public static Database getDatabase() {
        if(null == database) {
            database = new Database(MyApplication.getInstance());
        }
        return database;
    }

    public static void addNumber(Number number) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_VALUE, number.getNumber());
        cv.put(KEY_ID, number.getId());
        //cv.put(KEY_NAME, "");
        getDatabase().getWritableDatabase().insert(TABLE_NAME, null, cv);
    }

    public static void addNumber() {
        ContentValues cv = new ContentValues();
        cv.put(KEY_VALUE, 0);
        //cv.put(KEY_NAME, "");
        getDatabase().getWritableDatabase().insert(TABLE_NAME, null, cv);
    }

    public static void deleteNumber(int Id) {
        getDatabase().getWritableDatabase().delete(TABLE_NAME, KEY_ID+"="+Id, null);
    }

    public static void updateNumber(Number number) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_VALUE, number.getNumber());
        cv.put(KEY_ID, number.getId());
        //cv.put(KEY_NAME, "");
        getDatabase().getWritableDatabase().replace(TABLE_NAME, null, cv);
    }

    public static Number getNumberWithId(int id) {

        Cursor cursor=getDatabase().getReadableDatabase().query(TABLE_NAME,null,KEY_ID+"="+id,null,null,null,null);
        if(cursor.getCount() == 0){
            return null;
        }
        else if(cursor.getCount() != 1) {
            Log.e(TAG, "Database Error on Search for "+ id);
            throw new Error("Database Error on Search for "+ id);
        }
        cursor.moveToFirst();
        int value = cursor.getInt(cursor.getColumnIndex(KEY_VALUE));
        cursor.close();
        return new Number(id, value);
    }

    private Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to " +
                newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static Cursor getAllData() {
        Cursor cursor=getDatabase().getReadableDatabase().query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }

}
