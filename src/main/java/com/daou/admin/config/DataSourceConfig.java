package com.daou.admin.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	
	@Bean(name = "eventDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.event")
	public DataSource eventDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "eventSqlSessionFactory")
	public SqlSessionFactory eventSqlSessionFactory(@Qualifier("eventDataSource") DataSource eventDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(eventDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/event/*/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name = "eventSqlSessionTemplate")
	public SqlSessionTemplate eventSqlSessionTemplate(SqlSessionFactory eventSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(eventSqlSessionFactory);
	}

}
