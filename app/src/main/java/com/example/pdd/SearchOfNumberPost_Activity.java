package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchOfNumberPost_Activity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity__search_of_number_post);
        editText=findViewById(R.id.editSearchOfnumberPost);
        Button button=findViewById(R.id.butSearcOfNymberPost);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
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
            case R.id.searchNumber2:
                Intent intent41=new Intent(SearchOfNumberPost_Activity.this, ActivitySearchAutoDriver.class);
                startActivity(intent41);
                finishAffinity();
                break;
            case R.id.pay2:
                Intent intent411=new Intent(SearchOfNumberPost_Activity.this,PaidFines_Activity.class);
                startActivity(intent411);
                break;
            case R.id.notpay2:
                Intent intent1=new Intent(SearchOfNumberPost_Activity.this,Unpaid_fines_Activity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
