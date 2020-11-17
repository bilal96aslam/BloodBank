package com.example.bloodbank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.model.Donor;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

private Context mContext;
private ArrayList<Donor> mylist;

public Myadapter(Context context, ArrayList<Donor> list)
{
    this.mContext=context;
    this.mylist=list;
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    Donor donor= mylist.get(position);
    holder.name.setText(donor.getName());
    holder.grp.setText(donor.getBlood_group());
    holder.city.setText(donor.getCity());
    holder.cell.setText(String.valueOf(donor.getCell()));
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView grp;
        private TextView city;
        private TextView cell;
        MyViewHolder(View v) {
            super(v);

            name=v.findViewById(R.id.name_t);
            grp=v.findViewById(R.id.blood_t);
            city=v.findViewById(R.id.city_t);
            cell=v.findViewById(R.id.phone_t);

        }
    }
}
