package com.example.bibliotheque;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home1 extends AppCompatActivity {
    ListView listView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(actionBarDrawerToggle.onOptionsItemSelected(item))
       {
           return true;

       }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        listView=findViewById(R.id.listView);
        ArrayList<Books> arrayList= new ArrayList<>();
        arrayList.add(new Books("Man's Search for Meaning",R.drawable.image, "A prominent Viennese psychiatrist before the war, Viktor Frankl was uniquely able to observe the way that both he and others in Auschwitz coped (or didnt) with the experience."));
        arrayList.add(new Books("Brave New World   Aldous Huxley",R.drawable.image1,"xxxxxxxxxxxx"));
        arrayList.add(new Books("1984   George Orwell",R.drawable.image2,"xxxxxxxxxxxxx"));
        arrayList.add(new Books("Road to Wigan Pier George Orwell",R.drawable.image3,"xxxxxxxxxxx"));
        BooksAdapter booksAdapter = new BooksAdapter(this,R.layout.list_item,arrayList);
        listView.setAdapter(booksAdapter);
        //drawer
        drawerLayout= findViewById(R.id.drawer);
        navigationView= findViewById(R.id.navigationview);
        actionBarDrawerToggle =new ActionBarDrawerToggle(this,drawerLayout ,R.string.open_menu,R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.nav_books:
                        Log.i("Tag","books");
                        drawerLayout.closeDrawer(GravityCompat.START);
                       break;
                    case R.id.nav_emprunte:
                        Log.i("Tag","emprunte");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_logout:
                        Log.i("Tag","logout");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent =new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                        break;
                }


                return true;
            }
        });

    }
}