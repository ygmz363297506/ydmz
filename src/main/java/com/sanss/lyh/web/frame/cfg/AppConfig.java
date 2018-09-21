package com.sanss.lyh.web.frame.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


@Configuration
@EnableScheduling
@ComponentScan(
        value = {"com.sanss.lyh"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
        })
public class AppConfig {	
	
	@Autowired
	Environment env;

	
    @Bean
	public JpaVendorAdapter mysqlJpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		
		return jpaVendorAdapter;
	}

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
      
    
    public static Object getBean(String beanName){
    	WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
    	Object obj = wac.getBean(beanName);
    	return obj;
    }
}