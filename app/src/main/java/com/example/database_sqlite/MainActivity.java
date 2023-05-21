package com.example.database_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button insert, update, del, view;
    ContactDatabaseHelper dbHelper;
    private EditText name, phone;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        dbHelper = new ContactDatabaseHelper(this);
        settingUpListeners();

        /*dbHelper.insertContact(new Contact("Ahsan", "9876543210"));
        dbHelper.insertContact(new Contact("Asad", "9876543211"));
        dbHelper.insertContact(new Contact("Hammad", "9876543212"));
        dbHelper.insertContact(new Contact("Arsal", "9876543213"));
        */

        /*dbHelper.updateContact(new Contact("ashsan","1234567890", 1));*/
/*        dbHelper.deleteContact(6);
        dbHelper.deleteContact(7);
        dbHelper.deleteContact(8);
        dbHelper.deleteContact(9);
        dbHelper.deleteContact(10);*/
        /*        dbHelper.insertContact(new Contact("Rizwan", "9876543214"));*/

    }

    private void settingUpListeners() {
        insert.setOnClickListener(view->{
            String n = name.getText().toString();
            String p = phone.getText().toString();
            dbHelper.insertContact(new Contact(n,p));
        });
/*
        update.setOnClickListener(view->{
            String n = name.getText().toString();
            String p = phone.getText().toString();
            int i = Integer.parseInt(identity.getText().toString());
            boolean check = dbHelper.updateContact(new Contact(n,p,i));
            if(check)
                Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Failed to update", Toast.LENGTH_SHORT).show();
        });
        del.setOnClickListener(view->{
            int i = Integer.parseInt(identity.getText().toString());
            boolean check = dbHelper.deleteContact(i);
            if(check)
                Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Failed to Delete", Toast.LENGTH_SHORT).show();
        });
        view.setOnClickListener(view ->{
            ArrayList<Contact> c = dbHelper.getAll();
            ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, c);
            listView.setAdapter(adapter);
        } );*/
    }

    private void initComponents() {
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        del = findViewById(R.id.delete);
        view = findViewById(R.id.view);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        listView = findViewById(R.id.listview);


    }
}