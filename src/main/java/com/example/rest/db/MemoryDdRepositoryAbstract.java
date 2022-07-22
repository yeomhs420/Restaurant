package com.example.rest.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryDdRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryifs<T>{

    private final List<T> db = new ArrayList<>();
    private int index = 0;


    @Override
    public Optional<T> findById(int index) {

        return db.stream().filter(it -> it.getIndex() == index).findFirst();    // MemoryDbEntity 리스트의 해당 index 를 갖는 데이터를 찾아 첫번째 데이터 리턴
    }

    @Override
    public T save(T entity) {

        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();
        // 해당 entity 의 index 를 갖는 것이 있는지 db 로부터 가져옴


        if(optionalEntity.isPresent()){
            int preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            deleteById(preIndex);
            db.add(entity);

            return entity;
        } // db에 이미 데이터가 있는 경우

        else{
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }   // 없는 경우


    }

    @Override
    public void deleteById(int index) {
        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();

        if(optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }
    }


    @Override
    public List<T> findall() {
        return db;
    }
}
