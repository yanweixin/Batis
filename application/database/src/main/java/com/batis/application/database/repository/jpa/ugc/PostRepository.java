package com.batis.application.database.repository.jpa.ugc;

import com.batis.application.database.entity.ugc.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByContentId(Long id);
}
