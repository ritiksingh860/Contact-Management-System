package com.springboot.cms.service;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://dpg-ch2dtvlgk4qarqggkhbg-a.oregon-postgres.render.com:5432/cmsdb_qpft")
                .username("root")
                .password("YydS2m4SIzcX963vuJQxUgnjeGCeVcOR")
                .build();
    }
}
