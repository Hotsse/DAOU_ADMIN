package com.daou.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.daou.admin.common.interceptor.CommonInterceptor;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

/**
 * Context 설정 
 * 
 * @fileName	ContextConfig.java
 * @author	hsyoon
 */
@Configuration
@EnableWebMvc
public class ContextConfig implements WebMvcConfigurer {
	
	@Autowired
	private CommonInterceptor commonInterceptor;
	
	
	/**
	 * CORS 맵핑 설정
	 * 
	 * @methodName	addCorsMappings
	 * @author			hsyoon
	 * @param				registry
	 * @return
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedHeaders("*")
			.allowCredentials(true);			
	}
	
	/**
	 * Interceptor 설정
	 * @methodName	addInterceptors
	 * @author			hsyoon
	 * @param				registry
	 * @return
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor);
	}
	
	/**
	 * Tiles3 view resolver
	 * 
	 * @methodName	tilesViewResolver
	 * @author			hsyoon
	 * @return
	 */
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
	
	/**
	 * Tiles3 configuration
	 * 
	 * @methodName	tilesConfigurer
	 * @author			hsyoon
	 * @return
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {"classpath:config/tiles3.xml"});
		configurer.setCheckRefresh(true);
		return configurer;
	}
		
	/**
	 * XSS Filter configuration
	 * 
	 * @methodName	getFilterRegistrationBean
	 * @author			hsyoon
	 * @return
	 */
	@Bean
    public FilterRegistrationBean<XssEscapeServletFilter> getFilterRegistrationBean(){
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<XssEscapeServletFilter>();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
