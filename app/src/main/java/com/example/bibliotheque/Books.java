package com.example.bibliotheque;

public class Books {
    private String _Titre,_Description;
    private  int _image;
    public Books(String Titre,int image,String Descrption)
    {
        this._Titre=Titre;
        this._image=image;
        this._Description=Descrption;
    }

    public String get_Titre() {
        return _Titre;
    }

    public int get_image() {
        return _image;
    }

    public String get_Description() {
        return _Description;
    }

    public void set_Titre(String _Titre) {
        this._Titre = _Titre;
    }

    public void set_image(int _image) {
        this._image = _image;
    }

    public void set_Description(String _Description) {
        this._Description = _Description;
    }
}
