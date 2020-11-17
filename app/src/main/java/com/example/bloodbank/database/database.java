package com.example.bloodbank.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bloodbank.LoginActivity;
import com.example.bloodbank.MainActivity;
import com.example.bloodbank.model.Donor;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    //private  static final String DATABASE_NAME = "BloodBank.db";
    private  static final String DATABASE_NAME = "BloodBankDB.db";
    private static final String TABLE_SIGNUP = "User_Table";
    private static final String TABLE_DONORS = "Donors";
    ContentValues contentValues;

    public database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_SIGNUP + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,FULL_NAME TEXT,EMAIL VARCHAR(255),PASSWORD VARCHAR(255),CONFIRMPASSWORD VARCHAR(255))" );
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_DONORS + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,FULL_NAME TEXT,CITY TEXT,BLOODGROUP TEXT,CELLNO INTEGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_SIGNUP);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_DONORS);
        onCreate(sqLiteDatabase);
    }

    public boolean login(String email,String password){
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SIGNUP+" WHERE EMAIL=? AND PASSWORD=?",new String[]{email,password});

        if(cursor.getCount()>0 ){
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean setSignup(String fullName,String email,String password,String confirmPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("FULL_NAME", fullName);
        contentValues.put("EMAIL",email);
        contentValues.put("PASSWORD",password);
        contentValues.put("CONFIRMPASSWORD",confirmPassword);

        long result = db.insert(TABLE_SIGNUP,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public  boolean insertDonorForm(String Name,String City,String bloodGroup,int cellNo)
    {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues=new ContentValues(  );
        contentValues.put("FULL_NAME",Name);
        contentValues.put("CITY",City);
        contentValues.put("BLOODGROUP",bloodGroup);
        contentValues.put("CELLNO",cellNo);
        long result = db.insert(TABLE_DONORS,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Donor> getSearchData(String City, String Group)
    {

        ArrayList<Donor> arrayList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_DONORS+" WHERE CITY=? AND BLOODGROUP=?",new String[]{City,Group});

        while (cursor.moveToNext()) {

            String name = cursor.getString(1);
            String city = cursor.getString(2);
            String group =cursor.getString(3);
            int cell= cursor.getInt(4);

            Donor donorData = new Donor(name,group,city,cell);
            arrayList.add(donorData);

        }
        cursor.close();

        return arrayList;
    }

    public ArrayList getAllData()
    {

        ArrayList<Donor> arrayList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+TABLE_DONORS,null);

        while (cursor.moveToNext()) {

            String name = cursor.getString(1);
            String city = cursor.getString(2);
            String group =cursor.getString(3);
            int cell= cursor.getInt(4);

            Donor donorData = new Donor(name,group,city,cell);
            arrayList.add(donorData);

        }
        cursor.close();

        return arrayList;
    }


}
