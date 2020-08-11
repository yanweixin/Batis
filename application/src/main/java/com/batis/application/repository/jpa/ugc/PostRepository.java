package com.batis.application.repository.jpa.ugc;

import com.batis.application.entity.ugc.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
