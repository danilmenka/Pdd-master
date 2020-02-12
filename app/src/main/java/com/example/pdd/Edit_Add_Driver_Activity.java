package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pdd.DBHelp.DBHelperDrivers;
import com.example.pdd.Requests.AsyncPattern;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Edit_Add_Driver_Activity extends AppCompatActivity implements AsyncPattern.AsyncPatternCallBack {
    EditText editText1;
    EditText editText2;
    String name = "";
    String licenseNum = "";
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit__add__driver);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Редактировать добавить водителя");

        editText1=findViewById(R.id.editAddName);
        editText2=findViewById(R.id.editAddNumberDocDriver);
        Button button=findViewById(R.id.butOfEditAddDriver);

        try {
            Bundle arguments = getIntent().getExtras();
            name = arguments.get("name").toString();
            licenseNum = arguments.get("licenseNum").toString();
            id = arguments.get("id").toString();

        } catch (Exception e){
            Log.e("Tag","new Driver");
        }

        if (name.isEmpty()) {
            getSupportActionBar().setTitle("Добавление водителя");
            button.setText("Добавить водителя");
        }
        else {
            getSupportActionBar().setTitle("Редактирование водителя");
            button.setText("Редактировать водителя");
            editText2.setText(licenseNum);
            editText1.setText(name);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.isEmpty()){
                    List nameValuePairs = new ArrayList(2);
                    nameValuePairs.add(new BasicNameValuePair("title",String.valueOf( editText1.getText())));
                    nameValuePairs.add(new BasicNameValuePair("licenseNum", String.valueOf( editText2.getText())));
                    AsyncPattern asyncPattern= new AsyncPattern(Edit_Add_Driver_Activity.this,"driver",nameValuePairs,true,false);
                    asyncPattern.registrationAsyncPatternCallBack(Edit_Add_Driver_Activity.this);
                    asyncPattern.execute();}
                else {
                    List nameValuePairs = new ArrayList(2);
                    nameValuePairs.add(new BasicNameValuePair("title",String.valueOf( editText1.getText())));
                    nameValuePairs.add(new BasicNameValuePair("licenseNum", String.valueOf( editText2.getText())));
                    AsyncPattern asyncPattern= new AsyncPattern(Edit_Add_Driver_Activity.this,"driver/"+id,nameValuePairs,false,true);
                    asyncPattern.registrationAsyncPatternCallBack(Edit_Add_Driver_Activity.this);
                    asyncPattern.execute();
                }

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doAsyncPatternCallBack(String answer) {
        answer = answer.trim();
        try {
            JSONObject jsonObject = new JSONObject(answer);
            if (jsonObject.getString("data").equals("true")) {
                Log.e("ID1", id + " this");
                DBHelperDrivers dbHelper;
                dbHelper = new DBHelperDrivers(this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(DBHelperDrivers.KEY_TITLE, String.valueOf(editText1.getText()));
                cv.put(DBHelperDrivers.KEY_LICENSENUM, String.valueOf(editText2.getText()));
                Log.e("ID2", id + " this");
                db.update(DBHelperDrivers.TABLE_DRIVERS, cv, DBHelperDrivers.KEY_IDDRIVER + "=" + String.valueOf(id), null);
                this.finish();
            }}
        catch (Exception e){
            Log.e("E","newDriver");
        }
        try {
            JSONObject jsonObject = new JSONObject(answer);
            String s1 = jsonObject.getString("data");
            ContentValues contentValues = new ContentValues();
            jsonObject = new JSONObject(s1);
            contentValues.put(DBHelperDrivers.KEY_IDDRIVER,jsonObject.getString("id"));
            contentValues.put(DBHelperDrivers.KEY_LICENSENUM, String.valueOf(editText2.getText()));
            contentValues.put(DBHelperDrivers.KEY_SCANDATE,"txt");
            contentValues.put(DBHelperDrivers.KEY_TITLE,String.valueOf(editText1.getText()));
            contentValues.put(DBHelperDrivers.KEY_DESCR,"txt");
            contentValues.put(DBHelperDrivers.KEY_CREATEDATE,"txt");

            DBHelperDrivers dbHelper;
            dbHelper = new DBHelperDrivers(Edit_Add_Driver_Activity.this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.insert(DBHelperDrivers.TABLE_DRIVERS, null, contentValues);
            this.finish();
        }catch (Exception e){Log.e("Error","not new");}


    }
}
