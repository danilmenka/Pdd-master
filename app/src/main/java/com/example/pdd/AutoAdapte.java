package com.example.pdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AutoAdapte{
    /*private LayoutInflater inflater;
    private List<Auto> autos;

    public AutoAdapte(List<Auto> autos,Context context) {
        this.autos = autos;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public AutoAdapte.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AutoAdapte.ViewHolder holder, int position) {
        Auto auto = autos.get(position);
        holder.nameView.setText(auto.getName());
        holder.companyView.setText(auto.getCompany());
    }

    @Override
    public int getItemCount() {
        return autos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, companyView;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.idName);
            companyView = (TextView) view.findViewById(R.id.idRegNumber);
        }
    }*/
}

