package com.example.database_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context,DBContract.DBEntities.DATABASE_NAME,null,DBContract.DBEntities.DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Sql_Queries.SQL_CREATE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Sql_Queries.SQL_DROP_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<Contacts_entity> getAll(){
        List<Contacts_entity> contacts_list = null;
        db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(Sql_Queries.SELECT_ALL, null);
        if(cursor.moveToFirst()) {
            contacts_list = new ArrayList<>();
            do {
                Contacts_entity entity = new Contacts_entity();
                entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBEntities.NAME)));
                entity.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBEntities.PHONE)));
                contacts_list.add(entity);
            } while (cursor.moveToNext());
        }
        db.close();
        return contacts_list;
    }
    public List<Contacts_entity> search(String name){
        List<Contacts_entity> list = null;
        db = this.getReadableDatabase();
        String []projection = {DBContract.DBEntities.NAME,
                DBContract.DBEntities.PHONE};
        String selection = DBContract.DBEntities.NAME+" = ?";
        String []selectionArgs = {name};
        Cursor cursor = db.query(DBContract.DBEntities.CONTACTS, projection, selection, selectionArgs, null, null, null);
        if(cursor.moveToFirst()){
            list = new ArrayList<>();
            do{
                Contacts_entity contacts_entity = new Contacts_entity();
                contacts_entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBEntities.NAME)));
                contacts_entity.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBEntities.PHONE)));
                list.add(contacts_entity);
            }while (cursor.moveToNext());
        }
        db.close();
        return list;
    }
    public int update(Contacts_entity contacts_entity,String name){
        ContentValues contentValues = new ContentValues();
        String selection = DBContract.DBEntities.NAME+" = ?";
        String [] selecionArgs = {name};
        contentValues.put(DBContract.DBEntities.NAME,contacts_entity.getName());
        contentValues.put(DBContract.DBEntities.PHONE, contacts_entity.getPhone());
        int rows = db.update(DBContract.DBEntities.CONTACTS,contentValues,selection,selecionArgs);
        db.close();
        return rows;
    }
    public int delete(String name){
        String selection = DBContract.DBEntities.NAME+" = ?";
        String [] selecionArgs = {name};
        int rows = db.delete(DBContract.DBEntities.CONTACTS,selection,selecionArgs);
        return rows;
    }

    public boolean insert(Contacts_entity contacts_entity){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.DBEntities.NAME,contacts_entity.getName());
        contentValues.put(DBContract.DBEntities.PHONE,contacts_entity.getPhone());
        long pk = db.insert(DBContract.DBEntities.CONTACTS,null,contentValues);
        db.close();
        if(pk>0){
            return true;
        }
        else {
            return false;
        }
    }
}
