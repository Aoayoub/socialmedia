package com.example.socialmedia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.socialmedia.model2.DatabaseHelper;
import com.example.socialmedia.model2.Post;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class AddPost extends AppCompatActivity {
ImageView add_photo;
Button add_data;
Bitmap bitmap;
EditText desc;
String email_user;
DatabaseHelper db;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (  resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                InputStream input = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(input);
                add_photo.setImageBitmap(bitmap);
                add_photo.setImageURI(uri);
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));
        email_user=getIntent().getStringExtra("emailhome");
        if(email_user != null)
            Toast.makeText(this,"not null",Toast.LENGTH_LONG).show();
        db=new DatabaseHelper(this);
        add_photo=findViewById(R.id.Add_image);
        add_data=findViewById(R.id.Add);
        desc=findViewById(R.id.write_text);
        add_photo.setOnClickListener(
           v->{Intent inetent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);startActivityForResult(inetent,100);});

        add_data.setOnClickListener(v->{
            if(desc.getText().toString().isEmpty() || bitmap==null)
                Toast.makeText(this,"empty post",Toast.LENGTH_LONG).show();
            else
            {
                String description=desc.getText().toString();
                byte[] imageblop=Utility.convertBitmap2BLOB(bitmap);
                db.addPost(new Post(email_user,imageblop,description));
                Toast.makeText(this,"succes",Toast.LENGTH_LONG).show();
                desc.setText("");
                bitmap=null;
                finish();

            }


        });



    }



}