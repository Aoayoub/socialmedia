package com.example.bibliotheque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table users(email TEXT primary key ,password TEXT, c_password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists users");
    }
    public Boolean insertData(String email, String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result=sqLiteDatabase.insert("users",null,contentValues);
        if(result==-1)return false;
        else return true;
    }
    public  Boolean Checkemail(String email)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("Select * from users where email=?",new String[]{email});
        if(cursor.getCount()>0)return true;
        else return false;
    }
    public Boolean Checkempass(String email,String password )
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where email=? AND password=?",new String[]{email,password});
        if(cursor.getCount()>0)return true;
        else return false;
    }
}
