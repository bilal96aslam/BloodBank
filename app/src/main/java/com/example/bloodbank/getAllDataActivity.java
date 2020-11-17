package com.example.bloodbank;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.Adapter.Myadapter;
import com.example.bloodbank.database.database;

import java.util.ArrayList;

public class getAllDataActivity extends AppCompatActivity {

    database db;

    private RecyclerView recyclerview;
    private Myadapter myadaptor;
    private ArrayList mylist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerview=findViewById(R.id.mrecycler_view);
        db = new database(this);
        mylist=new ArrayList<>();

         mylist=db.getAllData();


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




    }
}
