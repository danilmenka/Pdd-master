package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pdd.DBHelp.DBHelperFines;
import com.example.pdd.DBHelp.DBHelperUnpaidFines;

import org.json.JSONException;
import org.json.JSONObject;

public class Staffdetails_Activity extends AppCompatActivity implements View.OnClickListener {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;

    ImageView imageView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffdetails_);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Детали штрафа");

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
        button3=findViewById(R.id.buttAppeal);
        button4=findViewById(R.id.butHide);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        setTextNonNull(postNum,textView1);
        setTextNonNull(postDate,textView2);
        setTextNonNull(suma,textView3);
        setTextNonNull(totalSuma,textView4);
        setTextNonNull(koapCode+" "+ text,textView5);
        setTextNonNull(discountDate,textView6);
        setTextNonNull(address,textView7);
        setTextNonNull(regnumTxt,textView8);
        setTextNonNull(lisenceNumTxt,textView9);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butLookMap:
                break;
            case R.id.butPayStaff:
                Intent intent421=new Intent(Staffdetails_Activity.this, PayActivity.class);
                startActivity(intent421);
                break;
            case R.id.buttAppeal:
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
        if ((string.equals("null"))||(string.equals(""))) string = "Информация отсутствует";
        textView.setText(string);
    }
}
