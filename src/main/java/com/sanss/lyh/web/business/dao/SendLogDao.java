package com.sanss.lyh.web.business.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sanss.lyh.web.business.model.SendRecord;
import com.sanss.lyh.web.business.utils.Page;
import com.sanss.lyh.web.business.utils.SQLUtil;

@Repository
public class SendLogDao {
	// 日志定义
	final static Logger logger = LoggerFactory.getLogger(SendLogDao.class);
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询发送记录
	 * @param record
	 * @param page
	 * @return
	 */
	public List<SendRecord> querysendList(SendRecord record,Page page) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_send where callernumber=?  ");
			parameter.add(record.getCallernumber());
			if (record.getCallednumber()!=null && !"".equals(record.getCallednumber())) {
				sqlbuffer.append(" and callednumber=?");
				parameter.add(record.getCallednumber());
			}
			if(record.getText()!=null && !"".equals(record.getText())){
				sqlbuffer.append(" and text like ?");
				parameter.add("%"+record.getText()+"%");
			}
			sqlbuffer.append(" order by sendtime desc  ");
			logger.info("查询sql："+sqlbuffer.toString());
			return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(), new BeanPropertyRowMapper<SendRecord>(SendRecord.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 查询发送记录个数
	 * @param record
	 * @return
	 */
	public long countsendlist(SendRecord record) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_send where callernumber=?  ");
			parameter.add(record.getCallernumber());
			if (record.getCallednumber()!=null && !"".equals(record.getCallednumber())) {
				sqlbuffer.append(" and callednumber=?");
				parameter.add(record.getCallednumber());
			}
			if(record.getText()!=null && !"".equals(record.getText())){
				sqlbuffer.append(" and text like ?");
				parameter.add("%"+record.getText()+"%");
			}
			sqlbuffer.append(" order by sendtime desc  ");
			logger.info("查询sql："+sqlbuffer.toString());
			long result=jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(),long.class);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	
}
