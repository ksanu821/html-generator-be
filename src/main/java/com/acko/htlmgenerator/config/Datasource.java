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
//@EnableJpaRepositories(
//        basePackages = {
//                "com.acko.htlmgenerator.repositories"
//        })
public class Datasource {

//    private final ISecretManager secretManager;
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
//        DBCredential dbCredential = secretManager.getDBCredential(PARTNERSHIP_DB);
//        if (dbCredential == null) {
//            log.error("Cannot find db credentials from secrets for name {}", PARTNERSHIP_DB);
////            throw new ReportingServiceException(
////                    ErrorCode.INTERNAL_SERVER_ERROR,
////                    HttpStatus.INTERNAL_SERVER_ERROR,
////                    "Cannot find db credentials in secrets, for name {0}", PARTNERSHIP_DB
////            );
//        }
        return dataSourceProperties.initializeDataSourceBuilder()
                .username("navdeep.singh")
                .password("")
                .build();
    }

//    @Bean(name = "partnershipEntityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
//            final EntityManagerFactoryBuilder builder,
//            @Qualifier("partnershipDataSource") final DataSource dataSource) {
//        final HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
//        propertiesMap.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        propertiesMap.put("hibernate.physical_naming_strategy",
//                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        return builder.dataSource(dataSource)
//                .properties(propertiesMap)
//                .persistenceUnit("Partnership")
//                .packages("com.acko.partnership.reportingservice.models")
//                .build();
//    }

//    @Bean(name = "partnershipTransactionManager")
//    @Primary
//    public PlatformTransactionManager platformTransactionManager(
//            @Qualifier("partnershipEntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}
