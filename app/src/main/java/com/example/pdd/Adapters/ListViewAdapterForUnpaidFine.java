package com.example.pdd.Adapters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.example.pdd.DBHelp.DBHelperCars;
import com.example.pdd.Edit_Add_Auto_Activity;
import com.example.pdd.R;
import com.example.pdd.Requests.AsyncPattern;
import com.example.pdd.Staffdetails_Activity;

import org.apache.http.message.BasicNameValuePair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        CardView cardView;

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
            holder.txtTotalSuma = (TextView) convertView.findViewById(R.id.text_unpaid_totalSuma);
            holder.cardView = (CardView)convertView.findViewById(R.id.card_view_unpaid);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        int ttSm=0;
        int sm=0;
        try {
            ttSm= Integer.parseInt(totalSuma[position]);
            sm = Integer.parseInt(suma[position]);
        }catch (Exception e){
        }


        if(totalSuma[position].equals("")||totalSuma[position].equals(null)){
            holder.txtTotalSuma.setVisibility(View.GONE);
        }else
            if(ttSm>sm)
            {
            holder.txtTotalSuma.setText(totalSuma[position]+"р.");
            holder.txtTotalSuma.setPaintFlags(holder.txtTotalSuma.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {holder.txtTotalSuma.setVisibility(View.GONE);
            }

        if(discountDate[position].length()<6){
            holder.txtDiscountDate.setVisibility(View.GONE);
        }

        if (postDate[position].length()>10){
        postDate[position]= postDate[position].substring(0,10);

            String strCurrentDate = postDate[position];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date newDate = null;
            try {
                newDate = format.parse(strCurrentDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            format = new SimpleDateFormat("dd.mm.yyyy");
            postDate[position] = format.format(newDate);
        }
        if (discountDate[position].length()>10){

        discountDate[position].substring(0,10);
        String strCurrentDate = discountDate[position];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("dd.mm.yyyy");
        discountDate[position] = "Скидка 50% осталась до "+format.format(newDate);
    }

        holder.txtText.setText(text[position]);
        holder.txtPostDate.setText(postDate[position]);
        holder.txtPostNum.setText(postNum[position]);
        holder.txtSuma.setText(suma[position]+"р.");
        holder.txtDiscountDate.setText(discountDate[position]);





        holder.paiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG",id[position]);
                Intent intent41= new Intent(context, Edit_Add_Auto_Activity.class);
                context.startActivity(intent41);
            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4221= new Intent(context, Staffdetails_Activity.class);
                intent4221.putExtra("id",id[position]);
                context.startActivity(intent4221);
            }
        });

        return convertView;
    }
}