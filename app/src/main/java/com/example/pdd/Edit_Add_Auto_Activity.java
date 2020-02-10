package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pdd.Requests.AsyncPattern;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Edit_Add_Auto_Activity extends AppCompatActivity implements AsyncPattern.AsyncPatternCallBack  {
   EditText editText1;
   EditText editText2;
   EditText editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit__add__auto);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Редактировать добавить авто");

        editText3=findViewById(R.id.editAddNameAuto);
        editText2=findViewById(R.id.editAddRegistrNumber);
        editText1=findViewById(R.id.editAddRegistr);

        Button button=findViewById(R.id.butOfEditAddAuto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List nameValuePairs = new ArrayList(3);
                nameValuePairs.add(new BasicNameValuePair("stsnum",String.valueOf( editText1.getText())));
                nameValuePairs.add(new BasicNameValuePair("regnum", String.valueOf( editText2.getText())));
                nameValuePairs.add(new BasicNameValuePair("title", String.valueOf( editText3.getText())));
                AsyncPattern asyncPattern= new AsyncPattern(Edit_Add_Auto_Activity.this,"car",nameValuePairs,true);
                asyncPattern.registrationAsyncPatternCallBack(Edit_Add_Auto_Activity.this);
                asyncPattern.execute();

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

    }
}
