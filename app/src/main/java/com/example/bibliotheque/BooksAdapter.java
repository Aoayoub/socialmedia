package com.example.bibliotheque;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<Books> {
    private Context _context;
    private int _resource;
    public BooksAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Books> objects) {
        super(context, resource, objects);
        this._context=context;
        this._resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater= LayoutInflater.from(_context);
        convertView = layoutInflater.inflate(_resource,parent,false);
        ImageView imageView=convertView.findViewById(R.id.imageView5);
        TextView txttitle = convertView.findViewById(R.id.Title);
        TextView txtdes=convertView.findViewById(R.id.description);
        imageView.setImageResource(getItem(position).get_image());
        txttitle.setText(getItem(position).get_Titre());
        txtdes.setText(getItem(position).get_Description());
        return convertView;
       }
}
