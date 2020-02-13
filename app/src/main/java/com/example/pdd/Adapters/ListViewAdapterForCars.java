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

import com.example.pdd.DBHelp.DBHelperCars;
import com.example.pdd.Edit_Add_Auto_Activity;
import com.example.pdd.R;
import com.example.pdd.Requests.AsyncPattern;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterForCars extends BaseAdapter implements AsyncPattern.AsyncPatternCallBack
{
    Activity context;
    String names[];
    String regNumbers[];
    String svids[];
    String id[];
    String idDb[];
    String idPosition;
    public interface CallBackCars{
        void doCallBackCars();
    }
    CallBackCars callBackCars;
    public void registrationCallBackCars(CallBackCars callBackCars){
        this.callBackCars = callBackCars;
    }

    public ListViewAdapterForCars(Activity context, String id[],String names[],String regNumbers[],String svids[],String idDb[]) {
        super();
        this.context = context;
        this.id = id;
        this.names = names;
        this.regNumbers = regNumbers;
        this.svids = svids;
        this.idDb = idDb;
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
        TextView txtViewRegNumber;
        TextView txtViewSvid;

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
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtViewName = (TextView) convertView.findViewById(R.id.text_car_one1);
            holder.txtViewRegNumber = (TextView) convertView.findViewById(R.id.text_car_two1);
            holder.txtViewSvid = (TextView) convertView.findViewById(R.id.text_car_three1);
            holder.editButton = (Button) convertView.findViewById(R.id.text_car_four);
            holder.deleteButton = (Button) convertView.findViewById(R.id.text_car_four1);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtViewName.setText(names[position]);
        holder.txtViewRegNumber.setText(regNumbers[position]);
        holder.txtViewSvid.setText(svids[position]);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG",id[position]);
                Intent intent41= new Intent(context, Edit_Add_Auto_Activity.class);
                intent41.putExtra("name",names[position]);
                intent41.putExtra("regNumber",regNumbers[position]);
                intent41.putExtra("svid",svids[position]);
                intent41.putExtra("id",id[position]);
                context.startActivity(intent41);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Удаление автомобиля")
                        .setMessage("Вы действительно хотите удалить "+names[position]+"?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                List nameValuePairs = new ArrayList(1);
                                nameValuePairs.add(new BasicNameValuePair("stsnum","g"));
                                AsyncPattern asyncPattern= new AsyncPattern(context,"car/"+id[position],nameValuePairs,false,false,true);
                                asyncPattern.registrationAsyncPatternCallBack(ListViewAdapterForCars.this);
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
        if (answer.equals("true")){
            DBHelperCars dbHelper;
            dbHelper = new DBHelperCars(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            db.delete(DBHelperCars.TABLE_CARS, DBHelperCars.KEY_IDCAR + "=" + String.valueOf(idPosition), null);
            callBackCars.doCallBackCars();
        }

    }

}