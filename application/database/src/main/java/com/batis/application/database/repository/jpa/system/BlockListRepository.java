package com.batis.application.database.repository.jpa.system;

import com.batis.application.database.entity.system.BlockList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockListRepository extends JpaRepository<BlockList, Long> {
    List<BlockList> findAllByType(String type);
}
