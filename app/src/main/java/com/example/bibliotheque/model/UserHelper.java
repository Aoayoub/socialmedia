package com.example.bibliotheque.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends SQLiteOpenHelper implements CrudDao<User> {
    private final static String Db_Name="Biblio_db";
    private final static int version=1;
    private final static  String Table_Name="User";
    private final static  String Id_user="id_user";
    private final static String email="email";
    private final static String password="password";
    public UserHelper(Context cntxt){
        super(cntxt,Db_Name,null,version);
    }
    public void onCreate(@NonNull SQLiteDatabase db) {
        String CREATE_User = "CREATE TABLE " + Table_Name + "( "
                + Id_user + " INTEGER PRIMARY KEY, " + email + "  varchar(60) UNIQUE NOT NULL,"
                + password + " varchar(20)" + ")";
        db.execSQL(CREATE_User);
        db.close();
    }
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);

        // Create tables again
        onCreate(db);
    }
   public long additem(@NonNull User o){
        SQLiteDatabase db=this.getWritableDatabase();
       ContentValues values=new ContentValues();

       values.put(email,o.getEmail());
       values.put(password,o.getPassword());

       long res= db.insert(Table_Name,null,values);
        db.close();
        return res;
    }

    @Override
    public void updateitem(@NonNull User obj) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
         values.put(password,obj.getPassword());
         db.update(Table_Name,values,Id_user +"= ?",new String[] { String.valueOf(obj.getId_user()) });
         db.close();
    }
    public List<User> getAllItem(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor iter=db.rawQuery("SELECT * FROM  " + Table_Name,null);
        List<User> list=new ArrayList<>();
        if( iter !=null){
            if(iter.moveToFirst())
            {
                do{
                    int id=iter.getInt(iter.getColumnIndexOrThrow("0"));
                    String email=iter.getString(iter.getColumnIndexOrThrow("1"));
                    String password=iter.getString(iter.getColumnIndexOrThrow("2"));
                    list.add(new User(id,email,password));
                }while(iter.moveToNext());
            }
        }
        db.close();

        return list;
    }

    @Override
    public User getitem(@NonNull User obj){
       String email_t=obj.getEmail();
       String password_t=obj.getPassword();
       SQLiteDatabase db=this.getReadableDatabase();
       Cursor iter=db.query(Table_Name,null,"email like '?' AND password '?'",new String[]{email_t,password_t},null,null,null,null);
       //Cursor iter=db.rawQuery("select form User where email like '"+email_t+"' and passwoed like '"+password_t+"';",null);
        if(iter !=null){
                iter.moveToFirst();
                User user=new User(iter.getString(iter.getColumnIndexOrThrow(email)),iter.getString(iter.getColumnIndexOrThrow(password)));
                db.close();
                        return user;
            }


           db.close();
            return null;
    }

    @Override
    public void delete(@NonNull User obj) {
        //for admin
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(Table_Name,"email = ?",new String[]{obj.getEmail()});
        db.close();
    }
    public List<String> getAllemails(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<String> emails=new ArrayList<>();
        String query="select "+email+" from "+Table_Name;
        Cursor iter=db.rawQuery(query,null);
        if(iter!=null){
            if(iter.moveToFirst()){
                do{
                    emails.add(iter.getString(iter.getColumnIndexOrThrow(email)));
                }while(iter.moveToNext());
            }
        }
        db.close();
        return emails;
    }
}
