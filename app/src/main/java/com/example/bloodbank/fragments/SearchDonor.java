package com.example.bloodbank.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.Adapter.Myadapter;
import com.example.bloodbank.LoginActivity;
import com.example.bloodbank.MainActivity;
import com.example.bloodbank.R;
import com.example.bloodbank.Recyclerview;
import com.example.bloodbank.SignupActivity;
import com.example.bloodbank.database.database;
import com.example.bloodbank.getAllDataActivity;
import com.example.bloodbank.model.Donor;

import java.util.ArrayList;

public class SearchDonor extends Fragment {

    private EditText blood_var,city_var;
    private Button search,viewAll;
    database db;

    private RecyclerView recyclerview;
    private Myadapter myadaptor;
    private ArrayList mylist;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.activity_search_donor,container,false);

        db = new database( this.getActivity() );
        //search
        blood_var = (EditText) view.findViewById( R.id.bloodgrp_et2);
        city_var = (EditText) view.findViewById( R.id.city_et );
        search = (Button) view.findViewById( R.id.search_butt);
        viewAll = (Button) view.findViewById( R.id.allData_butt);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylist=new ArrayList<>();

                String blood_group = blood_var.getText().toString();
                String city=city_var.getText().toString();

                Intent intent = new Intent((MainActivity)getActivity(), Recyclerview.class);
                intent.putExtra("Blood_grp", blood_group);
                intent.putExtra("City", city);
                blood_var.getText().clear();
                city_var.getText().clear();
                startActivity(intent);

            }

        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent((MainActivity)getActivity(), getAllDataActivity.class);
                startActivity(intent);
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
