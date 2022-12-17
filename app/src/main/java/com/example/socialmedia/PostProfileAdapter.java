package com.example.socialmedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia.model2.DatabaseHelper;
import com.example.socialmedia.model2.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class PostProfileAdapter extends ArrayAdapter<Post> {

    List<Post> posts=new ArrayList<>();

    public PostProfileAdapter(Context context,int ressource,List<Post> posts){
        super(context,ressource,posts);
    }



    public View getView(int position, View view, ViewGroup parent){

        DatabaseHelper db=new DatabaseHelper(getContext());

            Post post =getItem(position);
        View item = view;
        item = LayoutInflater.from(getContext()).inflate(R.layout.post_profile, parent, false);
            ImageView post_pic = item.findViewById(R.id.pic_profile);
            TextView description = item.findViewById(R.id.desc_profile);
            post_pic.setImageBitmap(Utility.convertBLOB2Bitmap(post.getImage()));
            description.setText(post.getDescription());



        return item;
    }


}
