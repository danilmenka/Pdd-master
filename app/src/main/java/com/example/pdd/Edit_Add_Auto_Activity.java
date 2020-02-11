package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pdd.DBHelp.DBHelperCars;
import com.example.pdd.Requests.AsyncPattern;
import com.example.pdd.ui.Autos.AutosFragment;
import com.example.pdd.ui.home.HomeFragment;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Edit_Add_Auto_Activity extends AppCompatActivity implements AsyncPattern.AsyncPatternCallBack  {
   EditText editText1;
   EditText editText2;
   EditText editText3;
    String name = "";
    String regNumber = "";
    String svid = "";
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit__add__auto);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        Button button=findViewById(R.id.butOfEditAddAuto);
        editText3=findViewById(R.id.editAddNameAuto);
        editText2=findViewById(R.id.editAddRegistrNumber);
        editText1=findViewById(R.id.editAddRegistr);

        try {
            Bundle arguments = getIntent().getExtras();
            name = arguments.get("name").toString();
            regNumber = arguments.get("regNumber").toString();
            svid = arguments.get("svid").toString();
            id = arguments.get("id").toString();
        } catch (Exception e){
            Log.e("Tag","new Car");
        }

        if (name.isEmpty()) {
            getSupportActionBar().setTitle("Добавить автомобиль");
            button.setText("Добавить автомобиль");
        }
        else {
            getSupportActionBar().setTitle("Редактировать автомобиль");
            button.setText("Редактировать автомобиль");
            editText3.setText(name);
            editText2.setText(regNumber);
            editText1.setText(svid);
            }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.isEmpty()){
                List nameValuePairs = new ArrayList(3);
                nameValuePairs.add(new BasicNameValuePair("stsnum",String.valueOf( editText1.getText())));
                nameValuePairs.add(new BasicNameValuePair("regnum", String.valueOf( editText2.getText())));
                nameValuePairs.add(new BasicNameValuePair("title", String.valueOf( editText3.getText())));
                AsyncPattern asyncPattern= new AsyncPattern(Edit_Add_Auto_Activity.this,"car",nameValuePairs,true,false);
                asyncPattern.registrationAsyncPatternCallBack(Edit_Add_Auto_Activity.this);
                asyncPattern.execute();}
                else {
                    List nameValuePairs = new ArrayList(3);
                    nameValuePairs.add(new BasicNameValuePair("stsnum",String.valueOf( editText1.getText())));
                    nameValuePairs.add(new BasicNameValuePair("regnum", String.valueOf( editText2.getText())));
                    nameValuePairs.add(new BasicNameValuePair("title", String.valueOf( editText3.getText())));
                    AsyncPattern asyncPattern= new AsyncPattern(Edit_Add_Auto_Activity.this,"car/"+id,nameValuePairs,false,true);
                    asyncPattern.registrationAsyncPatternCallBack(Edit_Add_Auto_Activity.this);
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
        if (answer.equals("true")){

            DBHelperCars dbHelper;
            dbHelper = new DBHelperCars(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DBHelperCars.KEY_TITLE, String.valueOf(editText3.getText()));
            cv.put(DBHelperCars.KEY_REGNUM, String.valueOf(editText2.getText()));
            cv.put(DBHelperCars.KEY_STSNUM, String.valueOf(editText1.getText()));
            db.update(DBHelperCars.TABLE_CARS, cv, DBHelperCars.KEY_IDCAR + "=" + String.valueOf(id), null);
            this.finish();
        }
        try {
            JSONObject jsonObject = new JSONObject(answer);
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelperCars.KEY_IDCAR,jsonObject.getString("id"));
            contentValues.put(DBHelperCars.KEY_REGNUM,String.valueOf(editText2.getText()));
            contentValues.put(DBHelperCars.KEY_STSNUM,String.valueOf(editText1.getText()));
            contentValues.put(DBHelperCars.KEY_TITLE,String.valueOf(editText3.getText()));
            contentValues.put(DBHelperCars.KEY_LAST,"txt");
            contentValues.put(DBHelperCars.KEY_DESCR,"txt");
            contentValues.put(DBHelperCars.KEY_DATE,"txt");
            DBHelperCars dbHelper;
            dbHelper = new DBHelperCars(Edit_Add_Auto_Activity.this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.insert(DBHelperCars.TABLE_CARS, null, contentValues);
            this.finish();
        }catch (Exception e){Log.e("Error","not new");}


    }
}
