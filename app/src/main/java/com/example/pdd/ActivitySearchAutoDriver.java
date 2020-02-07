package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivitySearchAutoDriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Поиск по автомобилю и водителю");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
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
            case R.id.searchNumber:
                Intent intent421=new Intent(ActivitySearchAutoDriver.this, SearchOfNumberPost_Activity.class);
                startActivity(intent421);
                break;
            case R.id.pay:
                Intent intent4212=new Intent(ActivitySearchAutoDriver.this, PaidFines_Activity.class);
                startActivity(intent4212);
                break;
            case R.id.notpay:
                Intent intent4122=new Intent(ActivitySearchAutoDriver.this, Unpaid_fines_Activity.class);
                startActivity(intent4122);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
