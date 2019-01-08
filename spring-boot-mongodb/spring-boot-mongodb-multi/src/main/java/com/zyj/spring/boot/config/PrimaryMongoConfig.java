package com.zyj.spring.boot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.zyj.spring.boot.config.props.MultipleMongoProperties;

@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.neo.repository.primary", mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
}
