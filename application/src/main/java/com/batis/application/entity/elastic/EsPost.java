package com.batis.application.entity.elastic;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "post")
public class EsPost {
}
