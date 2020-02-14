package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.pdd.Adapters.ListViewAdapterForPaidFine;
import com.example.pdd.DBHelp.DBHelperFines;

public class PaidFines_Activity extends AppCompatActivity {
    private ListView lview;
    private ListViewAdapterForPaidFine lviewAdapter;

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

    @Override
    protected void onResume() {
        lview = (ListView) findViewById(R.id.list_view_payed);
        lview.setClickable(false);

        String id[];
        String text[];
        String postDate[];
        String postNum[];
        String suma[];
        String totalSuma[];
        String discountDate[];

        String svids[];
        try {
            DBHelperFines dbHelper;
            dbHelper = new DBHelperFines(PaidFines_Activity.this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.query(DBHelperFines.TABLE_FINES, null, null, null, null, null, null);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            long numRows = DatabaseUtils.queryNumEntries(db, DBHelperFines.TABLE_FINES);
            id = new String [(int)numRows];
            text = new String [(int)numRows];
            postDate = new String [(int)numRows];
            postNum = new String [(int)numRows];
            suma = new String [(int)numRows];
            totalSuma = new String [(int)numRows];
            discountDate = new String [(int)numRows];

            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelperFines.KEY_IDFINE);
                int textIndex = cursor.getColumnIndex(DBHelperFines.KEY_KOAPTEXT);
                int postDateIndex = cursor.getColumnIndex(DBHelperFines.KEY_POSTDATE);
                int postNumIndex = cursor.getColumnIndex(DBHelperFines.KEY_POSTNUM);
                int sumaIndex = cursor.getColumnIndex(DBHelperFines.KEY_SUMA);
                int totalSumaIndex = cursor.getColumnIndex(DBHelperFines.KEY_TOTALSUMA);
                int discountDateIndex = cursor.getColumnIndex(DBHelperFines.KEY_DISCOUNTDATE);
                int k =0;
                do {
                    id[k]=cursor.getString(idIndex);
                    text[k]=cursor.getString(textIndex);
                    postDate[k]=cursor.getString(postDateIndex);
                    postNum[k]=cursor.getString(postNumIndex);
                    suma[k]=cursor.getString(sumaIndex);
                    totalSuma[k]=cursor.getString(totalSumaIndex);
                    discountDate[k]=cursor.getString(discountDateIndex);
                    k++;
                } while (cursor.moveToNext());
            } else Log.d("mLog","0 rows");
            cursor.close();

            lviewAdapter = new ListViewAdapterForPaidFine(PaidFines_Activity.this,id,text,postDate,postNum,suma,totalSuma,discountDate);
            lview.setAdapter(lviewAdapter);

        } catch (Exception e){
            Log.e("DB","DB not found");
        }
        View empty = findViewById(R.id.list_view_texpayd_empty);
        lview.setEmptyView(empty);
        super.onResume();
    }
}
