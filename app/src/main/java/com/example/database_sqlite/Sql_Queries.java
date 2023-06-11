package com.example.database_sqlite;

public class Sql_Queries {
    public static final String SQL_CREATE_CONTACTS = "CREATE TABLE "+ DBContract.DBEntities.CONTACTS+
            " ("+ DBContract._ID+" INTEGER PRIMARY KEY ,"+ DBContract.DBEntities.NAME+" TEXT,"+
            DBContract.DBEntities.PHONE+" TEXT)";

    public static final String SQL_DROP_CONTACTS =
            "DROP TABLE IF EXISTS "+ DBContract.DBEntities.CONTACTS;

    public static final String SELECT_ALL="SELECT * FROM "+DBContract.DBEntities.CONTACTS;

}
