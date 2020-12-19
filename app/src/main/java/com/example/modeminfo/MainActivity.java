package com.example.modeminfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<String> nameArray;
    ArrayList<Integer> idArray;
    ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        nameArray= new ArrayList<String>();
        idArray = new ArrayList<>();

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,nameArray);
        listView.setAdapter(arrayAdapter);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Create.class);
                intent.putExtra("modemID",idArray.get(position));
                intent.putExtra("info","old");
                startActivity(intent);

            }
        });

        getData();

    }

    public void getData(){

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("ModemInfo",MODE_PRIVATE,null);

            Cursor cursor = database.rawQuery("SELECT * FROM modems",null);
            int idIndex = cursor.getColumnIndex("ID");
            int nameIndex = cursor.getColumnIndex("Name");

            while (cursor.moveToNext()){
                nameArray.add(cursor.getString(nameIndex));
                idArray.add(cursor.getInt(idIndex));

            }
            arrayAdapter.notifyDataSetChanged();
            cursor.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflater
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_modem,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if ( item.getItemId() == R.id.add_modem_item){
            Intent intent = new Intent(MainActivity.this,Create.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}