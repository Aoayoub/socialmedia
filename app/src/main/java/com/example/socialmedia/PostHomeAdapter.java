package com.example.socialmedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.socialmedia.model2.DatabaseHelper;
import com.example.socialmedia.model2.Post;

import java.util.ArrayList;
import java.util.List;

public class PostHomeAdapter extends ArrayAdapter<Post> {
    private List<Post> posts=new ArrayList<>();


    PostHomeAdapter(@NonNull Context context,int ressource,List<Post> posts){
        super(context,ressource,posts);

    }
    @NonNull
    @Override
    public View getView(int position, View convertview,@NonNull ViewGroup parent){
        DatabaseHelper db=new DatabaseHelper(getContext());
        View item = convertview;
            item = LayoutInflater.from(getContext()).inflate(R.layout.post_home, parent, false);
        Post post=getItem(position);
        ImageView image=item.findViewById(R.id.picture);
        TextView email=item.findViewById(R.id.user);
        TextView desc=item.findViewById(R.id.description);
        image.setImageBitmap(Utility.convertBLOB2Bitmap(post.getImage()));
        email.setText(post.getEmail_user());
        desc.setText(post.getDescription());
        return item;
    }
}
