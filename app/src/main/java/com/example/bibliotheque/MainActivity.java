package com.example.bibliotheque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView Sign_up;
EditText email,password,confirm_password;
 Button button_register;
 Database DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sign_up= findViewById(R.id.signup);
        button_register=findViewById(R.id.register);
        email=findViewById(R.id.emailregister);
        password=findViewById(R.id.password_register);
        confirm_password=findViewById(R.id.c_pass);
        DB=new Database(this);
        Sign_up.setOnClickListener(v->{ Intent intent=new Intent(getApplicationContext(),Login.class);startActivity(intent);});
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eml=email.getText().toString();
                String psw=password.getText().toString();
                String c_psw=confirm_password.getText().toString();
                if(eml.isEmpty()||psw.isEmpty()||c_psw.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"remplir tous les champs",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(psw.equals(c_psw))
                    {
                        Boolean checkemail= DB.Checkemail(eml);
                        if(checkemail==false)
                        {
                            Boolean insert=DB.insertData(eml,psw);
                            if(insert==true)
                            {
                                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"already exists",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"incorrect password",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}