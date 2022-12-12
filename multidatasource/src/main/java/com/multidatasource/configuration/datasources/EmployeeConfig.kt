package com.multidatasource.configuration.datasources

import com.multidatasource.repository.employee.entity.SalariesEntity
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.context.properties.ConfigurationProperties
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.multidatasource.repository.employee"],
    entityManagerFactoryRef = "employeeEntityManagerFactory",
    transactionManagerRef = "employeeTransactionManager"
)
open class EmployeeConfig {
    @ConfigurationProperties("app.datasource.employee")
    @Bean(name = ["employeeProperties"])
    open fun getProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @ConfigurationProperties("app.datasource.employee.configuration")
    @Bean(name = ["employeeDataSource"])
    open fun getDataSource(): DataSource {
        return getProperties()
            .initializeDataSourceBuilder()
//            .type<BasicDataSource>(BasicDataSource::class.java)
            .build()
    }

    @Bean(name = ["employeeEntityManagerFactory"])
    open fun getEntityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(getDataSource())
//            .packages(SalariesEntity::class.java)
            .build()
    }

    @Bean(name = ["employeeTransactionManager"])
    open fun getTransactionManager(
        @Qualifier("employeeEntityManagerFactory") entityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory.getObject())
    }
}