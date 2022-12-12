//package com.multidatasource.configuration.datasources;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "com.multidatasource.repository.sakila",
//            entityManagerFactoryRef = "sakilaEntityManagerFactory",
//            transactionManagerRef= "sakilaTransactionManager")
//public class SakilaConfig {
//
//    @Primary
//    @Bean(name = "sakilaProperties")
//    @ConfigurationProperties("app.datasource.sakila")
//    public DataSourceProperties getProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean(name = "sakilaDataSource")
//    @ConfigurationProperties("app.datasource.sakila.configuration")
//    public DataSource getDataSource() {
//        return getProperties()
//                .initializeDataSourceBuilder()
//                .type(BasicDataSource.class)
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "sakilaEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(getDataSource())
//                .packages(RentalEntity.class)
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "sakilaTransactionManager")
//    public PlatformTransactionManager getTransactionManager(
//            final @Qualifier("sakilaEntityManagerFactory")
//            LocalContainerEntityManagerFactoryBean entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory.getObject());
//    }
//}
