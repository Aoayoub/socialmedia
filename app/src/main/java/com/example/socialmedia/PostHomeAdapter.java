package com.example.socialmedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialmedia.model2.Post;

import java.util.ArrayList;
import java.util.List;

public class PostHomeAdapter extends ArrayAdapter<Post> {
    private List<Post> posts=new ArrayList<>();
    private Context context;
   private  int ressource;
    PostHomeAdapter(Context context,int ressource,List<Post> posts){
        super(context,ressource,posts);

    }
    @Override
    public View getView(int position, View convertview, ViewGroup parent){
        Post post=posts.get(position);
        View item= LayoutInflater.from(context).inflate(ressource,parent,false);
        ImageView image=item.findViewById(R.id.picture);
        TextView email=item.findViewById(R.id.user);
        TextView desc=item.findViewById(R.id.description);
        image.setImageBitmap(Utility.convertBLOB2Bitmap(post.getImage()));
        email.setText(post.getEmail_user());
        desc.setText(post.getDescription());
        return item;
    }
}
