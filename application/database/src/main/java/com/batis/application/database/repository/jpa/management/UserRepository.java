package com.batis.application.database.repository.jpa.management;

import com.batis.application.database.entity.management.User;
import com.batis.application.database.model.UserCredentials;
import com.batis.application.database.repository.jpa.CustomQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CustomQuery<User, Long> {

    @Query(name = "User.findByUsername", nativeQuery = true)
    Optional<User> findByUsername(String username);

    List<UserCredentials> findAllByEnabled(boolean enabled);

    Optional<UserCredentials> findUserCredentialsByUsername(String username);

    List<User> findByDisplayName(String displayName);

    boolean existsByUsername(String username);
}
