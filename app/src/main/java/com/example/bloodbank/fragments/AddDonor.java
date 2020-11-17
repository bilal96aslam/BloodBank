package com.example.bloodbank.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.MainActivity;
import com.example.bloodbank.R;
import com.example.bloodbank.database.database;

public class AddDonor extends Fragment {

    EditText name,blood,city,cell;
    Button reg_donor;

    database db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_add_donor,container,false);


       db= new database(this.getActivity());

        name= (EditText) view.findViewById(R.id.name_et);
        blood= (EditText) view.findViewById(R.id.bloodgrp_et);
        city= (EditText) view.findViewById(R.id.city_et);
        cell= (EditText) view.findViewById(R.id.phone_et);
        reg_donor=(Button) view.findViewById(R.id.reg_button);

        reg_donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Sname,Sblood,Scity,Scell;

                Sname=name.getText().toString();
                Sblood=blood.getText().toString();
                Scity=city.getText().toString();
                Scell=cell.getText().toString();

                if (Sname.equals(" ") || Sblood.equals(" ")|| Scity.equals(" ") || Scell.equals(" ") ){
                    Toast.makeText(getActivity(),"Fields are Empty",Toast.LENGTH_LONG).show();
                }

                boolean result = db.insertDonorForm(Sname,Scity,Sblood, Integer.parseInt(Scell));

                if (result==true){

                    Toast.makeText((MainActivity)getActivity(),"Donor is Register Successfully!",Toast.LENGTH_LONG).show();
                    name.getText().clear();
                    blood.getText().clear();
                    city.getText().clear();
                    cell.getText().clear();
                }
                else {
                    Toast.makeText((MainActivity)getActivity(),"Error in Donor Registration",Toast.LENGTH_LONG).show();
                }

            }
         });

        return view;
    }

    //enable options menu in this fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.logout,menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if (id==R.id.action_logout){
            Toast.makeText(getActivity(), "logout menu bbbb",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
