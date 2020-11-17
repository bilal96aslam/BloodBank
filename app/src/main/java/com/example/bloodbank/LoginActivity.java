package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.database.database;

public class LoginActivity extends AppCompatActivity {


    private EditText email_var,pass_var;
    private TextView sign_up;
    Button log_in;
    CheckBox remember_box;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_in= (Button) findViewById(R.id.login_button);

        email_var =(EditText)findViewById(R.id.email_et);
        pass_var =(EditText)findViewById(R.id.pass_et);
        sign_up=(TextView)findViewById(R.id.signUp_tv);
        remember_box=findViewById(R.id.checkbox);

       db= new database(LoginActivity.this);

       //shared preferences for checking if he's already loggen in or not
        SharedPreferences sharedPreferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox=sharedPreferences.getString("remember","");
        if(checkbox.equals("true")){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);

        }else if(checkbox.equals("false")){
            Toast.makeText(LoginActivity.this, "Please Login",Toast.LENGTH_SHORT).show();
        }

        //login
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;

                email=email_var.getText().toString();
                password=pass_var.getText().toString();

                if (email.equals(" ") ||  password.equals(" ") ){

                    Toast.makeText(LoginActivity.this,"Fields are Empty",Toast.LENGTH_LONG).show();
                }

                boolean result = db.login(email,password);

                if (result==false){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Email or Password are not correct",Toast.LENGTH_LONG).show();
                }

            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                  startActivity(intent);
            }
        });

        //remember me
        remember_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){
                    //first par is file name second is mode
                    SharedPreferences sharedPreferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Checked",Toast.LENGTH_SHORT).show();
                }
                else if(!compoundButton.isChecked()){
                    SharedPreferences sharedPreferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Unchecked",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
