package com.multidb.configuration.datasources;

import com.multidb.repository.employee.entity.SalariesEntity;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.multidb.repository.employee",
            entityManagerFactoryRef = "employeeEntityManagerFactory",
            transactionManagerRef= "employeeTransactionManager")
public class EmployeeConfig {

    @Bean(name = "employeeProperties")
    @ConfigurationProperties("app.datasource.employee")
    public DataSourceProperties getProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "employeeDataSource")
    @ConfigurationProperties("app.datasource.employee.configuration")
    public DataSource getDataSource() {
        return getProperties()
                .initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(getDataSource())
                .packages(SalariesEntity.class)
                .build();
    }

    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager getTransactionManager(
            final @Qualifier("employeeEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
