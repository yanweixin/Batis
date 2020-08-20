package com.batis.application.database.repository.jpa;

import com.batis.library.reflect.ReflectUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CustomQuery<T, ID> extends JpaRepository<T, ID> {

    @SuppressWarnings("unchecked")
    default <S extends T> S findOrCreate(S entity) {
        if (entity == null) {
            return null;
        }

        List<String> ignorePaths = new ArrayList();
        for (Field field : ReflectUtils.getAllFields(entity)) {
            Id id = field.getAnnotation(Id.class);
            Column column = field.getAnnotation(Column.class);
            if (id == null && (column == null || !column.unique())) {
                ignorePaths.add(field.getName());
            }
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withIgnoreNullValues()
                .withIgnorePaths(ignorePaths.toArray(new String[0]));
        Example<S> example = Example.of(entity, matcher);

        Optional<S> optionalS = findOne(example);
        if (optionalS.isEmpty()) {
            save(entity);
        } else {
            entity = optionalS.get();
        }
        return entity;
    }
}
