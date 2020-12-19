package com.example.modeminfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Create extends AppCompatActivity {

    EditText editTextName,editTextCompany,editTextModem,editTextModemIp,editTextModemAdminName,editTextModemAdminPassword,editTextDslUsername,editTextDslPassword,editTextWifiName,editTextWifiPassword,
            editTextWebPortalUsername,editTextWebPortalPassword;
    Button buttonSave,buttonEditSave;
    SQLiteDatabase modemDatabase;
    TextView textViewNameContent,textViewCompanyContent,textViewModemContent,textViewModemIpContent,textViewModemAdminNameContent,textViewModemAdminPasswordContent,
            textViewDslUsernameContent,textViewDslPasswordContent,textViewWifiNameContent,textViewWifiPasswordContent,textViewWebPortalUsernameContent,textViewWebPortalPasswordContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


                editTextName = findViewById(R.id.editTextName);
                editTextCompany = findViewById(R.id.editTextCompany);
                editTextModem = findViewById(R.id.editTextModem);
                editTextModemIp = findViewById(R.id.editTextModemIp);
                editTextModemAdminName = findViewById(R.id.editTextModemAdminName);
                editTextModemAdminPassword = findViewById(R.id.editTextModemAdminPassword);
                editTextDslUsername = findViewById(R.id.editTextDslUsername);
                editTextDslPassword = findViewById(R.id.editTextDslPassword);
                editTextWifiName = findViewById(R.id.editTextWifiName);
                editTextWifiPassword = findViewById(R.id.editTextWifiPassword);
                editTextWebPortalUsername = findViewById(R.id.editTextWebPortalUsername);
                editTextWebPortalPassword = findViewById(R.id.editTextWebPortalPassword);

                 textViewNameContent = findViewById(R.id.textViewNameContent);
                 textViewCompanyContent = findViewById(R.id.textViewCompanyContent);
                 textViewModemContent = findViewById(R.id.textViewModemContent);
                 textViewModemIpContent = findViewById(R.id.textViewModemIpContent);
                 textViewModemAdminNameContent = findViewById(R.id.textViewModemAdminNameContent);
                 textViewModemAdminPasswordContent = findViewById(R.id.textViewModemAdminPasswordContent);
                 textViewDslUsernameContent = findViewById(R.id.textViewDslUsernameContent);
                 textViewDslPasswordContent = findViewById(R.id.textViewDslPasswordContent);
                 textViewWifiNameContent = findViewById(R.id.textViewWifiNameContent);
                 textViewWifiPasswordContent = findViewById(R.id.textViewWifiPasswordContent);
                 textViewWebPortalUsernameContent = findViewById(R.id.textViewWebPortalUsernameContent);
                 textViewWebPortalPasswordContent = findViewById(R.id.textViewWebPortalPasswordContent);




                buttonSave = findViewById(R.id.buttonsave);
                buttonEditSave = findViewById(R.id.buttoneditsave);

                modemDatabase = this.openOrCreateDatabase("ModemInfo",MODE_PRIVATE,null);

                Intent intent = getIntent();
                String info = intent.getStringExtra("info");

                if (info.matches("new")){
                    viewModemsEdit();

                    editTextName.setText("");
                    editTextCompany.setText("");
                    editTextModem.setText("");
                    editTextModemIp.setText("");
                    editTextModemAdminName.setText("");
                    editTextModemAdminPassword.setText("");

                    editTextDslUsername.setText("");
                    editTextDslPassword.setText("");
                    editTextWifiName.setText("");
                    editTextWifiPassword.setText("");
                    editTextWebPortalUsername.setText("");
                    editTextWebPortalPassword.setText("");
                    buttonSave.setVisibility(View.VISIBLE);
                    buttonEditSave.setVisibility(View.INVISIBLE);
                }else if (info.matches("old")){
                    int modemID = intent.getIntExtra("modemID",1);
                    buttonSave.setVisibility(View.INVISIBLE);
                    buttonEditSave.setVisibility(View.INVISIBLE);


                    try {
                        Cursor cursor = modemDatabase.rawQuery("SELECT * FROM modems WHERE ID= ?",new String[]{String.valueOf(modemID)});

                        int nameIndex = cursor.getColumnIndex("Name");
                        int CompanyIndex = cursor.getColumnIndex("Company");
                        int modemIndex = cursor.getColumnIndex("Modem");
                        int modemIpIndex = cursor.getColumnIndex("ModemIp");
                        int modemAdminNameIndex = cursor.getColumnIndex("ModemAdminName");
                        int modemAdminPasswordIndex = cursor.getColumnIndex("ModemAdminPassword");

                        int DslUsernameIndex = cursor.getColumnIndex("DslUserName");
                        int DslPasswordIndex = cursor.getColumnIndex("DslPassword");
                        int WifiNameIndex = cursor.getColumnIndex("WifiName");
                        int WifiPasswordIndex = cursor.getColumnIndex("WifiPassword");
                        int WebPortalUsernameIndex = cursor.getColumnIndex("WebPortalName");
                        int WebPortalPasswordIndex = cursor.getColumnIndex("WebPortalPassword");

                        while(cursor.moveToNext()){


                            editTextName.setText(cursor.getString(nameIndex));
                            editTextCompany.setText(cursor.getString(CompanyIndex));
                            editTextModem.setText(cursor.getString(modemIndex));
                            editTextModemIp.setText(cursor.getString(modemIpIndex));
                            editTextModemAdminName.setText(cursor.getString(modemAdminNameIndex));
                            editTextModemAdminPassword.setText(cursor.getString(modemAdminPasswordIndex));
                            editTextDslUsername.setText(cursor.getString(DslUsernameIndex));
                            editTextDslPassword.setText(cursor.getString(DslPasswordIndex));
                            editTextWifiName.setText(cursor.getString(WifiNameIndex));
                            editTextWifiPassword.setText(cursor.getString(WifiPasswordIndex));
                            editTextWebPortalUsername.setText(cursor.getString(WebPortalUsernameIndex));
                            editTextWebPortalPassword.setText(cursor.getString(WebPortalPasswordIndex));

                            textViewNameContent.setText(cursor.getString(nameIndex));
                            textViewCompanyContent.setText(cursor.getString(CompanyIndex));
                            textViewModemContent.setText(cursor.getString(modemIndex));
                            textViewModemIpContent.setText(cursor.getString(modemIpIndex));
                            textViewModemAdminNameContent.setText(cursor.getString(modemAdminNameIndex));
                            textViewModemAdminPasswordContent.setText(cursor.getString(modemAdminPasswordIndex));
                            textViewDslUsernameContent.setText(cursor.getString(DslUsernameIndex));
                            textViewDslPasswordContent.setText(cursor.getString(DslPasswordIndex));
                            textViewWifiNameContent.setText(cursor.getString(WifiNameIndex));
                            textViewWifiPasswordContent.setText(cursor.getString(WifiPasswordIndex));
                            textViewWebPortalUsernameContent.setText(cursor.getString(WebPortalUsernameIndex));
                            textViewWebPortalPasswordContent.setText(cursor.getString(WebPortalPasswordIndex));

                        }

                        cursor.close();

                    }catch (Exception e){

                    }

                    viewModemsNonEdit();


                }

    }


    public void viewModemsNonEdit(){

        editTextName.setVisibility(View.INVISIBLE);
        editTextCompany.setVisibility(View.INVISIBLE);
        editTextModem.setVisibility(View.INVISIBLE);
        editTextModemIp.setVisibility(View.INVISIBLE);
        editTextModemAdminName.setVisibility(View.INVISIBLE);
        editTextModemAdminPassword.setVisibility(View.INVISIBLE);
        editTextDslUsername.setVisibility(View.INVISIBLE);
        editTextDslPassword.setVisibility(View.INVISIBLE);
        editTextWifiName.setVisibility(View.INVISIBLE);
        editTextWifiPassword.setVisibility(View.INVISIBLE);
        editTextWebPortalUsername.setVisibility(View.INVISIBLE);
        editTextWebPortalPassword.setVisibility(View.INVISIBLE);

        textViewNameContent.setVisibility(View.VISIBLE);
        textViewCompanyContent.setVisibility(View.VISIBLE);
        textViewModemContent.setVisibility(View.VISIBLE);
        textViewModemIpContent.setVisibility(View.VISIBLE);
        textViewModemAdminNameContent.setVisibility(View.VISIBLE);
        textViewModemAdminPasswordContent.setVisibility(View.VISIBLE);
        textViewDslUsernameContent.setVisibility(View.VISIBLE);
        textViewDslPasswordContent.setVisibility(View.VISIBLE);
        textViewWifiNameContent.setVisibility(View.VISIBLE);
        textViewWifiPasswordContent.setVisibility(View.VISIBLE);
        textViewWebPortalUsernameContent.setVisibility(View.VISIBLE);
        textViewWebPortalPasswordContent.setVisibility(View.VISIBLE);

    }

    public void viewModemsEdit(){

        editTextName.setVisibility(View.VISIBLE);
        editTextCompany.setVisibility(View.VISIBLE);
        editTextModem.setVisibility(View.VISIBLE);
        editTextModemIp.setVisibility(View.VISIBLE);
        editTextModemAdminName.setVisibility(View.VISIBLE);
        editTextModemAdminPassword.setVisibility(View.VISIBLE);
        editTextDslUsername.setVisibility(View.VISIBLE);
        editTextDslPassword.setVisibility(View.VISIBLE);
        editTextWifiName.setVisibility(View.VISIBLE);
        editTextWifiPassword.setVisibility(View.VISIBLE);
        editTextWebPortalUsername.setVisibility(View.VISIBLE);
        editTextWebPortalPassword.setVisibility(View.VISIBLE);

        textViewNameContent.setVisibility(View.INVISIBLE);
        textViewCompanyContent.setVisibility(View.INVISIBLE);
        textViewModemContent.setVisibility(View.INVISIBLE);
        textViewModemIpContent.setVisibility(View.INVISIBLE);
        textViewModemAdminNameContent.setVisibility(View.INVISIBLE);
        textViewModemAdminPasswordContent.setVisibility(View.INVISIBLE);
        textViewDslUsernameContent.setVisibility(View.INVISIBLE);
        textViewDslPasswordContent.setVisibility(View.INVISIBLE);
        textViewWifiNameContent.setVisibility(View.INVISIBLE);
        textViewWifiPasswordContent.setVisibility(View.INVISIBLE);
        textViewWebPortalUsernameContent.setVisibility(View.INVISIBLE);
        textViewWebPortalPasswordContent.setVisibility(View.INVISIBLE);



    }

    public  void save(View view){
        String name = editTextName.getText().toString();
        String company = editTextCompany.getText().toString();
        String modem = editTextModem.getText().toString();
        String modemIp = editTextModemIp.getText().toString();
        String modemAdminName = editTextModemAdminName.getText().toString();
        String modemAdminPassword = editTextModemAdminPassword.getText().toString();
        String dslUsername = editTextDslUsername.getText().toString();
        String dslPassword = editTextDslPassword.getText().toString();
        String wifiName = editTextWifiName.getText().toString();
        String WifiPassword = editTextWifiPassword.getText().toString();
        String WebPortalName = editTextWebPortalUsername.getText().toString();
        String WebPortalPassword = editTextWebPortalPassword.getText().toString();

        try {
            modemDatabase = this.openOrCreateDatabase("ModemInfo",MODE_PRIVATE,null);
           modemDatabase.execSQL("CREATE TABLE IF NOT EXISTS modems (ID INTEGER PRIMARY KEY,Name VARCHAR,Company VARCHAR,Modem VARCHAR,ModemIp VARCHAR," +
                   "ModemAdminName VARCHAR,ModemAdminPassword VARCHAR,DslUserName VARCHAR,DslPassword VARCHAR,WifiName VARCHAR,WifiPassword VARCHAR,WebPortalName VARCHAR,WebPortalPassword VARCHAR)");



            String sqlString="INSERT INTO modems (Name,Company,Modem,ModemIp,ModemAdminName,ModemAdminPassword,DslUserName,DslPassword,WifiName,WifiPassword,WebPortalName,WebPortalPassword)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            SQLiteStatement sqLiteStatement = modemDatabase.compileStatement(sqlString);
            sqLiteStatement.bindString(1,name);
            sqLiteStatement.bindString(2,company);
            sqLiteStatement.bindString(3,modem);
            sqLiteStatement.bindString(4,modemIp);
            sqLiteStatement.bindString(5,modemAdminName);
            sqLiteStatement.bindString(6,modemAdminPassword);
            sqLiteStatement.bindString(7,dslUsername);
            sqLiteStatement.bindString(8,dslPassword);
            sqLiteStatement.bindString(9,wifiName);
            sqLiteStatement.bindString(10,WifiPassword);
            sqLiteStatement.bindString(11,WebPortalName);
            sqLiteStatement.bindString(12,WebPortalPassword);
            sqLiteStatement.execute();


        }catch (Exception e){

        }
        Intent intent = new Intent(Create.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }

    public void editsave(View view){




       // String name = editTextName.getText().toString();
        String name = editTextName.getText().toString();
        String company = editTextCompany.getText().toString();
        String modem = editTextModem.getText().toString();
        String modemIp = editTextModemIp.getText().toString();
        String modemAdminName = editTextModemAdminName.getText().toString();
        String modemAdminPassword = editTextModemAdminPassword.getText().toString();
        String dslUsername = editTextDslUsername.getText().toString();
        String dslPassword = editTextDslPassword.getText().toString();
        String wifiName = editTextWifiName.getText().toString();
        String WifiPassword = editTextWifiPassword.getText().toString();
        String WebPortalName = editTextWebPortalUsername.getText().toString();
        String WebPortalPassword = editTextWebPortalPassword.getText().toString();

        Intent intent = getIntent();
        int modemID = intent.getIntExtra("modemID",1);
        System.out.println("modemID:" + modemID);

        try {


            modemDatabase = this.openOrCreateDatabase("ModemInfo",MODE_PRIVATE,null);
            modemDatabase.execSQL("CREATE TABLE IF NOT EXISTS modems (ID INTEGER PRIMARY KEY,Name VARCHAR,Company VARCHAR,Modem VARCHAR,ModemIp VARCHAR," +
                    "ModemAdminName VARCHAR,ModemAdminPassword VARCHAR,DslUserName VARCHAR,DslPassword VARCHAR,WifiName VARCHAR,WifiPassword VARCHAR,WebPortalName VARCHAR,WebPortalPassword VARCHAR)");

            modemDatabase.execSQL("UPDATE modems SET " +
                    " Name='" + name +
                    "', Company='" + company +
                    "', Modem='" + modem +
                    "', ModemIp='" + modemIp +
                    "', ModemAdminName='" + modemAdminName +
                    "', ModemAdminPassword='" + modemAdminPassword +
                    "', DslUserName='" + dslUsername +
                    "', DslPassword='" + dslPassword +
                    "', WifiName='" + wifiName +
                    "', WifiPassword='" + WifiPassword +
                    "', WebPortalName='" + WebPortalName +
                    "', WebPortalPassword='" + WebPortalPassword +
                    "' WHERE ID =" + modemID );




        }catch (Exception e){

        }
        Intent intentMain = new Intent(Create.this,MainActivity.class);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentMain);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflater
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_or_edit_modem,menu);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
     if (item.getItemId() == R.id.edit_modem_item){
         viewModemsEdit();
             buttonSave.setVisibility(View.INVISIBLE);
         buttonEditSave.setVisibility(View.VISIBLE);
       //  Toast.makeText(Create.this,"Edit",Toast.LENGTH_LONG).show();
            //editTextName.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));

                 editTextName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
         editTextName.setTextIsSelectable(true);
       // editTextName.setBackgroundColor(R.drawable.ic_launcher_background);



     }
       else if ( item.getItemId() == R.id.delete_modem_item){

         AlertDialog.Builder deleteAlert = new AlertDialog.Builder(this);

         deleteAlert.setTitle("Confirm Deletion");
         deleteAlert.setMessage("Are you sure you want to remove this item?");
         deleteAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

                 Intent intent = getIntent();
                int modemID = intent.getIntExtra("modemID",1);
                 System.out.println("modemID:" + modemID);

                 try {

                     modemDatabase.execSQL("DELETE FROM modems WHERE ID= ?",new String[]{String.valueOf(modemID)});
                     Toast.makeText(Create.this,"Selected modem has been deleted",Toast.LENGTH_SHORT).show();
                     Intent intentMain = new Intent(Create.this,MainActivity.class);
                     intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intentMain);

                 }catch (Exception e){

                 }


             }
         });
         deleteAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
               //  Toast.makeText(Create.this,"Olmadı",Toast.LENGTH_LONG).show();
             }
         });

         deleteAlert.show();


        }
        return super.onOptionsItemSelected(item);
    }
}