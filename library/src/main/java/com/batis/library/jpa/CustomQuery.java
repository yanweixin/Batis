package com.batis.library.jpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomQuery<T, ID> extends JpaRepository<T, ID> {

//    TODO: ignore non unique column using WithIgnorePaths
    default <S extends T> S findOrCreate(S entity) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreNullValues();
        Example<S> example = Example.of(entity, matcher);

        Optional<S> optionalS = findAll(example).stream().findFirst();
        if (optionalS.isEmpty()) {
            save(entity);
        } else {
            entity = optionalS.get();
        }
        return entity;
    }
}
