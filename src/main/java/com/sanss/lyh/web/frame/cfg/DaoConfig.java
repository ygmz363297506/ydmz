/**
 * File Name:DaoConfig.java 
 * Package Name:com.sanss.fptc.web.frame.config 
 * Date:2015年3月16日下午3:26:24 
 * Copyright (c) 2015, hyphantom@gmail.com All Rights Reserved. 
 */
package com.sanss.lyh.web.frame.cfg;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: DaoConfig <br/>
 * date: 2015年3月16日 下午3:26:24 <br/>
 * 
 * @author phantom洋
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import({ DataSourceConfig.class })
public class DaoConfig {

	@Resource(name = "dataSource")
	public DataSource dataSource;

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(dataSource);
		return dstm;
	}
}
