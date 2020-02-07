package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Staffdetails_Activity extends AppCompatActivity implements View.OnClickListener {

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

        textView1=findViewById(R.id.NumberPostOfStaffDetail);
        textView2=findViewById(R.id.DatePostOfStaffDetail);
        textView3=findViewById(R.id.SumOfStaffDetail);
        textView4=findViewById(R.id.oldPriceOfStaffDetail);
        textView5=findViewById(R.id.KoAPOfStaffDetail);
        textView6=findViewById(R.id.whoGiveOfStaffDetail);
        textView7=findViewById(R.id.photoOfStaffDetail);
        imageView=findViewById(R.id.imageViewOfStaffDetail);
        button1=findViewById(R.id.butLookMap);
        button2=findViewById(R.id.butPayStaff);
        button3=findViewById(R.id.buttAppeal);
        button4=findViewById(R.id.butHide);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

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
}
