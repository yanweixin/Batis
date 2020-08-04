package com.batis.application.repository.management;

import com.batis.application.entity.management.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAll();

    Optional<User> findById(Long id);

    boolean existsById(Long aLong);
}
