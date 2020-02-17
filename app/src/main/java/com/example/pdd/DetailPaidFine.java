package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pdd.DBHelp.DBHelperFines;
import com.example.pdd.DBHelp.DBHelperPhoto;
import com.example.pdd.Requests.AsyncPattern;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailPaidFine extends AppCompatActivity implements AsyncPattern.AsyncPatternCallBack{

        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;


    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    String id="";
        Button button1;
        Button button2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_paid_fine);
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Детали штрафа");
            imageView1 = (ImageView)findViewById(R.id.imageViewOfStaffDetailPai);
            imageView2 = (ImageView)findViewById(R.id.imageViewOfStaffDetailPai2);
            imageView3 = (ImageView)findViewById(R.id.imageViewOfStaffDetailPai3);
            imageView4 = (ImageView)findViewById(R.id.imageViewOfStaffDetailPai4);
            imageView5 = (ImageView)findViewById(R.id.imageViewOfStaffDetailPai5);
            imageView6 = (ImageView)findViewById(R.id.imageViewOfStaffDetailPai6);


            String text="";
            String postDate="";
            String postNum="";
            String totalSuma="";
            String koapCode="";
            String address="";
            String regnum="";
            String lisenceNum="";
            String regnumTxt="";
            String lisenceNumTxt="";

            Bundle arguments = getIntent().getExtras();
            id = arguments.get("id").toString();

            try {
                DBHelperFines dbHelper;
                dbHelper = new DBHelperFines(DetailPaidFine.this);
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = database.rawQuery("select * from " + DBHelperFines.TABLE_FINES + " where " + DBHelperFines.KEY_IDFINE + "='" + id + "'" , null);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelperFines.KEY_IDFINE);
                    int textIndex = cursor.getColumnIndex(DBHelperFines.KEY_KOAPTEXT);
                    int postDateIndex = cursor.getColumnIndex(DBHelperFines.KEY_POSTDATE);
                    int postNumIndex = cursor.getColumnIndex(DBHelperFines.KEY_POSTNUM);
                    int totalSumaIndex = cursor.getColumnIndex(DBHelperFines.KEY_TOTALSUMA);
                    int koapCodeIndex = cursor.getColumnIndex(DBHelperFines.KEY_KOAPCODE);
                    int addressIndex = cursor.getColumnIndex(DBHelperFines.KEY_ADDRESS);
                    int regnumIndex = cursor.getColumnIndex(DBHelperFines.KEY_CAR);
                    int lisenceNumIndex = cursor.getColumnIndex(DBHelperFines.KEY_DRIVER);

                    id=cursor.getString(idIndex);
                    text=cursor.getString(textIndex);
                    postDate=cursor.getString(postDateIndex);
                    postNum=cursor.getString(postNumIndex);
                    totalSuma=cursor.getString(totalSumaIndex);
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


            textView1=findViewById(R.id.postNumPaid);
            textView2=findViewById(R.id.postDatePaid);
            textView3=findViewById(R.id.suma_paid);
            textView4=findViewById(R.id.koap_paid);
            textView5=findViewById(R.id.address_paid);
            textView6=findViewById(R.id.rernum_paid);
            textView7=findViewById(R.id.vod_paid);

            button1=findViewById(R.id.butLookMapPaid);
            button2=findViewById(R.id.butLookDocofPaid);







            AsyncPattern asyncPattern = new AsyncPattern(DetailPaidFine.this,"fine/"+id+"/photo/base64",null,false,false,false);
            asyncPattern.registrationAsyncPatternCallBack(DetailPaidFine.this);
            asyncPattern.execute();

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent42= new Intent(DetailPaidFine.this, PhotoDetail.class);
                    intent42.putExtra("id",id);
                    intent42.putExtra("numButton",1);
                    startActivity(intent42);

                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent42= new Intent(DetailPaidFine.this, PhotoDetail.class);
                    intent42.putExtra("id",id);
                    intent42.putExtra("numButton",2);
                    startActivity(intent42);

                }
            });
            imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent42= new Intent(DetailPaidFine.this, PhotoDetail.class);
                    intent42.putExtra("id",id);
                    intent42.putExtra("numButton",3);
                    startActivity(intent42);

                }
            });
            imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent42= new Intent(DetailPaidFine.this, PhotoDetail.class);
                    intent42.putExtra("id",id);
                    intent42.putExtra("numButton",4);
                    startActivity(intent42);

                }
            });
            imageView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent42= new Intent(DetailPaidFine.this, PhotoDetail.class);
                    intent42.putExtra("id",id);
                    intent42.putExtra("numButton",5);
                    startActivity(intent42);

                }
            });
            imageView6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent42= new Intent(DetailPaidFine.this, PhotoDetail.class);
                    intent42.putExtra("id",id);
                    intent42.putExtra("numButton",6);
                    startActivity(intent42);

                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent42= new Intent(DetailPaidFine.this, PhotoDetail.class);
                    intent42.putExtra("id",id);
                    intent42.putExtra("dock","delta");
                    startActivity(intent42);
                }
            });

            setTextNonNull(postNum,textView1);
            setTextNonNull(formDate(postDate),textView2);
            setTextNonNull(totalSuma,textView3);
            if(koapCode.equals("null")) setTextNonNull(text,textView4);
                else setTextNonNull(koapCode+" "+ text,textView4);
            setTextNonNull(address,textView5);
            setTextNonNull(regnumTxt,textView6);
            setTextNonNull(lisenceNumTxt,textView7);

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
            if ((string.equals("null"))||(string.equals(""))||(string.equals("null null"))) string = "Информация отсутствует";
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

        try {
            JSONObject jsonObject = new JSONObject(answer);
            String k = jsonObject.getString("data");

            String[] items = k.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

            String [] results = new String[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    results[i] = items[i].substring(1,items[i].length()-1);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DBHelperPhoto.KEY_FROMING,"fines");
                    contentValues.put(DBHelperPhoto.KEY_IDOBJECT,id);
                    contentValues.put(DBHelperPhoto.KEY_BASE64,results[i]);
                    DBHelperPhoto dbHelper;
                    dbHelper = new DBHelperPhoto(DetailPaidFine.this);
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