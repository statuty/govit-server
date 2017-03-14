package com.statuty.govit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan("com.statuty.govit")
@PropertySource("classpath:application.properties")
@EnableElasticsearchRepositories(basePackages = "com/statuty/govit/repository")
public class ApplicationConfiguration {

}

