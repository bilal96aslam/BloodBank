package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bloodbank.Adapter.Myadapter;
import com.example.bloodbank.database.database;
import com.example.bloodbank.fragments.SearchDonor;
import com.example.bloodbank.model.Donor;

import java.util.ArrayList;

public class Recyclerview extends AppCompatActivity {

    private RecyclerView recyclerview;
    private Myadapter myadaptor;
    private ArrayList<Donor> mylist;
    database db;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerview=findViewById(R.id.mrecycler_view);
        db = new database(this);
        mylist=new ArrayList<>();
       img=(ImageView) findViewById(R.id.backImg_search);

        Bundle extra = getIntent().getExtras();
        String group = extra.getString("Blood_grp");
        String city = extra.getString("City");

        mylist=db.getSearchData(city,group);

        if (mylist.isEmpty()){
            Toast.makeText(this,"No Records Found!",Toast.LENGTH_LONG).show();
        }
        else{
            recyclerview.hasFixedSize();
            LinearLayoutManager manager=new LinearLayoutManager(this);
            recyclerview.setLayoutManager(manager);
            myadaptor=new Myadapter(this,mylist);
            recyclerview.setAdapter(myadaptor);
        }


//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fm=getSupportFragmentManager();
//                MainActivity m=new MainActivity();
//                fm.beginTransaction().replace(R.id.mainfrag,m).commit();
//            }
//        });



    }
}
