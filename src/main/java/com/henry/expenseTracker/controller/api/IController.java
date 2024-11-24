package com.henry.expenseTracker.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IController<T> {

    default ResponseEntity<List<T>> findAll() {return null;};
    default ResponseEntity<List<T>> findAll(Integer id){return null;};
    ResponseEntity<T> findByPk(Integer id);
    ResponseEntity<String> delete(Integer id);
    ResponseEntity<T> update(T t);
    ResponseEntity<T> save(T t);

}
