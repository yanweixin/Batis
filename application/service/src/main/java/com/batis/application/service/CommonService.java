package com.batis.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonService<T> {

    T findById(Long id);

    Page<T> findAll(Pageable pageable);

    T save(T t);

    T updateById(Long id, T t);

    List<T> saveAll(List<T> ts);

    int deleteById(Long id);
}
