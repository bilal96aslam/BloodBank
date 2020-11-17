package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bloodbank.database.database;

public class SignupActivity extends AppCompatActivity {

    private EditText name_var,email_var,pass_var,cf_var;
    private Button signup;
    private ImageView img;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new database(SignupActivity.this);

        name_var=(EditText) findViewById(R.id.name_et);
        email_var=(EditText) findViewById(R.id.email_et);
        pass_var=(EditText) findViewById(R.id.password_et);
        cf_var=(EditText) findViewById(R.id.confirm_pass_et);
        img=(ImageView) findViewById(R.id.backImg);
        signup=(Button)findViewById(R.id.sign_up_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new database(SignupActivity.this);

                String name,email,password,cnfpass;

                name= name_var.getText().toString();
                email=email_var.getText().toString();
                password=pass_var.getText().toString();
                cnfpass=cf_var.getText().toString();

                if (email.equals(" ") || name.equals(" ")|| password.equals(" ") || cnfpass.equals(" ") ){

                    Toast.makeText(SignupActivity.this,"Fields are Empty",Toast.LENGTH_LONG).show();
                }

                else if (!password.equals(cnfpass)){
                    pass_var.setText(" ");
                    cf_var.setText(" ");
                    Toast.makeText(SignupActivity.this,"Password are not equal",Toast.LENGTH_LONG).show();
                }
                else{

                    if (password.equals(cnfpass)){
                        boolean result=db.setSignup(name,email,password,cnfpass);
                        if (result==true){
                            Toast.makeText(SignupActivity.this,"User Register Successfully!",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            name_var.setText(" ");
                            email_var.setText(" ");
                            pass_var.setText(" ");
                            cf_var.setText(" ");
                            Toast.makeText(SignupActivity.this,"User Not Register",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
