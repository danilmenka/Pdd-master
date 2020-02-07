package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PaidFines_Activity extends AppCompatActivity {
    //TextView textView1;
    //TextView textView2;
    //TextView textView3;
    //TextView textView4;
    //TextView textView5;
    //TextView textView6;
    //TextView textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_fines_);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Оплаченные штрафы");

        /*textView1=findViewById(R.id.numberPost);
        textView2=findViewById(R.id.paidSum);
        textView3=findViewById(R.id.datePost);
        textView4=findViewById(R.id.violatedArticle);
        textView5=findViewById(R.id.numberKOAP);
        textView6=findViewById(R.id.numberAutoId);
        textView7=findViewById(R.id.numberUd);
        Button button=findViewById(R.id.docOfpaid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Здесь пока что переход на активити детали штрафа
                Intent intent421=new Intent(PaidFines_Activity.this, Staffdetails_Activity.class);
                startActivity(intent421);
            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.paid_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
            case R.id.searchNumber3:
                Intent intent41=new Intent(PaidFines_Activity.this, ActivitySearchAutoDriver.class);
                startActivity(intent41);
                break;
            case R.id.ser:
                Intent intent411=new Intent(PaidFines_Activity.this,SearchOfNumberPost_Activity.class);
                startActivity(intent411);
                break;
            case R.id.notpay3:
                Intent intent1=new Intent(PaidFines_Activity.this,Unpaid_fines_Activity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
