package com.henry.expenseTracker.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IController<T> {

    default ResponseEntity<List<T>> findAll() {return ResponseEntity.ok(null);};
    default ResponseEntity<List<T>> findAll(Long id){return ResponseEntity.ok(null);};
    ResponseEntity<T> findById(Long id);
    ResponseEntity<String> delete(Long id);
    ResponseEntity<String> update(T t);
    ResponseEntity<T> save(T t);

}
