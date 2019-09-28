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
	 * @methodName	daouDataSource
	 * @author			hsyoon
	 * @return
	 */
	@Bean(name = "daouDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.daou")
	public DataSource daouDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * SqlSessionFactory 객체 생성
	 * 
	 * @methodName	daouSqlSessionFactory
	 * @author			hsyoon
	 * @param				dataSource
	 * @param				applicationContext
	 * @return
	 * @throws			Exception
	 */
	@Bean(name = "daouSqlSessionFactory")
	public SqlSessionFactory daouSqlSessionFactory(@Qualifier("daouDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/daou/*/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * SqlSessionTemplate 객체 생성
	 * @methodName	daouSqlSessionTemplate
	 * @author			hsyoon
	 * @param				sqlSessionFactory
	 * @return
	 * @throws			Exception
	 */
	@Bean(name = "daouSqlSessionTemplate")
	public SqlSessionTemplate daouSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
