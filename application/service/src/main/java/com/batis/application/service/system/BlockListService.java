package com.batis.application.service.system;

import com.batis.application.database.entity.system.BlockList;
import com.batis.application.database.repository.jpa.system.BlockListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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

//    public BlockList addBlockList(BlockList blockList) {
//        if (blockList.isPattern()) {
//            addPattern(blockList.getType(), blockList.getValue());
//        }
//        return blockListRepository.save(blockList);
//    }

    public List<BlockList> addBlockLists(List<BlockList> blockLists) {
        List<BlockList> blocks = blockLists.stream()
                .filter(blockList -> blockListRepository.findByTypeAndValue(blockList.getType(), blockList.getValue()).isEmpty())
                .collect(Collectors.toList());
        if (blocks.size() == 0) {
            return null;
        }
        blocks.stream()
                .filter(BlockList::isPattern)
                .forEach(blockList -> addPattern(blockList.getType(), blockList.getValue()));
        return blockListRepository.saveAll(blocks);
    }

    @PostConstruct
    public void setPatterns() {
        final List<BlockList> blockLists = blockListRepository.findAllByPattern(true);
        final Stream<String> types = blockLists
                .stream()
                .map(BlockList::getType)
                .distinct();
        types.forEach(
                type -> patterns.put(type, blockLists
                        .stream()
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

    private synchronized void addPattern(String type, String value) {
        Pattern pattern = Pattern.compile(value);
        if (this.patterns.get(type) == null) {
            List<Pattern> patterns = new ArrayList<>();
            patterns.add(pattern);
            this.patterns.put(type, patterns);
        } else {
            this.patterns.get(type).add(pattern);
        }
    }
}
