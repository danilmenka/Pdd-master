package com.example.pdd.ui.Autos;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.Adapters.ListViewAdapterForCars;
import com.example.pdd.DBHelp.DBHelperCars;
import com.example.pdd.DBHelp.DBHelperDrivers;
import com.example.pdd.Edit_Add_Auto_Activity;
import com.example.pdd.R;

public class AutosFragment extends Fragment implements ListViewAdapterForCars.CallBackCars {
    private ListView lview;
    private ListViewAdapterForCars lviewAdapter;
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_autos, container, false);
        Button button=root.findViewById(R.id.addAuto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent41= new Intent(getActivity(), Edit_Add_Auto_Activity.class);
                startActivity(intent41);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        lview = (ListView) getActivity().findViewById(R.id.list_view_cars);
        lview.setClickable(false);
        String id[];
        String idDb[];
        String names[];
        String regNumbers[];
        String svids[];
        try {
            DBHelperCars dbHelper;
            dbHelper = new DBHelperCars(getActivity());
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.query(DBHelperCars.TABLE_CARS, null, null, null, null, null, null);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            long numRows = DatabaseUtils.queryNumEntries(db, DBHelperCars.TABLE_CARS);
            id = new String [(int)numRows];
            idDb = new String [(int)numRows];
            names = new String [(int)numRows];
            regNumbers = new String [(int)numRows];
            svids = new String [(int)numRows];
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelperCars.KEY_IDCAR);
                int nameIndex = cursor.getColumnIndex(DBHelperCars.KEY_TITLE);
                int regIndex = cursor.getColumnIndex(DBHelperCars.KEY_REGNUM);
                int svidsIndex = cursor.getColumnIndex(DBHelperCars.KEY_STSNUM);
                int idDbIndex = cursor.getColumnIndex(DBHelperCars.KEY_ID);
                int k =0;
                do {
                    id[k]=cursor.getString(idIndex);
                    names[k]=cursor.getString(nameIndex);
                    regNumbers[k]=cursor.getString(regIndex);
                    svids[k]=cursor.getString(svidsIndex);
                    idDb[k]=cursor.getString(idDbIndex);
                    k++;
                } while (cursor.moveToNext());

            } else Log.d("mLog","0 rows");

            cursor.close();

            lviewAdapter = new ListViewAdapterForCars(getActivity(),id,names,regNumbers, svids,idDb);
            lviewAdapter.registrationCallBackCars(this);
            lview.setAdapter(lviewAdapter);

        } catch (Exception e){
            Log.e("DB","DB not found");
        }
        View empty = getActivity().findViewById(R.id.textEmpty);
        lview.setEmptyView(empty);
        super.onResume();
    }

    @Override
    public void doCallBackCars() {
        onResume();
    }
}