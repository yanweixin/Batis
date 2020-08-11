package com.batis.application.repository.jpa.ugc;

import com.batis.application.entity.ugc.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
