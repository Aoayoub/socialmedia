package com.example.bibliotheque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliotheque.model.User;
import com.example.bibliotheque.model.UserHelper;

import java.util.Locale;

public class Login extends AppCompatActivity {
Button button_Login;
EditText email,password;
UserHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.eml_em);
        password=findViewById(R.id.psw);
        button_Login=findViewById(R.id.button2);
        db=new UserHelper(this);

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

                    startActivity(new Intent(this,Home_app.class));
                    finish();
                    //Toast.makeText(this,"succes",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(this,"email or passwoed incorrcet",Toast.LENGTH_LONG).show();
            }


        };
      button_Login.setOnClickListener(exp);

    }
    public boolean isoneofthefieldsempty(){
        return email.getText().toString().trim().isEmpty() || password.getText().toString().isEmpty();
    }
    public boolean checkuser(String email,String password){


        return db.getitem(new User(email, password)) != null;
    }
   /* public boolean checkuser(User user){
        UserHelper db=new UserHelper(getApplicationContext());
        return db.getitem(user)!=null;
    }*/
}