package com.ducnd.chattfunn.common.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class ConfigDataSource {

    private final Environment environment;
    @Autowired
    public ConfigDataSource(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {

//        ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();
//        try {
//            dataSource.setDriverClass(environment.getRequiredProperty("spring.datasource.driver-class-name"));
//            String url = environment.getRequiredProperty("spring.datasource.url");
//            dataSource.setJdbcUrl(url);
//            String username = environment.getRequiredProperty("spring.datasource.username");
//            dataSource.setUser(username);
//            String password = environment.getRequiredProperty("spring.datasource.password");
//            dataSource.setPassword(password);
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//        return dataSource;



        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUser(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }
}
