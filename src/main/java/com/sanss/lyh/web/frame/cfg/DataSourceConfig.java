/**
 * File Name:DataSourceConfig.java 
 * Package Name:com.sanss.fptc.web.frame.config 
 * Date:2015年3月17日下午1:44:47 
 * Copyright (c) 2015, hyphantom@gmail.com All Rights Reserved. 
 */
package com.sanss.lyh.web.frame.cfg;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

/**
 * ClassName: DataSourceConfig <br/>
 * date: 2015年3月17日 下午1:44:47 <br/>
 * 
 * @author phantom洋
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class DataSourceConfig {
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource("mysqlDS");
		return dataSource;
	}
}
