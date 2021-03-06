package com.spring.boot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.spring.boot.config.props.MultipleMongoProperties;

@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.neo.repository.secondary", mongoTemplateRef = "secondaryMongoTemplate")
public class SecondaryMongoConfig {

}
