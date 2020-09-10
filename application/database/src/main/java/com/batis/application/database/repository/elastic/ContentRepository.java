package com.batis.application.database.repository.elastic;

import com.batis.application.database.entity.ugc.Content;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ContentRepository extends ElasticsearchRepository<Content, Long> {
    List<Content> findAllByTitleContainsOrBodyContains(String title, String body);

    @Query("{ \"multi_match\": { \"query\": \"?0\", \"operator\": \"or\", \"auto_generate_synonyms_phrase_query\": \"true\", \"fields\": [ \"body\", \"title\" ] } }")
    List<Content> findAllByKeywords(String keywords);
}
