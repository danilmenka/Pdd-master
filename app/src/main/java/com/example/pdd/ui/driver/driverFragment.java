package com.example.pdd.ui.driver;

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

import com.example.pdd.Adapters.ListViewAdapterForDivers;
import com.example.pdd.DBHelp.DBHelperDrivers;
import com.example.pdd.Edit_Add_Driver_Activity;
import com.example.pdd.R;
import com.example.pdd.ui.Autos.SlideshowViewModel;

public class driverFragment extends Fragment implements ListViewAdapterForDivers.CallBackDrivers{
    private ListView lview;
    private ListViewAdapterForDivers lviewAdapter;


    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_driver, container, false);

        Button button=root.findViewById(R.id.addDriver);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent41=new Intent(getActivity(), Edit_Add_Driver_Activity.class);
                startActivity(intent41);
            }
        });
        return root;
    }
    @Override
    public void onResume() {
        lview = (ListView) getActivity().findViewById(R.id.list_view_drivers);
        lview.setClickable(false);
        String id[];
        String names[];
        String licenseNum[];
        try {
            DBHelperDrivers dbHelper;
            dbHelper = new DBHelperDrivers(getActivity());
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.query(DBHelperDrivers.TABLE_DRIVERS, null, null, null, null, null, null);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            long numRows = DatabaseUtils.queryNumEntries(db, DBHelperDrivers.TABLE_DRIVERS);
            id = new String [(int)numRows];
            names = new String [(int)numRows];
            licenseNum = new String [(int)numRows];
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelperDrivers.KEY_IDDRIVER);
                int nameIndex = cursor.getColumnIndex(DBHelperDrivers.KEY_TITLE);
                int licenseNumIndex = cursor.getColumnIndex(DBHelperDrivers.KEY_LICENSENUM);
                int k =0;
                do {
                    id[k]=cursor.getString(idIndex);
                    names[k]=cursor.getString(nameIndex);
                    licenseNum[k]=cursor.getString(licenseNumIndex);
                    k++;
                } while (cursor.moveToNext());

            } else Log.d("mLog","0 rows");

            cursor.close();
            lviewAdapter = new ListViewAdapterForDivers(getActivity(),id,names,licenseNum);
            lviewAdapter.registrationCallBackDrivers(this);
            lview.setAdapter(lviewAdapter);

        } catch (Exception e){
            Log.e("DB","DB not found");
        }
        View empty = getActivity().findViewById(R.id.textEmptyDriver);
        lview.setEmptyView(empty);
        super.onResume();
    }

    @Override
    public void doCallBackDrivers() {
        onResume();
    }
}