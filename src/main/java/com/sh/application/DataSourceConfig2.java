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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.sh.dmdao", sqlSessionTemplateRef = "dmSqlSessionTemplate")
public class DataSourceConfig2 {

	@Bean(name = "dmDataSource")
	@Qualifier("dmDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.dm")
	public DataSource dmDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dmJdbcTemplate")
	public JdbcTemplate dmJdbcTemplate(@Qualifier("dmDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Qualifier("dmTransactionManager")
	@Bean(name = "dmTransactionManager")
	public DataSourceTransactionManager dmTransactionManager(@Qualifier("dmDataSource") DataSource dmDataSource) {
		DataSourceTransactionManager dmTransactionManager = new DataSourceTransactionManager();
		dmTransactionManager.setDataSource(dmDataSource);
		return dmTransactionManager;
	}

	@Bean(name = "dmSqlSessionFactory")
	@Qualifier("dmSqlSessionFactory")
	public SqlSessionFactory dmSqlSessionFactory(@Qualifier("dmDataSource") DataSource dmDataSource) throws Exception {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean dmSqlSessionFactory = new SqlSessionFactoryBean();
		dmSqlSessionFactory.setDataSource(dmDataSource);
		try {
			dmSqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/dmmapper/*.xml"));
			return dmSqlSessionFactory.getObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Bean(name = "dmSqlSessionTemplate")
	public SqlSessionTemplate dmSqlSessionTemplate(@Qualifier("dmSqlSessionFactory") SqlSessionFactory dmSqlSessionFactory) {
		return new SqlSessionTemplate(dmSqlSessionFactory);
	}
}
