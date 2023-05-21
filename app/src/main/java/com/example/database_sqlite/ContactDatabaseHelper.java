package com.example.database_sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ArrayRes;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class ContactDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactDirectory.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Contact";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE_NUMBER, contact.getPhone_no());

        long newRow = db.insert(TABLE_NAME, null, values);
        if (newRow == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Contact> getAll(){
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery + TABLE_NAME, null);
        ArrayList<Contact>  arrContact = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do{
                Contact model = new Contact();
                model.setId(cursor.getInt(0));
                model.setName(cursor.getString(1));
                model.setPhone_no(cursor.getString(2));

                arrContact.add(model);
            }while (cursor.moveToNext());
        }
    return arrContact;
    }


    public boolean updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE_NUMBER, contact.getPhone_no());
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(contact.getId())};
        long count = db.update(TABLE_NAME, values, selection, selectionArgs);
        if (count == -1)
            return false;
        else
            return true;

    }
    public boolean deleteContact(int contactId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(contactId)};
        int count = db.delete(TABLE_NAME, selection, selectionArgs);
        if (count == -1)
            return false;
        else
            return true;
    }

}
