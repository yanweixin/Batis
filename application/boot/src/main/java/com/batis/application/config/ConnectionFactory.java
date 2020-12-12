package com.batis.application.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private interface Singleton {
        ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private final Properties properties;
    private final DataSource dataSource;

    private ConnectionFactory() {
        this.properties = readProperties();
        this.dataSource = getDataSource();
    }

    private synchronized Properties readProperties() {
        if (properties == null) {
            YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
            factoryBean.setResources(new ClassPathResource("application.yml"));
            return factoryBean.getObject();
        }
        return properties;
    }

    private DataSource getDataSource() {
        String url = System.getenv().getOrDefault("SPRING_DATASOURCE_URL", properties.getProperty("spring.datasource.url"));
        String driverClassName = properties.getProperty("spring.datasource.driver-class-name");
        return DataSourceBuilder
                .create()
                .driverClassName(driverClassName)
                .url(url.substring(0, url.lastIndexOf('/')) + "/log")
                .username("logs")
                .password("logs")
                .build();
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}

