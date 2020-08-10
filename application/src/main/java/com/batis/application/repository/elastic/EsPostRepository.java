package com.batis.application.repository.elastic;

import com.batis.application.entity.ugc.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsPostRepository extends ElasticsearchRepository<Post,Long> {

}
