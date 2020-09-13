package com.batis.application.database.repository.jpa.system;

import com.batis.application.database.entity.system.MultiLangText;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultiLangTextRepository extends JpaRepository<MultiLangText, Long> {
    List<MultiLangText> findAllByTextId(Integer textId);
}
