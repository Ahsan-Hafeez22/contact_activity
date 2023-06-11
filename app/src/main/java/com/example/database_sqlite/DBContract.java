package com.example.database_sqlite;


import android.provider.BaseColumns;

public final class DBContract implements BaseColumns{
    private DBContract(){}
    public static class DBEntities implements BaseColumns{
        public static final String CONTACTS = "contacts";
        public static final String NAME = "name";
        public static final String PHONE = "phone_number";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "myContacts.db";

    }

}