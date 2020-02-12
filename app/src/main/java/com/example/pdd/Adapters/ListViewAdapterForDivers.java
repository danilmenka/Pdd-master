package com.example.pdd.Adapters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.pdd.DBHelp.DBHelperDrivers;
import com.example.pdd.Edit_Add_Driver_Activity;
import com.example.pdd.R;
import com.example.pdd.Requests.AsyncPattern;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterForDivers extends BaseAdapter implements AsyncPattern.AsyncPatternCallBack
{
    Activity context;
    String names[];
    String id[];
    String licenseNum[];
    String idPosition;
    public interface CallBackDrivers{
        void doCallBackDrivers();
    }
    CallBackDrivers callBackDrivers;
    public void registrationCallBackDrivers(CallBackDrivers callBackDrivers){
        this.callBackDrivers = callBackDrivers;
    }

    public ListViewAdapterForDivers(Activity context, String id[],String names[], String licenseNum[]) {
        super();
        this.context = context;
        this.id = id;
        this.names = names;
        this.licenseNum = licenseNum;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return names.length;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    private class ViewHolder {
        TextView txtViewName;
        TextView txtViewLicenseNum;
        Button editButton;
        Button deleteButton;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_item_drivers, null);
            holder = new ViewHolder();
            holder.txtViewName = (TextView) convertView.findViewById(R.id.text_diver_one1);
            holder.txtViewLicenseNum = (TextView) convertView.findViewById(R.id.text_driver_two1);
            holder.editButton = (Button) convertView.findViewById(R.id.text_driver_four);
            holder.deleteButton = (Button) convertView.findViewById(R.id.text_driver_four1);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtViewName.setText(names[position]);
        holder.txtViewLicenseNum.setText(licenseNum[position]);
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG",id[position]);
                Intent intent41= new Intent(context, Edit_Add_Driver_Activity.class);
                intent41.putExtra("name",names[position]);
                intent41.putExtra("licenseNum",licenseNum[position]);
                intent41.putExtra("id",id[position]);
                context.startActivity(intent41);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Удаление водителя")
                        .setMessage("Вы действительно хотите удалить "+names[position]+"?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                List nameValuePairs = new ArrayList(1);
                                nameValuePairs.add(new BasicNameValuePair("stsnum","g"));
                                AsyncPattern asyncPattern= new AsyncPattern(context,"driver/"+id[position],nameValuePairs,false,false,true);
                                asyncPattern.registrationAsyncPatternCallBack(ListViewAdapterForDivers.this);
                                idPosition = id[position];
                                asyncPattern.execute();
                            }
                        }).create().show();
            }
        });


        return convertView;
    }

    @Override
    public void doAsyncPatternCallBack(String answer) {
        answer = answer.trim();

        try {
            JSONObject jsonObject = new JSONObject(answer);
            if (jsonObject.getString("data").equals("true")) {
                DBHelperDrivers dbHelper;
                dbHelper = new DBHelperDrivers(context);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                db.delete(DBHelperDrivers.TABLE_DRIVERS, DBHelperDrivers.KEY_IDDRIVER + "=" + String.valueOf(idPosition), null);
                callBackDrivers.doCallBackDrivers();
            }}
        catch (Exception e){
            Log.e("E","newDriver");
        }
    }

}