package com.example.pdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pdd.Requests.AsyncPattern;
import com.example.pdd.ui.cabinet.ShareCabinet;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewAccountEmail extends AppCompatActivity implements AsyncPattern.AsyncPatternCallBack{
    int count;
    EditText editText;
    Button button;
    String txt;
    private static final String APP_PREFERENCES = "PddSettings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account_email);
        txt="";
        try {
            Bundle arguments = getIntent().getExtras();
            txt = arguments.get("txt").toString();
        } catch (Exception e){
            Log.e("Tag","new Driver");
        }



        editText = findViewById(R.id.editAccountEmail);
        button = findViewById(R.id.butAccountNext);
        count=1;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ANANAs1",txt+"LALA");
                if (txt.equals("new")){
                    if (count==2){                Log.e("ANANAs1",txt+"C2");
                        if (txt.equals("new")){
                            if (editText.getText().length()<2){
                                Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                                        "Введите код для подтверждаения email", Toast.LENGTH_SHORT);
                                toast.show();
                            }else {
                                List nameValuePairs;
                                nameValuePairs= new ArrayList(1);
                                nameValuePairs.add(new BasicNameValuePair("pin",String.valueOf(editText.getText())));
                                AsyncPattern asyncPattern = new AsyncPattern(NewAccountEmail.this,"account/pin/check",nameValuePairs,true,false,false);
                                asyncPattern.registrationAsyncPatternCallBack(NewAccountEmail.this);
                                asyncPattern.execute();
                            }
                        }}

                if (count==1){

                if (editText.getText().length()<2){
                    Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                            "Введите email", Toast.LENGTH_SHORT);
                    toast.show();
                }else {

                    List nameValuePairs;
                    nameValuePairs= new ArrayList(1);
                    Log.e("ANANAs1",txt+"C1"+String.valueOf(editText.getText()));
                    nameValuePairs.add(new BasicNameValuePair("email",String.valueOf(editText.getText())));
                    AsyncPattern asyncPattern = new AsyncPattern(NewAccountEmail.this,"account",nameValuePairs,true,false,false);
                    asyncPattern.registrationAsyncPatternCallBack(NewAccountEmail.this);
                    asyncPattern.execute();}
                }

            }if(txt.equals("open")){ Log.e("ANANAs1",txt+"C3");
                    List nameValuePairs;
                    nameValuePairs= new ArrayList(1);
                    nameValuePairs.add(new BasicNameValuePair("email",String.valueOf(editText.getText())));
                    AsyncPattern asyncPattern = new AsyncPattern(NewAccountEmail.this,"account/login",nameValuePairs,true,false,false);
                    asyncPattern.registrationAsyncPatternCallBack(NewAccountEmail.this);
                    asyncPattern.execute();
                }
            }
        });

    }

    @Override
    public void doAsyncPatternCallBack(String answer) {
        Log.e("AKULA",answer);
        Log.e("TXT",count+"S"+txt);
        try {
            if (txt.equals("new")){
            if (count==2){
                Log.e("ANAS","false 2");
                try {JSONObject jsonObject = new JSONObject(answer);
                    if (jsonObject.getString("data").equals("true")) {
                        Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                                "Аккаунт успешно создан", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                                "Введен не верный код активации", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }catch (Exception e){                        Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                        "Введен не верный код активации", Toast.LENGTH_SHORT);
                    toast.show();}}
                if (count==1){
                    Log.e("ANAS","false 1");
                JSONObject jsonObject = new JSONObject(answer);
                if (jsonObject.getString("data").equals("false")) {
                    if (jsonObject.getString("checkEmail").equals("true")) {
                        Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                                "Введите код для активации, высланный на email", Toast.LENGTH_SHORT);
                        toast.show();
                        count = 2;
                        editText.setText("");
                        editText.setHint("Код активации");
                        button.setText("Подтвердить email");
                    }
                }else {
                    Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                            "Аккаунт успешно создан", Toast.LENGTH_SHORT);
                    toast.show();
                }}
                }
            if(txt.equals("open")){





                if(count==2){


                   /*
                    Log.e("ANAS","true 2");
                    JSONObject jsonObject = new JSONObject(answer);
                    SharedPreferences mSettings;
                    mSettings = NewAccountEmail.this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString("email", jsonObject.getString("email"));
                    editor.putString("subscribeEmail", jsonObject.getString("subscribeEmail"));
                    editor.putString("subscribePush", jsonObject.getString("subscribePush"));
                    Intent i = new Intent(NewAccountEmail.this,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);*/
                    }
                if (count==1){
                    {                Log.e("ANAS","true 1");

                        JSONObject jsonObject = new JSONObject(answer);
                        if(jsonObject.getString("data").equals("true")){
                            Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                                    "Выполнен вход в аккаунт", Toast.LENGTH_SHORT);
                            toast.show();
                            SharedPreferences mSettings;
                            mSettings = NewAccountEmail.this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putString("email", String.valueOf(editText.getText()));
                            editor.apply();
                            Intent i = new Intent(NewAccountEmail.this,MainActivity.class);
                            i.putExtra("email",String.valueOf(editText.getText()));
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            /////////////////////////////////////////////////////////////////////
                        }else {
                            Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                                    "Вход в аккаунт не выполнен", Toast.LENGTH_SHORT);
                            toast.show();}}

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(answer);
            String k = new String(jsonObject.getString("message"));
            Toast toast = Toast.makeText(NewAccountEmail.this.getApplicationContext(),
                    k ,Toast.LENGTH_SHORT);
            toast.show();
        }catch (Exception e){}


        Log.e("LOGAN",answer);
    }
}
