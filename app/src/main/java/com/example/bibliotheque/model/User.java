package com.example.bibliotheque.model;

import java.util.Objects;

public class User {
    private Integer id_user;
    private String email;
    private String password;
    public User(String email,String password){

        this.email=email;
        this.password=password;
    }
    public User(Integer id,String email,String password){
        this(email,password);
        this.id_user=id;
    }
    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(){
        return "id : " +getId_user()+" mail "+getEmail();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id_user.equals(user.id_user) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user, email);
    }
}
