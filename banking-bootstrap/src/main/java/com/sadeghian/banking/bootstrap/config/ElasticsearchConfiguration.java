package com.sadeghian.banking.bootstrap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@Profile("!local")
@EnableElasticsearchRepositories(
        basePackages = "com.sadeghian.banking.infrastructure.elasticsearch.repository"
)
public class ElasticsearchConfiguration {
}


