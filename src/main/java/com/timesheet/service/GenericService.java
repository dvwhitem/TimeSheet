package com.timesheet.service;


import java.io.Serializable;
import java.util.List;

/**
 * Created by vitaliy on 15.04.15.
 */
public interface GenericService<T, ID extends Serializable> {

    List<T> findAll();

    T findById(ID id);

    T save(T entity);

    void delete(T entity);
}
