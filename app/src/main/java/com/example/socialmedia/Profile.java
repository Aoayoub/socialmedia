package com.example.socialmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.socialmedia.model2.DatabaseHelper;
import com.example.socialmedia.model2.Post;

import java.util.List;

public class Profile extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_home);
         email=getIntent().getStringExtra("emailhome");
        DatabaseHelper db=new DatabaseHelper(this);
        List<Post> posts=db.getallposts(email);
         PostHomeAdapter adapter=new PostHomeAdapter(this,R.layout.post_profile,posts);
        ListView listView=findViewById(R.id.list_profil);

        listView.setAdapter(adapter);


    }
}