package com.example.bibliotheque.model;

public class Exemplaire {
    private  Integer id_book;
    private Integer id_exempl;

    public Exemplaire(Integer id_exempl,Integer id_book){
        this.id_book=id_book;
        this.id_exempl=id_exempl;
    }
    public Integer getId_exempl() {
        return id_exempl;
    }

    public void setId_exempl(Integer id_exempl) {
        this.id_exempl = id_exempl;
    }


    public Integer getId_book() {
        return id_book;
    }

    public void setId_book(Integer id_book) {
        this.id_book = id_book;
    }



}
