package org.hotel.DAO;

import java.util.List;

public interface iCurd <T>{

    void insert(T obj);
    List<T> getAll();
    T getById(Long id);
    void update(T obj, Long id);
    void deleteById(Long id);

}
