package com.sanss.lyh.web.frame.cfg;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory", 
	transactionManagerRef = "mysqlTransactionManager", 
	basePackages = { "com.sanss.lyh.web"})
public class MysqlConfig {
	
	@Autowired
	JpaVendorAdapter mysqlJpaVendorAdapter;
    
	@Bean(name = "mysqlDataSource")
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource("mysqlDS");
		return dataSource;
	}

	@Bean(name = "mysqlEntityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource());

		lef.setJpaVendorAdapter(mysqlJpaVendorAdapter);
		lef.setPackagesToScan("com.sanss.lyh.web.business.model");
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.ejb.naming_strategy",
				"org.hibernate.cfg.ImprovedNamingStrategy");
		lef.setJpaProperties(jpaProperties);
		lef.setPersistenceUnitName("mysqlPersistenceUnit");
		lef.afterPropertiesSet();
		return lef.getObject();
	}

	@Bean(name = "mysqlEntityManager")
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}

	@Bean(name = "mysqlTransactionManager")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
}
