package com.example.socialmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia.model2.DatabaseHelper;

import java.util.Locale;
import java.util.Objects;

public class Login extends AppCompatActivity {
Button button_Login;
TextView Add_Account;
EditText email,password;
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.eml_em);
        password=findViewById(R.id.psw);
        button_Login=findViewById(R.id.button2);
        Add_Account = findViewById(R.id.Createacc);
        db=new DatabaseHelper(this);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));

        View.OnClickListener exp=v->{
            if(isoneofthefieldsempty())
                Toast.makeText(this,"empty field",Toast.LENGTH_LONG).show();
            else {
                String mail=email.getText().toString().toLowerCase(Locale.ROOT);
                String Pass=password.getText().toString();

                if(checkuser(mail,Pass))
                {
                    //home
                    Toast.makeText(this,"succes",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this,home.class));

                    //startActivity(new Intent(this,Home.class));
                    finish();
                    //Toast.makeText(this,"succes",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(this,"email or passwoed incorrcet",Toast.LENGTH_LONG).show();
            }


        };
      button_Login.setOnClickListener(exp);
        Add_Account.setOnClickListener(v -> {Intent intent =new Intent(this,MainActivity.class);startActivity(intent);});
    }
    public boolean isoneofthefieldsempty(){
        return email.getText().toString().trim().isEmpty() || password.getText().toString().isEmpty();
    }
    public boolean checkuser(String email,String password){


        return db.checkUser(email,password);
    }
   /* public boolean checkuser(User user){
        UserHelper db=new UserHelper(getApplicationContext());
        return db.getitem(user)!=null;
    }*/
}