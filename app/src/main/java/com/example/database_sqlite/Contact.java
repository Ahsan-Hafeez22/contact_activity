package com.example.database_sqlite;

public class Contact {
    String name;
    String phone_no;
    int id;

    public Contact() {
    }

    public Contact(String name, String phone_no) {
        this.name = name;
        this.phone_no = phone_no;
    }

    public Contact(String name, String phone_no, int id) {
        this.name = name;
        this.phone_no = phone_no;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
