package com.timesheet.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vitaliy on 28.04.15.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.timesheet.repository")
public class PersistenceConfig {

    @Bean(name = "dataSource", destroyMethod = "close")
    public ComboPooledDataSource dataSource(
            @Value("${jdbc.driverClassName}") String driver,
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.username}") String username,
            @Value("${jdbc.password}") String password,
            @Value("${jdbc.acquireIncrement}") Integer acquireIncrement,
            @Value("${jdbc.idleConnectionTestPeriod}") Integer idleConnectionTestPeriod,
            @Value("${jdbc.maxPoolSize}") Integer maxPoolSize,
            @Value("${jdbc.maxStatements}") Integer maxStatements,
            @Value("${jdbc.minPoolSize}") Integer minPoolSize
            ) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMaxStatements(maxStatements);
        dataSource.setMinPoolSize(minPoolSize);
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Value("${hibernate.cache.use_second_level_cache}") String cacheUseSecondLevel,
            @Value("${hibernate.cache.use_query_cache}") String cacheUseQueryCache,
            @Value("${hibernate.cache.provider_class}") String cacheProviderClass,
            @Value("${hibernate.cache.region.factory_class}") String cacheRegionFactoryClass,
            @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl,
            @Value("${hibernate.show_sql}") String showSql,
            @Value("${hibernate.jdbc.batch_size}") String batchSize,
            @Value("${hibernate.jdbc.fetch_size}") String fetchSize,
            @Value("${hibernate.max_fetch_depth}") String maxFetchDepth,
            @Value("${domainPackagesToScan}") String domain,
            ComboPooledDataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(domain);
        entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.cache.use_second_level_cache", cacheUseSecondLevel);
        jpaProperties.put("hibernate.cache.use_query_cache", cacheUseQueryCache);
        jpaProperties.put("hibernate.cache.provider_class", cacheProviderClass);
        jpaProperties.put("hibernate.cache.region.factory_class", cacheRegionFactoryClass);
        jpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        jpaProperties.put("hibernate.show_sql", showSql);
        jpaProperties.put("hibernate.jdbc.batch_size", batchSize);
        jpaProperties.put("hibernate.jdbc.fetch_size", fetchSize);
        jpaProperties.put("hibernate.max_fetch_depth", maxFetchDepth);
        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

        return entityManagerFactoryBean;
    }
}
