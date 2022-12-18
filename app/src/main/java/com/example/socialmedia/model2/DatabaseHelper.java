package com.example.socialmedia.model2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "socialmedia.db";

    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";

    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    //for posts
    private final static String POST_USER="post";
    private final static  String ID_POST="id_post";
    private final static String EMAIL_POST="email_user";
    private final static String DESCRIPTION="description";
    private final static String IMAGE="image";


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_EMAIL + " TEXT UNIQUE," + COLUMN_USER_PASSWORD + " TEXT" + ")";
    private String CREATE_POST_TABLE=" CREATE TABLE "+POST_USER+"("+ID_POST+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EMAIL_POST+" TEXT NOT NULL, "+DESCRIPTION+" TEXT,"+IMAGE+" BLOB );";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_POST_TABLE="DROP TABLE IF EXISTS "+POST_USER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_POST_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_POST_TABLE);
        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,

                COLUMN_USER_PASSWORD
        };
        // sorting orders

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_ID))));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_EMAIL+ " = ?",
                new String[]{String.valueOf(user.getEmail())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ? ;" ;

        // selection arguments
        String[] selectionArgs = {email};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();

        return cursorCount>0;

    }
    public void addPost(Post post){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(EMAIL_POST,post.getEmail_user());
        values.put(DESCRIPTION,post.getDescription());
        values.put(IMAGE,post.getImage());
        db.insert(POST_USER,null,values);
        db.close();

    }
    public List<Post> getallposts(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<Post> posts=new ArrayList<>();
        Post post=new Post();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ POST_USER+" ORDER BY "+ID_POST+" DESC",null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    String email_p=cursor.getString(cursor.getColumnIndexOrThrow(EMAIL_POST));
                    byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(IMAGE));
                    String desc_p=cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
                    posts.add(new Post(email_p,image,desc_p));
                }while(cursor.moveToNext());

            }

        }
        db.close();
        return posts;
    }

    public List<Post> getallposts(String email){
        List <Post> posts=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        //Cursor cursor=db.query(POST_USER,null,EMAIL_POST+" = ?",new String[]{email},null,null,ID_POST+" DESC");
        Cursor cursor=db.rawQuery(String.format("select * from %s where %s=\"%s\" order by %s desc;",POST_USER,EMAIL_POST,email,ID_POST),null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    Integer id= cursor.getInt(cursor.getColumnIndexOrThrow(ID_POST));
                    String mail=cursor.getString(cursor.getColumnIndexOrThrow(EMAIL_POST));
                    byte[] image= cursor.getBlob(cursor.getColumnIndexOrThrow(IMAGE));
                    String Desc=cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
                    posts.add(new Post(id,mail,image,Desc));
                }while(cursor.moveToNext());
            }
        }
        db.close();

        return posts;
    }

    public void delete(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(POST_USER,ID_POST+" = ?",new String[]{String.valueOf(id)});
        db.close();

    }
}
