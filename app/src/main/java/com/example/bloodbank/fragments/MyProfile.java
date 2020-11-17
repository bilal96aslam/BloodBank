package com.example.bloodbank.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.LoginActivity;
import com.example.bloodbank.R;

import static android.content.Context.MODE_PRIVATE;

public class MyProfile extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_my_profile,container,false);
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
            Toast.makeText(getActivity(), "logout menu aaaa",Toast.LENGTH_SHORT).show();

//            SharedPreferences sharedPreferences=getSharedPreferences("checkbox",MODE_PRIVATE);
//            SharedPreferences.Editor editor=sharedPreferences.edit();
//            editor.putString("remember","false");
//            editor.apply();
//
//            finish();


        }
        return super.onOptionsItemSelected(item);
    }
}
