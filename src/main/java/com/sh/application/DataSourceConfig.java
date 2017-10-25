package com.sh.application;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.sh.dao", sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class DataSourceConfig {

	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "primaryJdbcTemplate")
	@Primary
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "primaryTransactionManager")
	@Primary
	public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
		DataSourceTransactionManager primaryTransactionManager = new DataSourceTransactionManager();
		primaryTransactionManager.setDataSource(primaryDataSource);
		return primaryTransactionManager;
	}

	@Bean(name = "primarySqlSessionFactory")
	@Qualifier("primarySqlSessionFactory")
	@Primary
	public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean primarySqlSessionFactory = new SqlSessionFactoryBean();
		primarySqlSessionFactory.setDataSource(dataSource);
		try {
			primarySqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
			return primarySqlSessionFactory.getObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Bean(name = "primarySqlSessionTemplate")
	@Primary
	public SqlSessionTemplate dmSqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory primarySqlSessionFactory) {
		return new SqlSessionTemplate(primarySqlSessionFactory);
	}

}
