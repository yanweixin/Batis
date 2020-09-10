package com.batis.application.service.system;

import com.batis.application.database.entity.system.BlockList;
import com.batis.application.database.repository.jpa.system.BlockListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BlockListService {
    private final Map<String, List<Pattern>> patterns;
    BlockListRepository blockListRepository;

    @Autowired
    public BlockListService(BlockListRepository blockListRepository) {
        this.patterns = new HashMap<>();
        this.blockListRepository = blockListRepository;
    }

    public List<BlockList> findAllByType(String type) {
        return blockListRepository.findAllByType(type);
    }

    public BlockList addBlockList(BlockList blockList) {
        if (blockList.isPattern()) {
            addPattern(blockList.getType(), blockList.getValue());
        }
        return blockListRepository.save(blockList);
    }

    @PostConstruct
    public void setPatterns() {
        final Stream<BlockList> blockLists = blockListRepository.findAll().stream()
                .filter(BlockList::isPattern);
        final Stream<String> types = blockLists
                .map(BlockList::getType)
                .distinct();
        types.forEach(
                type -> patterns.put(type, blockLists
                        .filter(it -> it.getType().equals(type))
                        .map(blockList -> Pattern.compile(blockList.getValue()))
                        .collect(Collectors.toList())
                )
        );
    }

    @Bean("blockList")
    public Map<String, List<Pattern>> getPatterns() {
        return patterns;
    }

    private void addPattern(String type, String value) {
        Pattern pattern = Pattern.compile(value);
        patterns.get(type).add(pattern);
    }
}
