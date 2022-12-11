package com.example.bibliotheque.model;

import java.time.LocalDate;

public class emprunte {
    public Integer id_user;
    private Integer id_emprunte;

    private LocalDate date_emprunte;
    private Integer id_exemplaire;
    public emprunte(Integer id_user,Integer id_emprunte,Integer id_book,LocalDate date_emprunte){
        this.id_emprunte=id_emprunte;
        this.id_user=id_user;
        this.id_exemplaire=id_book;
        this.date_emprunte=date_emprunte;
    }
    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }


    public Integer getId_emprunte() {
        return id_emprunte;
    }

    public void setId_emprunte(Integer id_emprunte) {
        this.id_emprunte = id_emprunte;
    }

    public LocalDate getDate_emprunte() {
        return date_emprunte;
    }

    public void setDate_emprunte(LocalDate date_emprunte) {
        this.date_emprunte = date_emprunte;
    }


    public Integer getId_book() {
        return id_exemplaire;
    }

    public void setId_book(Integer id_book) {
        this.id_exemplaire = id_book;
    }



}
