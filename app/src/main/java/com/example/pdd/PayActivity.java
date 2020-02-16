package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pdd.Requests.AsyncPattern;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends AppCompatActivity implements AsyncPattern.AsyncPatternCallBack {

    EditText editText1;
    EditText editText2;
    String name;
    String id [];
    String ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Оплатить штраф");
        try {
            id = getIntent().getExtras().getStringArray("id");
        } catch (Exception e){
            Log.e("Tag","new Car");
        }


        editText1 = findViewById(R.id.masked_edit_text);
        editText2=findViewById(R.id.editPHIO);
        Button button=findViewById(R.id.butPayOfPayActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.length()<11){
                    Toast toast = Toast.makeText(PayActivity.this,
                            "Введите номер телефона", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    if (editText2.length()<1){
                        Toast toast = Toast.makeText(PayActivity.this,
                                "Введите ФИО", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {

                        List nameValuePairs = new ArrayList(id.length+2);
                        nameValuePairs.add(new BasicNameValuePair("fio",String.valueOf( editText2.getText())));
                        nameValuePairs.add(new BasicNameValuePair("phone", String.valueOf( editText1.getText())));
                        for (int i=0;i<id.length;i++){
                            nameValuePairs.add(new BasicNameValuePair("id", id[i]));
                        }
                        AsyncPattern asyncPattern = new AsyncPattern(PayActivity.this,"invoice",nameValuePairs,true,false,false);
                        asyncPattern.registrationAsyncPatternCallBack(PayActivity.this);
                        asyncPattern.execute();
                    }

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
        Log.e("PAY",answer);
        try {
            JSONObject jsonObject = new JSONObject(answer);
            String payUrl = jsonObject.getString("payUrl");
            Log.e("PayUrl",payUrl);
        }catch (Exception e){}


    }
}
