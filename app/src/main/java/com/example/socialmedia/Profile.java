package com.example.socialmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.socialmedia.model2.DatabaseHelper;
import com.example.socialmedia.model2.Post;

import java.util.List;

public class Profile extends AppCompatActivity {
    String email;
    List<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_home);
         email=getIntent().getStringExtra("emailhome");

        DatabaseHelper db=new DatabaseHelper(this);
        posts=db.getallposts();


        PostProfileAdapter adapter=new PostProfileAdapter(this,R.layout.post_profile,posts);
        ListView listView=findViewById(R.id.list_profil);

        listView.setAdapter(adapter);



    }
}