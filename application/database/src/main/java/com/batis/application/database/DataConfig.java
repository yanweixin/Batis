package com.batis.application.database;

import com.batis.application.database.entity.audit.StringIdAudit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
//@EnableJpaRepositories(basePackages = "com.batis.application.database.repository.jpa")
//@EnableElasticsearchRepositories(basePackages = "com.batis.application.database.repository.elastic")
//namedQueriesLocation = "classpath:META-INF/elasticsearch-named-queries.properties"
@EnableJpaRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JpaRepository.class))
@EnableElasticsearchRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticsearchRepository.class))
@EnableMongoRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MongoRepository.class))
@EnableMongoAuditing
public class DataConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new StringIdAudit();
    }
}
