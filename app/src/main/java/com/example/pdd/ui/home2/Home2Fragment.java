package com.example.pdd.ui.home2;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.Adapters.ListViewAdapterForUnpaidFine;
import com.example.pdd.DBHelp.DBHelperFines;
import com.example.pdd.DBHelp.DBHelperUnpaidFines;
import com.example.pdd.PaidFines_Activity;
import com.example.pdd.PayActivity;
import com.example.pdd.R;
import com.example.pdd.SearchOfNumberPost_Activity;
import com.example.pdd.Unpaid_fines_Activity;

public class Home2Fragment extends Fragment {

    private ListView lview;
    private ListViewAdapterForUnpaidFine lviewAdapter;
    private HomeViewModel2 homeViewModel2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel2 = ViewModelProviders.of(this).get(HomeViewModel2.class);
        View root = inflater.inflate(R.layout.fragment_home2, container, false);
        setHasOptionsMenu (true);
        Button button=root.findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent411= new Intent(getActivity(), PayActivity.class);
                startActivity(intent411);
            }
        });
        return root;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menuhome2, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }



    @Override
    public void onResume() {
        lview = (ListView) getActivity().findViewById(R.id.list_view_unpay);
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
            DBHelperUnpaidFines dbHelper;
            dbHelper = new DBHelperUnpaidFines(getActivity());
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.query(DBHelperUnpaidFines.TABLE_UNPAID_FINES, null, null, null, null, null, null);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            long numRows = DatabaseUtils.queryNumEntries(db, DBHelperUnpaidFines.TABLE_UNPAID_FINES);
            id = new String [(int)numRows];
            text = new String [(int)numRows];
            postDate = new String [(int)numRows];
            postNum = new String [(int)numRows];
            suma = new String [(int)numRows];
            totalSuma = new String [(int)numRows];
            discountDate = new String [(int)numRows];

            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_IDFINE);
                int textIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_KOAPTEXT);
                int postDateIndex = cursor.getColumnIndex(DBHelperFines.KEY_POSTDATE);
                int postNumIndex = cursor.getColumnIndex(DBHelperFines.KEY_POSTNUM);
                int sumaIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_SUMA);
                int totalSumaIndex = cursor.getColumnIndex(DBHelperFines.KEY_TOTALSUMA);
                int discountDateIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_DISCOUNTDATE);

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

            lviewAdapter = new ListViewAdapterForUnpaidFine(getActivity(),id,text,postDate,postNum,suma,totalSuma,discountDate);
        //    lviewAdapter.registrationCallBackUnpaidFine(this);
            lview.setAdapter(lviewAdapter);

        } catch (Exception e){
            Log.e("DB","DB not found");
        }
        View empty = getActivity().findViewById(R.id.list_view_textunpay_empty);
        lview.setEmptyView(empty);
        super.onResume();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.searchNum:
                Intent intent421=new Intent(getActivity(), SearchOfNumberPost_Activity.class);
                startActivity(intent421);
                break;
            case R.id.payww:
                Intent intent4212=new Intent(getActivity(), PaidFines_Activity.class);
                startActivity(intent4212);
                break;
            case R.id.notpayww:
                Intent intent4122=new Intent(getActivity(), Unpaid_fines_Activity.class);
                startActivity(intent4122);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}