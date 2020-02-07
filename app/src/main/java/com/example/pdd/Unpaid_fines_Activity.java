package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Unpaid_fines_Activity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid_fines_);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Неоплаченные штрафы");

        /*textView1=findViewById(R.id.numberPost2);
        textView2=findViewById(R.id.sumWithout);
        textView3=findViewById(R.id.sumWith);
        textView4=findViewById(R.id.datePost2);
        textView5=findViewById(R.id.violatedArticle2);
        textView6=findViewById(R.id.numberKOAP2);
        textView7=findViewById(R.id.numberAutoId2);
        textView8=findViewById(R.id.numberUd2);
        Button button=findViewById(R.id.payBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.uppaid_menu,menu);
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
            case R.id.searchNumber4:
                Intent intent41=new Intent(Unpaid_fines_Activity.this, ActivitySearchAutoDriver.class);
                startActivity(intent41);
                break;
            case R.id.ser4:
                Intent intent411=new Intent(Unpaid_fines_Activity.this,SearchOfNumberPost_Activity.class);
                startActivity(intent411);
                break;
            case R.id.pay4:
                Intent intent1=new Intent(Unpaid_fines_Activity.this,PaidFines_Activity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
