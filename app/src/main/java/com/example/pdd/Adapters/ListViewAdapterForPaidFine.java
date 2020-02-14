package com.example.pdd.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.pdd.DetailPaidFine;
import com.example.pdd.R;
import com.example.pdd.Staffdetails_Activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListViewAdapterForPaidFine extends BaseAdapter
{
    Activity context;
    String id[];
    String text[];
    String postDate[];
    String postNum[];
    String suma[];
    String totalSuma[];
    String discountDate[];


    public ListViewAdapterForPaidFine (Activity context, String id[],String text[],String postDate[],String postNum[],String suma[],String totalSuma[],String discountDate[]) {
        super();
        this.context = context;
        this.id = id;
        this.text = text;
        this.postDate = postDate;
        this.postNum = postNum;
        this.suma = suma;
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
            convertView = inflater.inflate(R.layout.list_item_paid, null);
            holder = new ViewHolder();
            holder.txtText = (TextView) convertView.findViewById(R.id.textViewPaydText);
            holder.txtPostDate = (TextView) convertView.findViewById(R.id.textView17);
            holder.txtPostNum = (TextView) convertView.findViewById(R.id.textView21);
            holder.txtSuma = (TextView) convertView.findViewById(R.id.textView22);

            holder.paiButton = (Button) convertView.findViewById(R.id.buttonPayd);

            holder.cardView = (CardView)convertView.findViewById(R.id.card_view_paid);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
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

        if(text[position].length()<5){
            text[position] = "Неизвестное нарушение";
        }

        holder.txtText.setText(text[position]);
        holder.txtPostDate.setText(postDate[position]);
        holder.txtPostNum.setText(postNum[position]);
        holder.txtSuma.setText(suma[position]+"р.");




        holder.paiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG",id[position]);
            /*    Intent intent41= new Intent(context, Edit_Add_Auto_Activity.class);
                context.startActivity(intent41);*/
            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent42231= new Intent(context, DetailPaidFine.class);
                intent42231.putExtra("id",id[position]);
                context.startActivity(intent42231);
            }
        });

        return convertView;
    }
}