package com.batis.application.database.repository.jpa.ugc;

import com.batis.application.database.entity.ugc.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
