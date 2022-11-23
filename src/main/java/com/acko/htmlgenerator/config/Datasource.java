package com.acko.htmlgenerator.config;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Data
@Log4j2
@Configuration
public class Datasource {

    private static final String PARTNERSHIP_DB = "postgres";

    @Bean(name = "partnershipDataSourceProperties")
    @ConfigurationProperties(prefix = "coi.datasource")
    @Primary
    public DataSourceProperties getDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "partnershipDataSource")
    @Primary
    public DataSource getDataSource(@Qualifier("partnershipDataSourceProperties") final DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .username("navdeep.singh")
                .password("")
                .build();
    }
}
