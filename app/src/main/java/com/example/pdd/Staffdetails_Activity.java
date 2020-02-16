package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import com.example.pdd.DBHelp.DBHelperPhoto;
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

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;


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

        imageView1 = (ImageView)findViewById(R.id.imageViewOfStaffDetail2);
        imageView2 = (ImageView)findViewById(R.id.imageViewOfStaffDetail);
        imageView3 = (ImageView)findViewById(R.id.imageViewOfStaffDetail3);
        imageView4 = (ImageView)findViewById(R.id.imageViewOfStaffDetail5);
        imageView5 = (ImageView)findViewById(R.id.imageViewOfStaffDetail6);
        imageView6 = (ImageView)findViewById(R.id.imageViewOfStaffDetail4);

        Bundle arguments = getIntent().getExtras();
        id = arguments.get("id").toString();

        try {
            DBHelperUnpaidFines dbHelper;
            dbHelper = new DBHelperUnpaidFines(Staffdetails_Activity.this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("select * from " + DBHelperUnpaidFines.TABLE_UNPAID_FINES + " where " + DBHelperUnpaidFines.KEY_IDFINE + "='" + id + "'", null);
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

                id = cursor.getString(idIndex);
                text = cursor.getString(textIndex);
                postDate = cursor.getString(postDateIndex);
                postNum = cursor.getString(postNumIndex);
                suma = cursor.getString(sumaIndex);
                totalSuma = cursor.getString(totalSumaIndex);
                discountDate = cursor.getString(discountDateIndex);
                koapCode = cursor.getString(koapCodeIndex);
                address = cursor.getString(addressIndex);
                regnum = cursor.getString(regnumIndex);
                lisenceNum = cursor.getString(lisenceNumIndex);

            } else Log.d("mLog", "0 rows");
            cursor.close();
/*
            lviewAdapter = new ListViewAdapterForUnpaidFine(getActivity(),id,text,postDate,postNum,suma,totalSuma,discountDate);
        lviewAdapter.registrationCallBackUnpaidFine(this);
            lview.setAdapter(lviewAdapter);*/

        } catch (Exception e) {
            Log.e("DB", "DB not found");
        }
        try {
            JSONObject jsonObject = new JSONObject(regnum);
            regnumTxt = jsonObject.getString("stsnum");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(lisenceNum);
            lisenceNumTxt = jsonObject.getString("lisenceNum");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            DBHelperPhoto dbHelper;
            dbHelper = new DBHelperPhoto(Staffdetails_Activity.this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Log.e("GERMAN", "ONE");
            Cursor cursor = database.rawQuery("select * from " + DBHelperPhoto.TABLE_PHOTO + " where " + DBHelperPhoto.KEY_IDOBJECT + "='" + id + "'", null);
            int lisenceNumIndex = cursor.getColumnIndex(DBHelperPhoto.KEY_BASE64);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String a1[] = new String[20];
            int k = 0;
            if (cursor.moveToFirst()) {


                do {
                    a1[k] = cursor.getString(lisenceNumIndex);
                    k++;
                } while (cursor.moveToNext());
            } else Log.d("mLog", "0 rows");
            cursor.close();
            Log.d("fRE", String.valueOf(k));
            switch (k) {
                case (1):
                    fromBase64(a1[0], imageView1);
                    break;
                case (2):
                    fromBase64(a1[0], imageView1);
                    fromBase64(a1[1], imageView2);
                    break;
                case (3):
                    fromBase64(a1[0], imageView1);
                    fromBase64(a1[1], imageView2);
                    fromBase64(a1[2], imageView3);
                    break;
                case (4):
                    fromBase64(a1[0], imageView1);
                    fromBase64(a1[1], imageView2);
                    fromBase64(a1[2], imageView3);
                    fromBase64(a1[3], imageView4);
                    break;
                case (5):
                    fromBase64(a1[0], imageView1);
                    fromBase64(a1[1], imageView2);
                    fromBase64(a1[2], imageView3);
                    fromBase64(a1[3], imageView4);
                    fromBase64(a1[4], imageView5);
                    break;
                case (6):
                    fromBase64(a1[0], imageView1);
                    fromBase64(a1[1], imageView2);
                    fromBase64(a1[2], imageView3);
                    fromBase64(a1[3], imageView4);
                    fromBase64(a1[4], imageView5);
                    fromBase64(a1[5], imageView6);
                    break;
            }


        } catch (Exception e){
            Log.e("DB","DB not found");
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

                String[] items = k.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

                String [] results = new String[items.length];

                for (int i = 0; i < items.length; i++) {
                    try {
                        results[i] = items[i].substring(1,items[i].length()-1);

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DBHelperPhoto.KEY_FROMING,"unFines");
                        contentValues.put(DBHelperPhoto.KEY_IDOBJECT,id);
                        contentValues.put(DBHelperPhoto.KEY_BASE64,results[i]);
                        DBHelperPhoto dbHelper;
                        dbHelper = new DBHelperPhoto(Staffdetails_Activity.this);
                        SQLiteDatabase database = dbHelper.getWritableDatabase();
                        database.insert(DBHelperPhoto.TABLE_PHOTO, null, contentValues);

                    } catch (NumberFormatException nfe) {
                        //NOTE: write something here if you need to recover from formatting errors
                    }
                }


                switch (results.length){
                    case (1):
                        fromBase64(results[0],imageView1);
                        break;
                    case (2):
                        fromBase64(results[0],imageView1);
                        fromBase64(results[1],imageView2);
                        break;
                    case (3):
                        fromBase64(results[0],imageView1);
                        fromBase64(results[1],imageView2);
                        fromBase64(results[2],imageView3);
                        break;
                    case (4):
                        fromBase64(results[0],imageView1);
                        fromBase64(results[1],imageView2);
                        fromBase64(results[2],imageView3);
                        fromBase64(results[3],imageView4);
                        break;
                    case (5):
                        fromBase64(results[0],imageView1);
                        fromBase64(results[1],imageView2);
                        fromBase64(results[2],imageView3);
                        fromBase64(results[3],imageView4);
                        fromBase64(results[4],imageView5);
                        break;
                    case (6):
                        fromBase64(results[0],imageView1);
                        fromBase64(results[1],imageView2);
                        fromBase64(results[2],imageView3);
                        fromBase64(results[3],imageView4);
                        fromBase64(results[4],imageView5);
                        fromBase64(results[5],imageView6);
                        break;

                }









            }catch (Exception e){
                Log.e("fre","ERR2");}

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
