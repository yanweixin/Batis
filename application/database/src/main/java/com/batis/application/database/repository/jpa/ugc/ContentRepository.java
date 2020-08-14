package com.batis.application.database.repository.jpa.ugc;

import com.batis.application.database.entity.ugc.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
