package com.example.rest.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryifs<T> {  //JpaRepository<Comment, Long>의 기능들을 직접 구현

    Optional<T> findById(int index);
    T save(T entity);
    void deleteById(int index);
    List<T> findall();
}
