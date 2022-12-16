package com.example.socialmedia.model2;

import android.content.Intent;

import java.time.LocalDate;

public final  class Post {
    private Integer Id_post;
    private String email_user;
    private byte[] image;
    private String Description;
    public Post(Integer id_post,String email_user,byte[] image,String Description)
    {
        this.Id_post=id_post;
        this.email_user=email_user;
        this.image=image;
        this.Description=Description;


    }
   public  Post(String email_user,byte[] image,String Description)
    {
        this.email_user=email_user;
        this.image=image;
        this.Description=Description;


    }
    public Post(){

    }
    public Integer getId_post() {
        return Id_post;
    }

    public void setId_post(Integer id_post) {
        Id_post = id_post;
    }


    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }





}
