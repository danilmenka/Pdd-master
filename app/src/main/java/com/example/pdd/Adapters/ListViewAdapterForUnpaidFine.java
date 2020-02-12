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
import android.widget.AdapterView;
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

public class ListViewAdapterForUnpaidFine extends BaseAdapter
{
    Activity context;
    String id[];
    String text[];
    String postDate[];
    String postNum[];
    String suma[];
    String totalSuma[];
    String discountDate[];


    public ListViewAdapterForUnpaidFine(Activity context, String id[],String text[],String postDate[],String postNum[],String suma[],String totalSuma[],String discountDate[]) {
        super();
        this.context = context;
        this.id = id;
        this.text = text;
        this.postDate = postDate;
        this.postNum = postNum;
        this.suma = suma;
        this.totalSuma = totalSuma;
        this.discountDate = discountDate;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return text.length;
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
        TextView txtText;
        TextView txtPostDate;
        TextView txtPostNum;
        TextView txtSuma;
        TextView txtDiscountDate;
        TextView txtTotalSuma;

        Button paiButton;

    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_item_unpdaid, null);
            holder = new ViewHolder();
            holder.txtText = (TextView) convertView.findViewById(R.id.text_unpaid3);
            holder.txtPostDate = (TextView) convertView.findViewById(R.id.text_unpaid1);
            holder.txtPostNum = (TextView) convertView.findViewById(R.id.text_unpaid4);
            holder.txtSuma = (TextView) convertView.findViewById(R.id.text_unpaid5);
            holder.txtDiscountDate = (TextView) convertView.findViewById(R.id.text_unpaid7);
            holder.paiButton = (Button) convertView.findViewById(R.id.text_unpaid6);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtText.setText(text[position]);
        holder.txtPostDate.setText(postDate[position]);
        holder.txtPostNum.setText(postNum[position]);
        holder.txtSuma.setText(suma[position]);
        holder.txtDiscountDate.setText(discountDate[position]);


        holder.paiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG",id[position]);
                Intent intent41= new Intent(context, Edit_Add_Auto_Activity.class);
             /*   intent41.putExtra("name",names[position]);
                intent41.putExtra("regNumber",regNumbers[position]);
                intent41.putExtra("svid",svids[position]);
                intent41.putExtra("id",id[position]);*/
                context.startActivity(intent41);
            }
        });

        return convertView;
    }
    

}