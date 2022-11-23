package com.acko.htlmgenerator.config;

import com.acko.htlmgenerator.pojo.DBCredential;
import com.acko.htlmgenerator.secrets.ISecretManager;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

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
