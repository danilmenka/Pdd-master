package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pdd.DBHelp.DBHelperFines;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailPaidFine extends AppCompatActivity implements View.OnClickListener {

        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;

        ImageView imageView;
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

            String id="";
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

            imageView=findViewById(R.id.imageViewOfStaffDetailPaid);
            button1=findViewById(R.id.butLookMapPaid);
            button2=findViewById(R.id.butLookDocofPaid);
            button1.setOnClickListener(this);
            button2.setOnClickListener(this);

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
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.butLookMap:
                    break;
                case R.id.butPayStaff:
              /*      Intent intent421=new Intent(Staffdetails_Activity.this, PayActivity.class);
                    startActivity(intent421);*/
                    break;

                case R.id.butHide:
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
    }