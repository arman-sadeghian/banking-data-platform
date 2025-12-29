package com.sadeghian.banking.bootstrap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "com.sadeghian.banking.infrastructure.elasticsearch.repository"
)
public class ElasticsearchConfiguration {
}
