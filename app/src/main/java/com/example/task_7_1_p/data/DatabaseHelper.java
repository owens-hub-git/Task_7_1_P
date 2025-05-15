package com.example.task_7_1_p.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.task_7_1_p.model.Item;
import com.example.task_7_1_p.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_ITEM_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.POST_TYPE + " TEXT, "
                + Util.NAME + " TEXT, "
                + Util.PHONE + " TEXT, "
                + Util.DESCRIPTION + " TEXT, "
                + Util.DATE + " TEXT, "
                + Util.LOCATION + " TEXT, "
                + Util.ISSUE + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS ";
        sqLiteDatabase.execSQL(DROP_ITEM_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(sqLiteDatabase);

    }

    public long insertItem(Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.POST_TYPE, item.getPostType());
        contentValues.put(Util.NAME, item.getName());
        contentValues.put(Util.PHONE, item.getPhone());
        contentValues.put(Util.DESCRIPTION, item.getDescription());
        contentValues.put(Util.DATE, item.getDate());
        contentValues.put(Util.LOCATION, item.getLocation());
        contentValues.put(Util.ISSUE, item.getIssue());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    public boolean fetchItem(String postType, String name, String phone, String description, String date, String location, String issue)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.ITEM_ID}, Util.POST_TYPE + "=? and " + Util.NAME + "=?" + Util.PHONE + "=?" + Util.DESCRIPTION + "=?" + Util.DATE + "=?" + Util.LOCATION + "=?" + Util.ISSUE + "=?",
                new String[] {postType, name, phone, description, date, location, issue}, null, null, null);
        int numberOfRows = cursor.getCount();
        db.close();

        if(numberOfRows > 0)
            return true;
        else
            return false;
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setItem_id(cursor.getInt(0));
                item.setPostType(cursor.getString(1));
                item.setName(cursor.getString(2));
                item.setPhone(cursor.getString(3));
                item.setDescription(cursor.getString(4));
                item.setDate(cursor.getString(5));
                item.setLocation(cursor.getString(6));
                item.setIssue(cursor.getString(7));
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return items;
    }

    public void deleteItemById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.ITEM_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }


}
