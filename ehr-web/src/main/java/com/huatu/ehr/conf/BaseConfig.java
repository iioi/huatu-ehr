package com.huatu.ehr.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan({"com.huatu.ehr.dao","com.huatu.ehr.service"})
public class BaseConfig {
	
	@Value("${jdbc.username}")
	private String jdbc_username;
	@Value("${jdbc.password}")
	private String jdbc_password;
	@Value("${jdbc.url}")
	private String jdbc_url;

	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername(jdbc_username);
		dataSource.setPassword(jdbc_password);
		dataSource.setUrl(jdbc_url);
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource); 
	}
	
	@Bean
	public DataSourceTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	 
}
