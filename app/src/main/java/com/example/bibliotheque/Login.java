package com.example.bibliotheque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
Button button_Login;
EditText email,password;
Database DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.eml_em);
        password=findViewById(R.id.psw);
        button_Login=findViewById(R.id.button2);
      button_Login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String eml=email.getText().toString();
              String psw=password.getText().toString();
              if(eml.isEmpty()||psw.isEmpty())
              {
                  Toast.makeText(Login.this,"enter all fields",Toast.LENGTH_LONG).show();
              }
              else
              {
                  Boolean checkmarks=DB.Checkempass(eml,psw);
                  if(checkmarks==true)
                  {

                      Intent intent = new Intent(getApplicationContext(),Home1.class);
                      startActivity(intent);
                  }
                  else
                  {
                      Toast.makeText(Login.this,"email or password incorrect",Toast.LENGTH_LONG).show();
                  }
              }


          }
      });

    }
}