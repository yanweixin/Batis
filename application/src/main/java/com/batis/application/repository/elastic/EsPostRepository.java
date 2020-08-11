package com.batis.application.repository.elastic;

import com.batis.application.entity.ugc.Post;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsPostRepository extends ElasticsearchRepository<Post, Long> {
    List<Post> findAllByContentTitleContainsOrContentBodyContains(String contentTitle, String contentBody);

    @Query("{ \"multi_match\": { \"query\": \"?0\", \"operator\": \"or\", \"auto_generate_synonyms_phrase_query\": \"true\", \"fields\": [ \"content.body\", \"content.title\" ] } }")
    List<Post> findAllByKeywords(String keywords);
}
