package com.example.contactsapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchContacts();
    }

    private void fetchContacts(){

        ArrayList<String> contacts = new ArrayList<>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;


        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri,projection,selection,selectionArgs,sortOrder);

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(projection[0]));
            String number = cursor.getString(cursor.getColumnIndex(projection[1]));

            Log.i("info","Name: " + name + " Number: " + number );
            contacts.add(name+"\n" + number);
        }

        ((ListView)findViewById(R.id.listcontacts))
                .setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts));

    }
}
