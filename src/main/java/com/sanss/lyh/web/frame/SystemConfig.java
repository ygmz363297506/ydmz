package com.sanss.lyh.web.frame;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({ "classpath:/config/properties/SystemConfig.properties" })
public class SystemConfig {
	public static final SimpleDateFormat DBdayFormat = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat UIdayFormat = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat DBdaytimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat UIdaytimeFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	public static final SimpleDateFormat DBtimeFormat = new SimpleDateFormat("HHmmss");
	public static final SimpleDateFormat UItimeFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static final SimpleDateFormat NewtimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Value("${reload.password}")
	public String reloadPassword;

	public String getReloadPassword() {
		return reloadPassword;
	}

	public void setReloadPassword(String reloadPassword) {
		this.reloadPassword = reloadPassword;
	}
	
	 
    @Value("${encrypt.key}")
	public String encryptKey;  

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
