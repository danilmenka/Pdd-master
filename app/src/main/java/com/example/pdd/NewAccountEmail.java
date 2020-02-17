package com.example.pdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pdd.Requests.AsyncPattern;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewAccountEmail extends AppCompatActivity implements AsyncPattern.AsyncPatternCallBack{
    int count;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account_email);
        editText = findViewById(R.id.editAccountEmail);
        button = findViewById(R.id.butAccountNext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length()<2){
                    Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                            "Введите email", Toast.LENGTH_SHORT);
                    toast.show();
                }else {

                    List nameValuePairs;
                    nameValuePairs= new ArrayList(1);
                    nameValuePairs.add(new BasicNameValuePair("email",String.valueOf(editText.getText())));
                    AsyncPattern asyncPattern = new AsyncPattern(NewAccountEmail.this,"account",nameValuePairs,true,false,false);
                    asyncPattern.registrationAsyncPatternCallBack(NewAccountEmail.this);
                    count=1;
                    asyncPattern.execute();
                }

            }
        });

    }

    @Override
    public void doAsyncPatternCallBack(String answer) {
        try {

            if (count==1){


            JSONObject jsonObject = new JSONObject(answer);
            if (jsonObject.getString("data").equals("false")) {
                if (jsonObject.getString("checkEmail").equals("true")) {
                    Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                            "Введите код для активации, высланный на email", Toast.LENGTH_SHORT);
                    toast.show();
                    count = 2;
                    



                }
            }else {
                Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                        "Аккаунт успешно создан", Toast.LENGTH_SHORT);
                toast.show();
            }
            }
            if (count==2){




            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("LOGAN",answer);
    }
}
