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

/**
 * DataSource 설정
 * @fileName	DataSourceConfig.java
 * @author	hsyoon
 */
@Configuration
public class DataSourceConfig {
	
	/**
	 * DataSource 객체 생성
	 *
	 * @methodName	eventDataSource
	 * @author			hsyoon
	 * @return
	 */
	@Bean(name = "eventDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.event")
	public DataSource eventDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * SqlSessionFactory 객체 생성
	 * 
	 * @methodName	eventSqlSessionFactory
	 * @author			hsyoon
	 * @param				dataSource
	 * @param				applicationContext
	 * @return
	 * @throws			Exception
	 */
	@Bean(name = "eventSqlSessionFactory")
	public SqlSessionFactory eventSqlSessionFactory(@Qualifier("eventDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/event/*/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * SqlSessionTemplate 객체 생성
	 * @methodName	eventSqlSessionTemplate
	 * @author			hsyoon
	 * @param				sqlSessionFactory
	 * @return
	 * @throws			Exception
	 */
	@Bean(name = "eventSqlSessionTemplate")
	public SqlSessionTemplate eventSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
