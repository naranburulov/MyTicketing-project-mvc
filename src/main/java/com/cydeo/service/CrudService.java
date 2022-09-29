package com.cydeo.service;



import java.util.List;

public interface CrudService <T, ID>{

    T save(T user);

    T findById(ID userName);

    List<T> findAll();

    void deleteById(ID userName);





}
