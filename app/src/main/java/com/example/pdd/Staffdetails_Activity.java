package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pdd.DBHelp.DBHelperFines;
import com.example.pdd.DBHelp.DBHelperUnpaidFines;
import com.example.pdd.Requests.AsyncPattern;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Staffdetails_Activity extends AppCompatActivity implements View.OnClickListener, AsyncPattern.AsyncPatternCallBack {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    int appealCount;

    ImageView imageView;
    Button button1;
    Button button2;
    Button button4;
    String id="";
    String text="";
    String postDate="";
    String postNum="";
    String suma="";
    String totalSuma="";
    String discountDate="";
    String koapCode="";
    String address="";
    String regnum="";
    String lisenceNum="";
    String regnumTxt="";
    String lisenceNumTxt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffdetails_);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Детали штрафа");




        Bundle arguments = getIntent().getExtras();
        id = arguments.get("id").toString();

        try {
            DBHelperUnpaidFines dbHelper;
            dbHelper = new DBHelperUnpaidFines(Staffdetails_Activity.this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("select * from " + DBHelperUnpaidFines.TABLE_UNPAID_FINES + " where " + DBHelperUnpaidFines.KEY_IDFINE + "='" + id + "'" , null);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_IDFINE);
                int textIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_KOAPTEXT);
                int postDateIndex = cursor.getColumnIndex(DBHelperFines.KEY_POSTDATE);
                int postNumIndex = cursor.getColumnIndex(DBHelperFines.KEY_POSTNUM);
                int sumaIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_SUMA);
                int totalSumaIndex = cursor.getColumnIndex(DBHelperFines.KEY_TOTALSUMA);
                int discountDateIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_DISCOUNTDATE);
                int koapCodeIndex = cursor.getColumnIndex(DBHelperFines.KEY_KOAPCODE);
                int addressIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_ADDRESS);
                int regnumIndex = cursor.getColumnIndex(DBHelperFines.KEY_CAR);
                int lisenceNumIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_DRIVER);

                    id=cursor.getString(idIndex);
                    text=cursor.getString(textIndex);
                    postDate=cursor.getString(postDateIndex);
                    postNum=cursor.getString(postNumIndex);
                    suma=cursor.getString(sumaIndex);
                    totalSuma=cursor.getString(totalSumaIndex);
                    discountDate=cursor.getString(discountDateIndex);
                    koapCode=cursor.getString(koapCodeIndex);
                    address=cursor.getString(addressIndex);
                    regnum=cursor.getString(regnumIndex);
                    lisenceNum=cursor.getString(lisenceNumIndex);

            } else Log.d("mLog","0 rows");
            cursor.close();
/*
            lviewAdapter = new ListViewAdapterForUnpaidFine(getActivity(),id,text,postDate,postNum,suma,totalSuma,discountDate);
        lviewAdapter.registrationCallBackUnpaidFine(this);
            lview.setAdapter(lviewAdapter);*/

        } catch (Exception e){
            Log.e("DB","DB not found");
        }
        try {
            JSONObject jsonObject= new JSONObject(regnum);
            regnumTxt = jsonObject.getString("stsnum");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject= new JSONObject(lisenceNum);
            lisenceNumTxt = jsonObject.getString("lisenceNum");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        textView1=findViewById(R.id.NumberPostOfStaffDetail);
        textView2=findViewById(R.id.DatePostOfStaffDetail);
        textView3=findViewById(R.id.SumOfStaffDetail);
        textView4=findViewById(R.id.oldPriceOfStaffDetail);
        textView5=findViewById(R.id.KoAPOfStaffDetail);
        textView6=findViewById(R.id.whoGiveOfStaffDetail);
        textView7=findViewById(R.id.photoOfStaffDetail);
        textView8=findViewById(R.id.photo4884);
        textView9=findViewById(R.id.photo1234);

        imageView=findViewById(R.id.imageViewOfStaffDetail);
        button1=findViewById(R.id.butLookMap);
        button2=findViewById(R.id.butPayStaff);
        button4=findViewById(R.id.butHide);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button4.setOnClickListener(this);

        setTextNonNull(postNum,textView1);
        setTextNonNull(formDate(postDate),textView2);

        setTextNonNull(suma,textView3);
        setTextNonNull(totalSuma,textView4);

        if ((suma.equals("null"))||(suma.equals(""))) {
            textView3.setText("Информация отсутствует");
        }else {
        textView3.setText(suma+" р.");}

        if ((totalSuma.equals("null"))||(totalSuma.equals(""))) {
            textView3.setText("Информация отсутствует");
        }else {
            textView4.setText(totalSuma+" р.");}

        appealCount=1;

        AsyncPattern asyncPattern = new AsyncPattern(Staffdetails_Activity.this,"fine/"+id+"/photo/base64",null,false,false,false);
        asyncPattern.registrationAsyncPatternCallBack(Staffdetails_Activity.this);
        asyncPattern.execute();


/*
["\/9j\/4AAQSkZJRgABAQAAAQABAAD\/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL\/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIy" +
                "MjIyMjIyMjL\/wAARCAAYAF0DASIAAhEBAxEB\/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL\/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q" +
                "0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6\/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL\/8QAtREAAgECBAQDBAcFBA" +
                "QAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn" +
                "6Onq8vP09fb3+Pn6\/9oADAMBAAIRAxEAPwCK10iyaFGdCxIB61INHsW6RufowojGLBnDkjyjgY9qS1hfbHIUC45yM5P6UAI+kafEpZkcr296DpVikYcWsj57A0tw87TIyQlkTtnFT2d08kPmSKEKk5BNAFSOzsJHaP7C6uo6MaPsVisqo+mOm44DF+Kt2gLF5mPMhpQfOvTn7sY4oAZ\/Z" +
                "WmgZMK593xTjpmnAAiBPqzHFS3MaMoLIGwR1ptwi+SiqiY3Dg9OlADP7L09lYiFeB1U5qGbT4Y13pZwFR\/eY5P61agi2Bjkcj7q9KRpI3ZRu4HX60AVpbSCOPzBYR7QOTuP6c1bSysHUE2acgHv\/jVa4lczLtKGMfws2KtLKHORKBwOB2oA5+11dmtVjZY8Y2kE4qyNZ8uPHlw4HvRRQA0a7" +
                "uJxHF\/30aQathCghi2nqOf8aKKAF\/tp0AxHEAOwz\/jQNdIGFih\/X\/GiigAOuuwwYYiPx\/xoOuucZhi4+v8AjRRQANrspGAkQz7H\/GmjVDjJjgP1z\/jRRQAHVAR9yAfmP61Wl1Z4WxGIsHrjmiigD\/\/Z",
                "\/9j\/4AAQSkZJRgABAQAAAQABAAD\/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL\/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMj" +
                        "IyMjL\/wAARCAFkAyADASIAAhEBAxEB\/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL\/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZW" +
                        "ZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6\/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL\/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMi" +
                        "MoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6\/9oADAMBAAIRAxEAPwDi9" +
                        "O8PaTcWMEslrmRkUk+a3cfWr6+FdFPWy\/8AIrf41LovOl2n\/XFe\/sK0xxQBmDwjohP\/AB4\/+Rn\/AMakHg\/QT\/y4f+Rn\/wAa1AasjJ7UAYn\/AAh2g\/8APj\/5Gk\/xo\/4Q7Qf+fH\/yNJ\/jW8PpS4INAGB\/wh2g\/wDPh\/5Gk\/xpf+EO0H\/nw\/8AIz\/41vBc0YoAwf8" +
                        "AhDtA\/wCfD\/yNJ\/jSjwboP\/Ph\/wCRn\/xroO9FAGB\/whugf9A\/\/wAjSf40f8Ib4f8A+fD\/AMjP\/wDFVv8A5UnUZ70AYH\/CHaB\/z4f+RpP8aP8AhDtA\/wCfD\/yM\/wD8VW9+P5UmSB\/9egDAPg7Qf+fD\/wAjP\/jULeEdEH\/Lj\/5Gf\/GujNV5B\/nNAGAfCuijpZf+RX\/" +
                        "xqA+GNG7Wn\/kVv8a6Aj61BIP8mgDEPhrR+n2T\/wAit\/jTodC0yAkrZRnP\/PTLfzrVI5pv0oAotpdh2sLX\/v0tINM0\/dzYW3\/fkf4VeOPSmYJ70AVJNN03oLG2H\/bJf8Kj\/sywP\/Llbf8AfoVfYAg5b9aYRxQBROmWOf8Ajytv+\/QoGnWH\/Plbf9+hVs0Z\/GgCp\/Zlh\/z6W\/" +
                        "\/Llbf9+hVvgDv+dCDcoIoArf2ZYf8+dv\/wB+h\/hSf2bYjj7Fbf8Aflatke9J7GgCr\/Z1j\/z5W\/8A35Wnf2ZYn\/lytv8Av0tWeAOlL1HFAFUaZYH\/AJcrb\/v0P8KX+zLHH\/Hja\/8AfoVY25p4X3FAFQaZYf8APja\/9+h\/hThpmnn\/AJcbX\/v0Ks456VIFPpxQBTGl6f8A8+Nr\/wB+" +
                        "\/hSjTNP\/wCfG1\/79L\/hV3y8HrSMD14oAo\/2Zp\/\/AD42v\/fof4Uv9l2Haxtv+\/K\/4Va5+tLQBWGl6eR\/x4Wv\/fpf8KX+y9P\/AOfC1\/78r\/hVnpRQBV\/svT\/+fG1\/79D\/AApP7L0\/\/nxtf+\/Iq3+I\/OjrQBSOl6f\/AM+Nt\/35H+FIdLsB\/wAuNt\/36X\/CrZFJ074oArf2" +
                        "XYH\/AJcbb\/vyKcNL0\/vY23\/flasA4P8A9elzQBTOl2AP\/Hlbf9+V\/wAKiuNMsRBKRZQD5TyIh6VpEE9KhnUiCXI6qf5UAUdP8PaVPp9tLLa\/M8SsTvbnIHvVseFtIBybIMPTzW\/xrc0Swin0CwMbjebePIb\/AHRUk1pLbyYeMr\/I09AMmDwtoDkEWCt6q0zg\/wA61rTwn4TeUCbRRg9xc" +
                        "zf\/ABVM8sHnBU\/SrMNyyPtmBYevegRp2\/w88GXZIg02PzFHKtcTAj\/yJVHWvhHZSW8suk2SrKFHlxC4OCfqzf1rcs71ZIFhciWIcqQQHX6HrXQWetRW+xnSbyx1yxJ+vAwf0q9BWZ5dD8Ib64w0NrZeXIchpr4gIPoMmrlx8KtO0CH7dqMttfWqY8+KPzIigyOQS\/zd\/SvTBrPhmxto" +
                        "om1Qx8fLvRs+vYU8eJ\/Dy8vrFoy4wd+VyPfinyoTbPJbHwv4Kl12\/W4ito7ERxNbefeNEDkc4JYE\/jWwPBvw+Yjb\/Zx\/7ih\/+OVvjT\/hpqk1w93caTCwlIQrfG3+XAI4DAHrWXqPw+8ConmQeKUtSxyiz6hCyH8+f1p6LoO5X\/4QbwEQMR6ef+4of\/jlc5D4Q0GbxEbZLKOSH" +
                        "+0zAFFwxHlYz13c16NpvhHwHY6eQ7aTcAnLS3VwsmO3DZHFa*/


//["\/9j\/4AAQSkZJRgABAQAAAQABAAD\/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL\/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL\/wAARCAAYAF0DASIAAhEBAxEB\/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL\/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6\/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL\/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6\/9oADAMBAAIRAxEAPwCK10iyaFGdCxIB61INHsW6RufowojGLBnDkjyjgY9qS1hfbHIUC45yM5P6UAI+kafEpZkcr296DpVikYcWsj57A0tw87TIyQlkTtnFT2d08kPmSKEKk5BNAFSOzsJHaP7C6uo6MaPsVisqo+mOm44DF+Kt2gLF5mPMhpQfOvTn7sY4oAZ\/ZWmgZMK593xTjpmnAAiBPqzHFS3MaMoLIGwR1ptwi+SiqiY3Dg9OlADP7L09lYiFeB1U5qGbT4Y13pZwFR\/eY5P61agi2Bjkcj7q9KRpI3ZRu4HX60AVpbSCOPzBYR7QOTuP6c1bSysHUE2acgHv\/jVa4lczLtKGMfws2KtLKHORKBwOB2oA5+11dmtVjZY8Y2kE4qyNZ8uPHlw4HvRRQA0a7uJxHF\/30aQathCghi2nqOf8aKKAF\/tp0AxHEAOwz\/jQNdIGFih\/X\/GiigAOuuwwYYiPx\/xoOuucZhi4+v8AjRRQANrspGAkQz7H\/GmjVDjJjgP1z\/jRRQAHVAR9yAfmP61Wl1Z4WxGIsHrjmiigD\/\/Z","\/9j\/4AAQSkZJRgABAQAAAQABAAD\/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL\/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL\/wAARCAFkAyADASIAAhEBAxEB\/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL\/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6\/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL\/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6\/9oADAMBAAIRAxEAPwDi9O8PaTcWMEslrmRkUk+a3cfWr6+FdFPWy\/8AIrf41LovOl2n\/XFe\/sK0xxQBmDwjohP\/AB4\/+Rn\/AMakHg\/QT\/y4f+Rn\/wAa1AasjJ7UAYn\/AAh2g\/8APj\/5Gk\/xo\/4Q7Qf+fH\/yNJ\/jW8PpS4INAGB\/wh2g\/wDPh\/5Gk\/xpf+EO0H\/nw\/8AIz\/41vBc0YoAwf8AhDtA\/wCfD\/yNJ\/jSjwboP\/Ph\/wCRn\/xroO9FAGB\/whugf9A\/\/wAjSf40f8Ib4f8A+fD\/AMjP\/wDFVv8A5UnUZ70AYH\/CHaB\/z4f+RpP8aP8AhDtA\/wCfD\/yM\/wD8VW9+P5UmSB\/9egDAPg7Qf+fD\/wAjP\/jULeEdEH\/Lj\/5Gf\/GujNV5B\/nNAGAfCuijpZf+RX\/xqA+GNG7Wn\/kVv8a6Aj61BIP8mgDEPhrR+n2T\/wAit\/jTodC0yAkrZRnP\/PTLfzrVI5pv0oAotpdh2sLX\/v0tINM0\/dzYW3\/fkf4VeOPSmYJ70AVJNN03oLG2H\/bJf8Kj\/sywP\/Llbf8AfoVfYAg5b9aYRxQBROmWOf8Ajytv+\/QoGnWH\/Plbf9+hVs0Z\/GgCp\/Zlh\/z6W\/8A36FA0yxP\/Llbf9+hVvgDv+dCDcoIoArf2ZYf8+dv\/wB+h\/hSf2bYjj7Fbf8Aflatke9J7GgCr\/Z1j\/z5W\/8A35Wnf2ZYn\/lytv8Av0tWeAOlL1HFAFUaZYH\/AJcrb\/v0P8KX+zLHH\/Hja\/8AfoVY25p4X3FAFQaZYf8APja\/9+h\/hThpmnn\/AJcbX\/v0Ks456VIFPpxQBTGl6f8A8+Nr\/wB+R\/hSjTNP\/wCfG1\/79L\/hV3y8HrSMD14oAo\/2Zp\/\/AD42v\/fof4Uv9l2Haxtv+\/K\/4Va5+tLQBWGl6eR\/x4Wv\/fpf8KX+y9P\/AOfC1\/78r\/hVnpRQBV\/svT\/+fG1\/79D\/AApP7L0\/\/nxtf+\/Iq3+I\/OjrQBSOl6f\/AM+Nt\/35H+FIdLsB\/wAuNt\/36X\/CrZFJ074oArf2XYH\/AJcbb\/vyKcNL0\/vY23\/flasA4P8A9elzQBTOl2AP\/Hlbf9+V\/wAKiuNMsRBKRZQD5TyIh6VpEE9KhnUiCXI6qf5UAUdP8PaVPp9tLLa\/M8SsTvbnIHvVseFtIBybIMPTzW\/xrc0Swin0CwMbjebePIb\/AHRUk1pLbyYeMr\/I09AMmDwtoDkEWCt6q0zg\/wA61rTwn4TeUCbRRg9xczf\/ABVM8sHnBU\/SrMNyyPtmBYevegRp2\/w88GXZIg02PzFHKtcTAj\/yJVHWvhHZSW8suk2SrKFHlxC4OCfqzf1rcs71ZIFhciWIcqQQHX6HrXQWetRW+xnSbyx1yxJ+vAwf0q9BWZ5dD8Ib64w0NrZeXIchpr4gIPoMmrlx8KtO0CH7dqMttfWqY8+KPzIigyOQS\/zd\/SvTBrPhmxtoom1Qx8fLvRs+vYU8eJ\/Dy8vrFoy4wd+VyPfinyoTbPJbHwv4Kl12\/W4ito7ERxNbefeNEDkc4JYE\/jWwPBvw+Yjb\/Zx\/7ih\/+OVvjT\/hpqk1w93caTCwlIQrfG3+XAI4DAHrWXqPw+8ConmQeKUtSxyiz6hCyH8+f1p6LoO5X\/4QbwEQMR6ef+4of\/jlc5D4Q0GbxEbZLKOSH+0zAFFwxHlYz13c16NpvhHwHY6eQ7aTcAnLS3VwsmO3DZHFa



        if(koapCode.equals("null")) setTextNonNull(text,textView5);
        else setTextNonNull(koapCode+" "+ text,textView5);
        setTextNonNull(formDate(discountDate),textView6);
        setTextNonNull(address,textView7);
        setTextNonNull(regnumTxt,textView8);
        setTextNonNull(lisenceNumTxt,textView9);
        button2.setText("Оплатить "+suma+" р.");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butLookMap:
                break;
            case R.id.butPayStaff:
                String idPosition [] = new String [1];
                idPosition[0] = id;
                Intent intent411= new Intent(Staffdetails_Activity.this, PayActivity.class);
                intent411.putExtra("id",idPosition);
                startActivity(intent411);
                break;
            case R.id.butHide:

                AsyncPattern asyncPattern = new AsyncPattern(Staffdetails_Activity.this,"fine/"+id,null,false,false,true);
                asyncPattern.registrationAsyncPatternCallBack(Staffdetails_Activity.this);
                asyncPattern.execute();

                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setTextNonNull (String string, TextView textView){
        if ((string.equals("null"))||(string.equals(""))) string = "Информация отсутствует";
        textView.setText(string);
    }
    private String formDate (String date){
        String dateDate;
        String dateTime;
        if (date.length()>10){
            dateDate= date.substring(0,10);
            dateTime = date.substring(11,16);
            String strCurrentDate = dateDate;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date newDate = null;
            try {
                newDate = format.parse(strCurrentDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            format = new SimpleDateFormat("dd.mm.yyyy");
            if(dateTime.equals("00:00"))
            date = format.format(newDate);
              else
             date = format.format(newDate)+" в "+dateTime+"(Мск)";
        }
        return date;
    }


    @Override
    public void doAsyncPatternCallBack(String answer) {

        if (appealCount==1){

            try {
                JSONObject jsonObject = new JSONObject(answer);
                String k = jsonObject.getString("data");
                JSONArray jsonArray = new JSONArray(k);
            //    String strArray[] = new String[jsonArray.length()];



                String arr = k;
                String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

                String [] results = new String[items.length];

                for (int i = 0; i < items.length; i++) {
                    try {
                        results[i] = items[i].substring(1,items[i].length()-1);
                    } catch (NumberFormatException nfe) {
                        //NOTE: write something here if you need to recover from formatting errors
                    };
                }






                String kk=results[0].substring(1,results[0].length()-1);
                fromBase64(kk,imageView);
            }catch (Exception e){                    Log.e("fre","ERR2");}


                /*
                Log.e("INAGE",k);
                String result=k;
                result = result.split(",")[0];
                Log.e("ONETONES",result);
                result = result.substring(2,result.length()-1);
                Log.e("ONETONE1S",result);
                fromBase64(result);*/



            appealCount =2;
        }

            if (appealCount==2){
        try {
            JSONObject jsonObject = new JSONObject(answer);
            String k = jsonObject.getString("data");
            if (k.equals(true)){
                Intent i = new Intent(Staffdetails_Activity.this,MainActivity.class);
                i.putExtra("nameClass","first");
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }catch (Exception e){}
        try {
            JSONObject jsonObject = new JSONObject(answer);
            String k = jsonObject.getString("message");
            Toast toast = Toast.makeText(Staffdetails_Activity.this.getApplicationContext(),
                    k, Toast.LENGTH_SHORT);
            toast.show();
        }catch (Exception e){}}



    }
    public void fromBase64(String image,ImageView myImag) {
        // Декодируем строку Base64 в массив байтов
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        // Декодируем массив байтов в изображение
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        // Помещаем изображение в ImageView
        myImag.setImageBitmap(decodedByte);
    }
}
