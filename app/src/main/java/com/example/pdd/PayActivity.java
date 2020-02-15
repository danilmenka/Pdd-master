package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PayActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    String name;
    String id [];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Оплатить штраф");
        try {
            id = getIntent().getExtras().getStringArray("id");
    Log.e("ZAZUBR",id[0]);
        } catch (Exception e){
            Log.e("Tag","new Car");
        }
    //    editText1=findViewById(R.id.etPhone);






        editText2=findViewById(R.id.editPHIO);
        Button button=findViewById(R.id.butPayOfPayActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
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
