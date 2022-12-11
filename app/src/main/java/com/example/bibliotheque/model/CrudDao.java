package com.example.bibliotheque.model;

import java.util.List;

public interface CrudDao <T>{
    List<T> getAllItem();
    void delete(T obj);
    T getitem(T obj);
    void updateitem(T obj);
    long additem(T ob);

}
