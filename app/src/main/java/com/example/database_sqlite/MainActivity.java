package com.example.database_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText name,phone;
    private Button save,showall,show,update,delete;
    private Contacts_entity contacts_entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        settingUpListeners();
    }

    private void settingUpListeners() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().isEmpty() && !phone.getText().toString().isEmpty()) {
                    DBHandler dbHandler = new DBHandler(MainActivity.this);
                    contacts_entity.setName(name.getText().toString());
                    contacts_entity.setPhone(phone.getText().toString());
                    boolean b = dbHandler.insert(contacts_entity);
                    if(b){
                        Toast.makeText(MainActivity.this, "row insert Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "row not insert", Toast.LENGTH_SHORT).show();
                    }
                    dbHandler.close();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Fill the required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(MainActivity.this);
                if(dbHandler.getAll()!=null) {
                    List<Contacts_entity> list = dbHandler.getAll();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    StringBuffer buffer = new StringBuffer();
                    for (int i = 0; i < list.size(); i++) {
                        buffer.append("\nRow " + i);
                        buffer.append("\n\n");
                        buffer.append(list.get(i).getName());
                        buffer.append("\t" + list.get(i).getPhone());
                        buffer.append("\n________________________");
                    }
                    builder.setMessage(buffer);
                    builder.show();
                    dbHandler.close();
                }else
                    Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().isEmpty()) {
                    DBHandler dbHandler = new DBHandler(MainActivity.this);
                    if (dbHandler.search(name.getText().toString()) != null) {
                        List<Contacts_entity> list = dbHandler.search(name.getText().toString());
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        StringBuffer buffer = new StringBuffer();
                        for (int i = 0; i < list.size(); i++) {
                            buffer.append("\nRow " + i);
                            buffer.append("\n\n");
                            buffer.append(list.get(i).getName());
                            buffer.append("\t" + list.get(i).getPhone());
                            buffer.append("\n________________________");
                        }
                        builder.setMessage(buffer);
                        builder.show();
                        dbHandler.close();
                    } else {
                        Toast.makeText(MainActivity.this, "Person not Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "name must required!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().isEmpty() && !phone.getText().toString().isEmpty()) {
                    DBHandler dbHandler = new DBHandler(MainActivity.this);
                    contacts_entity.setName(name.getText().toString());
                    contacts_entity.setPhone(phone.getText().toString());
                    int d = dbHandler.update(contacts_entity,name.getText().toString());
                    if(d>0){
                        Toast.makeText(MainActivity.this, d+" rows update Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "row not update yet", Toast.LENGTH_SHORT).show();
                    }
                    dbHandler.close();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Fill the required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().isEmpty()) {
                    DBHandler dbHandler = new DBHandler(MainActivity.this);
                    int b = dbHandler.delete(name.getText().toString());
                    if(b>0){
                        Toast.makeText(MainActivity.this, b+" rows deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "not delete yet", Toast.LENGTH_SHORT).show();
                    }
                    dbHandler.close();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Fill the required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initComponents() {
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        save = findViewById(R.id.save);
        showall = findViewById(R.id.showall);
        show = findViewById(R.id.show);
        update =  findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        contacts_entity = new Contacts_entity();
    }
}