package com.example.socialmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.socialmedia.model2.DatabaseHelper;
import com.example.socialmedia.model2.Post;

import java.util.List;
import java.util.Objects;

public class home extends AppCompatActivity {
     String email_previous;
     DatabaseHelper db;
     List<Post> posts;
     PostHomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));
        email_previous=getIntent().getStringExtra("email");
        db=new DatabaseHelper(this);
         posts=db.getallposts();
        Integer sz=posts.size();
        String size =sz.toString();
        Toast.makeText(this, size, Toast.LENGTH_SHORT).show();
        adapter=new PostHomeAdapter(this,R.layout.post_home,posts);
        ListView listView=findViewById(R.id.home_listview);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        email_previous=getIntent().getStringExtra("email");
        switch(item.getItemId())
        {
            case R.id.home1:
                Intent intent = new Intent(this,Profile.class);
                intent.putExtra("emailhome",email_previous);

                startActivity(intent);
                return true;
            case R.id.plus:
                Intent intent1=new Intent(this,AddPost.class);
                intent1.putExtra("emailhome",email_previous);
                startActivity(intent1);

                return true;
            case R.id.logout:
                Intent intent2= new Intent(this,Login.class);
                startActivity(intent2);

                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }
}